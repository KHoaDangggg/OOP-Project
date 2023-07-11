package crawldata.crawlsukien;

import java.io.IOException;

public class CrawlSuKien {
    public static void main(String[] args) {
        CrawlChienTranh crawlChienTranh = new CrawlChienTranh();
        CrawlChienTranhWiki crawlChienTranhWiki = new CrawlChienTranhWiki();
        CrawlThuVienLichSu crawlThuVienLichSu = new CrawlThuVienLichSu();
        try {
            crawlChienTranh.crawl();
            crawlChienTranhWiki.crawl();
            crawlThuVienLichSu.crawl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
