package CrawlData.CrawlTrieuDai.CrawlAnhHung;

public class AnhHung {
    private String hoVaTen;
    private String tuLieu;
    private String tomTat;
    private String queQuan;
    private String link;



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

    public String getLink(){ return  link; }


    public AnhHung(String hoVaTen, String tuLieu, String queQuan, String tomTat, String link) {
        this.hoVaTen = hoVaTen;
        this.tuLieu = tuLieu;
        this.tomTat = tomTat;
        this.queQuan = queQuan;
        this.link = link;
    }
}
