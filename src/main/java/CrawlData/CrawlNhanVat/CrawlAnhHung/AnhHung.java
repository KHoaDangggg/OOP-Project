package CrawlData.CrawlNhanVat.CrawlAnhHung;

public class AnhHung {
    private String hoVaTen;
    private String tuLieu;
    private String tomTat;
    private String queQuan;


    public String getHoVaTen() {
        return hoVaTen;
    }

    public String gettuLieu() {
        return tuLieu;
    }

    public String gettomTat() {
        return tomTat;
    }

    public String getQueQuan() {
        return queQuan;
    }


    public AnhHung(String hoVaTen, String tuLieu, String queQuan, String tomTat) {
        this.hoVaTen = hoVaTen;
        this.tuLieu = tuLieu;
        this.tomTat = tomTat;
        this.queQuan = queQuan;
    }
}
