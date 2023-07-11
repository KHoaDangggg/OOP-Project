package crawldata.crawlnhanvat.anhhungdanhnhan;

public class CrawlAnhHungDanhNhanVH {
    public static void main(String[] args) {
        CrawlAnhHungVuTrangManager crawlAnhHungVuTrang = new CrawlAnhHungVuTrangManager();
        crawlAnhHungVuTrang.crawl();
        CrawlDanhNhanLichSuManager crawlDanhNhanLichSu = new CrawlDanhNhanLichSuManager();
        crawlDanhNhanLichSu.crawl();
    }
}
