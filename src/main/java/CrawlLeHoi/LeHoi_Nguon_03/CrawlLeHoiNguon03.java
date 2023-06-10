package CrawlLeHoi.LeHoi_Nguon_03;

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

public class CrawlLeHoiNguon03 {

    public ArrayList<LeHoiWiki> list = new ArrayList<>();
    private final String BASE_URL = "https://vi.wikipedia.org";
    private final String URL = "https://vi.wikipedia.org/wiki/Th%E1%BB%83_lo%E1%BA%A1i:L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam_theo_t%E1%BB%89nh_th%C3%A0nh";
    public static ArrayList<String> url1s = new ArrayList<>();

    public void crawlMultipleLinks() {
        getUrls();
        for (String url : url1s) {
            getData(url);
        }

        //check
        for (LeHoiWiki l : list) {
            System.out.println(l.getTen() + " " + l.getDacDiemLeHoi().keySet());
        }
        System.out.println("So luong le hoi: " + list.size());

        //Convert to json
        writeToJSON(list);
    }

    private void getData(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element e = doc.getElementsByClass("mw-parser-output").first();
        String ten = Objects.requireNonNull(doc.getElementsByClass("firstHeading mw-first-heading").first()).text();
        //System.out.println(e.text());
        if (e != null) {
            createLeHoiWiki(e, ten, url);
        }
        //System.out.println(list.get(0).getTen());
    }

    private void createLeHoiWiki(Element e, String ten, String url) {
        LinkedHashMap<String, String> dacDiem = new LinkedHashMap<>();
        LeHoiWiki leHoiWiki = new LeHoiWiki(ten, null, dacDiem);
        //Lay thong tin mieu ta cua le hoi
        int i = 1;
        StringBuilder builder = new StringBuilder();
        if (e.child(0).is("p")) builder.append(e.child(0).text());
        if (e.childrenSize() > 1) {
            while (!e.child(i).is("h2") && e.child(i).is("p")) {
                builder.append(e.child(i).text());
                i++;
            }
            //i++;
            leHoiWiki.setMieuTa(builder.toString());
            //Lay thong tin dac diem
            String key = null;
            for (; i < e.childrenSize(); i++) {
                StringBuilder builder1 = new StringBuilder();
                if (e.child(i).is("h2")) {
                    key = e.child(i).text();
                    int a = key.indexOf("[");
                    key = key.substring(0, a);
                    //System.out.println(key);
                    i++;
                } else {
                    while (i < e.childrenSize() && !e.child(i).is("h2")) {
                        builder1.append(e.child(i).text());
                        i++;
                    }
                    if (i != e.childrenSize()) i--;
                }
                dacDiem.put(key, builder1.toString());
                leHoiWiki.setDacDiemLeHoi(dacDiem);
            }
            leHoiWiki.setLinkLeHoi(url);
            //Loai bo
            leHoiWiki.getDacDiemLeHoi().remove("Chú thích");
            leHoiWiki.getDacDiemLeHoi().remove("Tham khảo");
            leHoiWiki.getDacDiemLeHoi().remove("Xem thêm");
            leHoiWiki.getDacDiemLeHoi().remove("Liên kết ngoài");
            leHoiWiki.getDacDiemLeHoi().remove("Bài liên quan");
            leHoiWiki.getDacDiemLeHoi().remove(null);
            list.add(leHoiWiki);
        }
    }

    public void getUrls() {
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element e = doc.getElementsByClass("mw-category mw-category-columns").get(0);
        Elements elements = e.select("a[href]");
        ArrayList<String> urls = new ArrayList<>();
        for (Element e1 : elements) {
            String url = BASE_URL + e1.attr("href");
            urls.add(url);
        }
        //System.out.println("#:" + urls);

        int maxThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        List<Future<?>> futures = new ArrayList<>();
        for (String url : urls) {
            Future<?> future = executor.submit(genThread(url));
            futures.add(future);
        }

// A) Await all runnables to be done (blocking)
        for (Future<?> future : futures) {
            try {
                future.get(); // get will block until the future is done
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        }

// B) Check if all runnables are done (non-blocking)
        boolean allDone = true;
        for (Future<?> future : futures) {
            allDone &= future.isDone(); // check if future is done
        }

        if (allDone) {
            System.out.println("This is the array of urls: " + url1s);
            System.out.println("Number of urls: " + url1s.size());
        }

        //Shutdown executor
        executor.shutdown();
    }
    //Using multiple threads for faster data crawling
    private Thread genThread(String url) {
        ThreadNetwork_Nguon_03 threadNetwork1 = new ThreadNetwork_Nguon_03(url);
        return new Thread(threadNetwork1);
    }

    private void writeToJSON(ArrayList<LeHoiWiki> lehoi) {
        JSONArray jsonArray = new JSONArray();
        for (LeHoiWiki leHoi : lehoi) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tên lễ hội", leHoi.getTen());
            jsonObject.put("miêu tả", leHoi.getMieuTa());
            for(String key: leHoi.getDacDiemLeHoi().keySet())
             jsonObject.put(key, leHoi.getDacDiemLeHoi().get(key));
            jsonObject.put("link lễ hội", leHoi.getLinkLeHoi());
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_DATA/LeHoi_Nguon_03.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
