package CrawlLeHoi.LeHoi_Nguon_05;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Thread_T implements Runnable{
    private String url;

    public Thread_T(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
         getLinkFromPage(url);
    }

    private void getLinkFromPage(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element e = doc.getElementsByClass("listview").first();
        if (e != null) {
            Elements linkContainers = e.getElementsByClass("title");
            for(Element l: linkContainers){
                String link = l.attr("href");
                String BASE_URL = "http://lehoi.info";
                link = BASE_URL + link;
                System.out.println(link);
                CrawlLeHoiNguon05.leHoiArticles.add(link);
            }
        }
    }
}
