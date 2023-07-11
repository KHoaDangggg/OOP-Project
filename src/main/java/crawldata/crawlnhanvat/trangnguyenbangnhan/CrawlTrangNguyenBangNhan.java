package crawldata.crawlnhanvat.trangnguyenbangnhan;

import java.io.IOException;

public class CrawlTrangNguyenBangNhan {
    public static void main(String[] args) {
        CrawlTrangNguyenBangNhanManager crawlTrangNguyenBangNhan = new CrawlTrangNguyenBangNhanManager();
        try {
            crawlTrangNguyenBangNhan.crawl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
