package crawl_data.crawl_di_tich.crawl_di_tich_ditichvn;

public class CrawlDiTich {
    public static void main(String[] args) {
        CrawlFromDiTichVN crawlDiTich = new CrawlFromDiTichVN();
        crawlDiTich.crawl();
        System.out.println("Crawl from DiTich.vn successfully!");
    }
}
