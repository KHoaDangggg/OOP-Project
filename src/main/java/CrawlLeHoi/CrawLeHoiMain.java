package CrawlLeHoi;

import CrawlLeHoi.LeHoi_Nguon_01.CrawlLeHoiNguon01;
import CrawlLeHoi.LeHoi_Nguon_02.CrawlLeHoiNguon02;
import CrawlLeHoi.LeHoi_Nguon_03.CrawlLeHoiNguon03;
import CrawlLeHoi.LeHoi_Nguon_04.CrawlLeHoiNguon04;
import CrawlLeHoi.LeHoi_Nguon_05.CrawlLeHoiNguon05;

public class CrawLeHoiMain {

    private static final CrawlLeHoiNguon01 nguon01 = new CrawlLeHoiNguon01();
    private static final CrawlLeHoiNguon02 nguon02 = new CrawlLeHoiNguon02();
    private static final CrawlLeHoiNguon03 nguon03 = new CrawlLeHoiNguon03();
    public static final CrawlLeHoiNguon04 nguon04 = new CrawlLeHoiNguon04();
    public static final CrawlLeHoiNguon05 nguon05 = new CrawlLeHoiNguon05();
    public static void main(String[] args) {
        nguon01.crawlFromWiki();
        nguon02.wolCrawl();
        nguon03.crawlMultipleLinks();
        nguon04.crawlMultipleLinks();
        nguon05.crawlLeHoiInfo();
    }
}
