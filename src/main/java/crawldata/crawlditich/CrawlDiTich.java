package crawldata.crawlditich;

public class CrawlDiTich {
    public static void main(String[] args) {
        CrawlDiTichVN crawlDiTichVN = new CrawlDiTichVN();
        crawlDiTichVN.crawl();
        CrawlDiTichWiki crawlDiTichWiki = new CrawlDiTichWiki();
        crawlDiTichWiki.crawl();
    }
}
