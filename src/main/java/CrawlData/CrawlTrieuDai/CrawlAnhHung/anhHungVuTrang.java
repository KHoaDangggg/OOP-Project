package CrawlData.CrawlTrieuDai.CrawlAnhHung;

public class anhHungVuTrang {
    private String ten;
    private String namSinhNamMat;
    private String danToc;
    private String queQuan;
    private String namPhong;
    private String tieuSu;
    private String link;

    public String getTen() {
        return ten;
    }

    public String getNamSinhNamMat() {
        return namSinhNamMat;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getNamPhong() {
        return namPhong;
    }

    public String getTieuSu() {
        return tieuSu;
    }
    public String getLink(){ return  link; }



    public anhHungVuTrang(String ten, String namSinhNamMat, String danToc, String queQuan, String namPhong, String tieuSu, String link) {
        this.ten = ten;
        this.namSinhNamMat = namSinhNamMat;
        this.danToc = danToc;
        this.queQuan = queQuan;
        this.namPhong = namPhong;
        this.tieuSu = tieuSu;
        this.link = link;
    }
}
