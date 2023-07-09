package CrawlData.CrawlDiTich;

public class CrawlDiTichMain {
    public static void main(String[] args) {
        CrawlFromDiTichVN crawlDiTich = new CrawlFromDiTichVN();
        crawlDiTich.crawl();
        System.out.println("Crawl from DiTich.vn successfully!");
    }
}
