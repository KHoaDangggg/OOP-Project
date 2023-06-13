package CrawlData.CrawlNhanVat.CrawlAnhHung;

import CrawlData.CrawlNhanVat.NhanVat;

public class AnhHung extends NhanVat {
    private String tuLieu;
    private String tomTat;
    private String queQuan;


    public String gettuLieu() {
        return tuLieu;
    }

    public String gettomTat() {
        return tomTat;
    }

    public String getQueQuan() {
        return queQuan;
    }


    public AnhHung(String ten, String tuLieu, String queQuan, String tomTat) {
        super(ten);
        this.tuLieu = tuLieu;
        this.tomTat = tomTat;
        this.queQuan = queQuan;
    }
}
