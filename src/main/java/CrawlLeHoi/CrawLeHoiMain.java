package CrawlLeHoi;

import CrawlLeHoi.LeHoi_Nguon_01.CrawlLeHoiNguon01;
import CrawlLeHoi.LeHoi_Nguon_02.CrawlLeHoiNguon02;

public class CrawLeHoiMain {

    private static final CrawlLeHoiNguon01 nguon01 = new CrawlLeHoiNguon01();
    private static final CrawlLeHoiNguon02 nguon02 = new CrawlLeHoiNguon02();
    public static void main(String[] args) {
        nguon01.crawlFromWiki();
        nguon02.wolCrawl();
    }
}
