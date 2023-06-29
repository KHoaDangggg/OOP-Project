package CrawlData.CrawlNhanVat.NhaVat_VanSu;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrawlFromVanSu {
    private final ArrayList<String> pages = new ArrayList<>();
    private final ArrayList<String> urls = new ArrayList<>();
    private final String BASE_URL = "https://vansu.vn";
    private final ArrayList<NhanVatVanSu> listNhanVat = new ArrayList<>();


    public void crawl(){
        getPageLinks();
        getLinkInPages();
        getCharacterInLink();
        writeToJSON(listNhanVat);
    }
    private void getPageLinks(){
        for(int i = 0; i<119; i++){
            pages.add(BASE_URL+"/viet-nam/viet-nam-nhan-vat?page="+i);
        }
    }

    private void getLinkInPages(){
        int maxThreads = 10;
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
                throw new RuntimeException(ex);
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

    private void getCharacterInLink(){
        int maxThreads = 10;
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
                throw new RuntimeException(ex);
            }
        }

        // Check if all runnables are done (non-blocking)
        boolean allDone = true;
        for (Future<?> future : futures) {
            allDone &= future.isDone(); // check if future is done
        }

        if (allDone) {
            //System.out.println("This is the array of characters: " + listNhanVat);
            System.out.println("Number of characters: " + listNhanVat.size());
        }

        //Shutdown executor
        executor.shutdown();
    }

    private Thread getLinkThread(String url){
        Runnable runnable = () -> {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Elements rows = doc.body().select("tbody").select("tr");
            for (Element row : rows) {
                Elements td = row.children();
                if(td.get(0) != null){
                    String s = td.get(0).select("a").attr("href");
                    urls.add(BASE_URL+s);
                }
            }
        };
        return new Thread(runnable);
    }

    private Thread getDataThread(String url){
        Runnable runnable = () -> {
            LinkedHashMap<String, String> attr = new LinkedHashMap<>();
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ten = Objects.requireNonNull(doc.body().selectFirst("div[class=\"active section\"]")).text();
            Elements rows = doc.body().select("tbody").select("tr");
            for (Element row : rows) {
                Elements td = row.children();
                if (td.size() > 1) {
                    attr.put(td.get(0).text(), td.get(1).text());
                }
            }
            Element tt = rows.last();
            if(tt!=null) attr.put("Thông tin", tt.text());

            NhanVatVanSu nv = new NhanVatVanSu(ten, attr);
            System.out.println(ten);
            listNhanVat.add(nv);
        };
        return new Thread(runnable);
    }

    private static void writeToJSON(ArrayList<NhanVatVanSu> nv) {
        JSONArray jsonArray = new JSONArray();
        for (NhanVatVanSu nhanVat : nv) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ten", nhanVat.getTen());
            HashMap<String, String> ttcb = nhanVat.getAtt();
            if(ttcb!=null){
                for(String key: ttcb.keySet()) jsonObject.put(key, nhanVat.getAtt().get(key));
            }
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_Data/NhanVatLichSu_VanSu.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
