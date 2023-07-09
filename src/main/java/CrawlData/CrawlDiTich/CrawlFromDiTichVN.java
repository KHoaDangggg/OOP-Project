package CrawlData.CrawlDiTich;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

// http://ditich.vn/FrontEnd/DiTich?cpage=2&rpage=64&tpage=2

public class CrawlFromDiTichVN {
    private final ArrayList<String> pages = new ArrayList<>();
    private final ArrayList<String> urls = new ArrayList<>();
    private final String BASE_URL = "http://ditich.vn";
    private final ArrayList<DiTich_VN> listDiTich = new ArrayList<>();


    public void crawl(){
        getPageLinks();
        getLinkInPages();
        getHistoricalSitesInLink();
        writeToJSON(listDiTich);
    }
    private void getPageLinks(){
        for(int i = 1; i<=63; i++){
            pages.add(BASE_URL+"/FrontEnd/DiTich"+"?cpage="+i+"&rpage=64&tpage="+i);
            System.out.println(pages.get(i-1));
        }
    }

    private void getLinkInPages(){
        int maxThreads = 8;
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        List<Future<?>> futures = new ArrayList<>();
        for (String page : pages) {
            Future<?> future = executor.submit(getLinkThread(page));
            futures.add(future);
        }

        // Await all runnables to be done (blocking)
        for (Future<?> future : futures) {
            try {
                future.get(); // get will block until the future is done
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        // Check if all runnables are done (non-blocking)
        boolean allDone = true;
        for (Future<?> future : futures) {
            allDone &= future.isDone(); // check if future is done
        }

        if (allDone) {
            //System.out.println("This is the array of urls: " + urls);
            System.out.println("Number of urls: " + urls.size());
        }

        //Shutdown executor
        executor.shutdown();
    }

    private void getHistoricalSitesInLink(){
        int maxThreads = 8;
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        List<Future<?>> futures = new ArrayList<>();
        for (String url : urls) {
            Future<?> future = executor.submit(getDataThread(url));
            futures.add(future);
        }

        // Await all runnables to be done (blocking)
        for (Future<?> future : futures) {
            try {
                future.get(); // get will block until the future is done
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        // Check if all runnables are done (non-blocking)
        boolean allDone = true;
        for (Future<?> future : futures) {
            allDone &= future.isDone(); // check if future is done
        }

        if (allDone) {
            //System.out.println("This is the array of characters: " + listNhanVat);
            System.out.println("Number of characters: " + listDiTich.size());
        }

        //Shutdown executor
        executor.shutdown();
    }

    private Thread getLinkThread(String url){
        Runnable runnable = () -> {
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (doc != null) {
                Elements hrefs = doc.getElementsByClass("ih-item square colored effect4");
                for (Element e : hrefs) {
                    String s = e.select("a").attr("href");
                    urls.add(BASE_URL + s);
                    //Check urls
                    System.out.println(BASE_URL + s);
                }
            }
        };
        return new Thread(runnable);
    }

    private Thread getDataThread(String url){
        Runnable runnable = () -> {
            LinkedHashMap<String, String> thongTin = new LinkedHashMap<>();
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String ten = null;
            if(doc!=null) {
                Element tenElement = doc.body().getElementsByClass("hl__comp-heading hl__comp_heading_custom").first();
                if (tenElement != null) ten = tenElement.text();
                String img = doc.body().getElementsByClass("sp-large").attr("src");
                Element addr = doc.body().getElementsByClass("address-line1").first();
                String diaDiem = null;
                String loaiDiTich = null;
                if (addr != null)
                    diaDiem = addr.text();
                Element listContainer = doc.body().getElementsByClass("hl__illustrated-list__featured-items").first();
                if (listContainer != null) {
                    Elements list = listContainer.children();
                    for (Element i : list) {
                        String string = i.text();
                        String[] text = string.split(":", 2);
                        if (text[0].equalsIgnoreCase("Loại hình di tích")) loaiDiTich = text[1];
                        else thongTin.put(text[0], text[1]);
                    }
                    DiTich_VN diTichVn = new DiTich_VN(ten, loaiDiTich, diaDiem, thongTin, img);
                    listDiTich.add(diTichVn);
                    //Check di tich crawled
                    System.out.println(diTichVn.getTen());
                }
            }
        };
        return new Thread(runnable);
    }

    private static void writeToJSON(ArrayList<DiTich_VN> diTichVns) {
        JSONArray jsonArray = new JSONArray();
        for (DiTich_VN diTich : diTichVns) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ten", diTich.getTen());
            jsonObject.put("loaiDiTich", diTich.getLoaiDiTich());
            jsonObject.put("diaDiem", diTich.getDiaDiem());
            jsonObject.put("img", diTich.getImg());
            HashMap<String, String> thongTin = diTich.getThongTin();
            if(thongTin!=null){
                for(String key: thongTin.keySet()) jsonObject.put(key, thongTin.get(key));
            }
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_Data/DiTichVN.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
