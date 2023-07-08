package crawlData.CrawlNhanVat.CrawlAnhHung;

import crawlData.CrawlNhanVat.NhanVat;

public class DanhNhan extends NhanVat {
    private final String tuLieu;
    private final String tomTat;
    private final String queQuan;


    public String gettuLieu() {
        return tuLieu;
    }

    public String gettomTat() {
        return tomTat;
    }

    public String getQueQuan() {
        return queQuan;
    }


    public DanhNhan(String ten, String tuLieu, String queQuan, String tomTat) {
        super(ten);
        this.tuLieu = tuLieu;
        this.tomTat = tomTat;
        this.queQuan = queQuan;
    }
}
