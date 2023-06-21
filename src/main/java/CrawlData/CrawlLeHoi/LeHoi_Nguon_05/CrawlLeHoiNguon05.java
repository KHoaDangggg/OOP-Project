package CrawlData.CrawlLeHoi.LeHoi_Nguon_05;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrawlLeHoiNguon05 {
    public static final String BASE_URL = "http://lehoi.info";
    private final List<String> pages = new ArrayList<>();
    public static List<String> leHoiArticles = new ArrayList<>();
    public static List<LeHoi> listCacLeHoi = new ArrayList<>();


    public void crawlLeHoiInfo(){
        for (int i = 1; i < 64; i++) pages.add(BASE_URL + "/viet-nam/page" + i);
        System.out.println(pages);
        getArticle();
    }

    private void getArticle() {
        int maxThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        List<Future<?>> futures = new ArrayList<>();
        for (String page : pages) {
            Future<?> future = executor.submit(genThread_T(page));
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
            System.out.println("This is the array of articles: " + leHoiArticles);
            System.out.println("Number of articles: " + leHoiArticles.size());
            getListLeHoi();
        }

        //Shutdown executor
        executor.shutdown();
    }

    private void getListLeHoi() {
        int maxThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        List<Future<?>> futures = new ArrayList<>();
        for (String article : leHoiArticles) {
            Future<?> future = executor.submit(genThread_X(article));
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
            System.out.println("This is the array of lehoi: " + listCacLeHoi);
            System.out.println("Number of events: " + listCacLeHoi.size());
            writeToJSON((ArrayList<LeHoi>) listCacLeHoi);
        }

        //Shutdown executor
        executor.shutdown();
    }

    private Thread genThread_T(String url) {
        Thread_T t = new Thread_T(url);
        return new Thread(t);
    }

    private Thread genThread_X(String url) {
        Thread_X x = new Thread_X(url);
        return new Thread(x);
    }

    private void writeToJSON(ArrayList<LeHoi> lehoi) {
        JSONArray jsonArray = new JSONArray();
        for (LeHoi leHoi : lehoi) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ten", leHoi.getTen());
            jsonObject.put("diaDiem", leHoi.getDiaDiem());
            jsonObject.put("thoiGian", leHoi.getThoiGian());
            jsonObject.put("linkAnh", leHoi.getLinkAnh());
            jsonObject.put("thongTinLeHoi", leHoi.getThongTinLeHoi());
            jsonObject.put("linkLeHoi", leHoi.getLinkLeHoi());
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_DATA/LeHoi_Nguon_05.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
