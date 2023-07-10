package crawldata.crawlnhanvat.nhanvatnguoikesu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Thread_T implements Runnable{

    private String url;

    @Override
    public void run() {
        Document doc;
        try {
            doc = Jsoup.connect(url)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements names = doc.select("h2").select("a");
        for (Element name : names) {
            String url = name.attr("href");
            url = "https://nguoikesu.com" + url;
            System.out.println(url);
            CrawlNhanVat_NguoiKeSu.listlink.add(url);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
