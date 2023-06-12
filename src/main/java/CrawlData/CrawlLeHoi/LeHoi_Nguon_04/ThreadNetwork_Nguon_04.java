package CrawlData.CrawlLeHoi.LeHoi_Nguon_04;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ThreadNetwork_Nguon_04 implements Runnable{

    private final String url;

    public ThreadNetwork_Nguon_04(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        getDataFromUrl(url);
    }

    public void getDataFromUrl(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements e = doc.getElementsByClass("mw-category");
        if (e.size() > 0) {
            Elements elements = e.get(0).select("a[href]");
            ArrayList<String> url1s = new ArrayList<>();
            for (Element e1 : elements) {
                String BASE_URL = "https://vi.wikipedia.org";
                if(!e1.attr("href").equals("")) {
                    String url1 = BASE_URL + e1.attr("href");
                    url1s.add(url1);
                }
            }
            //System.out.println(url1s);
            CrawlLeHoiNguon04.url1s.addAll(url1s);
        }
    }

}
