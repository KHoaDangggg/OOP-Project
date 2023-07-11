package crawldata.crawlnhanvat.vua;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlVuaVietNam {
    public static void main(String[] args) throws IOException {
        String link = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
        Document doc = Jsoup.connect(link).get();
        CrawlVuaManager crawler = new CrawlVuaManager();
        Elements table = doc.select("table[cellpadding=0][style]").select("tr");
        crawler.add(table);
        crawler.printlist();
        crawler.writeToJson();
    }
}
