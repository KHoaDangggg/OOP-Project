package crawldata.crawltrieudai;

import java.io.UnsupportedEncodingException;

public class CrawlTrieuDai {
    public static void main(String[] args) throws UnsupportedEncodingException {
        CrawlTrieuDaiManager manager = new CrawlTrieuDaiManager();
        manager.crawl();
    }
}
