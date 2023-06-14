package CrawlData.CrawlNhanVat.CrawlAnhHung;

import CrawlData.CrawlNhanVat.NhanVat;

public class anhHungVuTrang extends NhanVat {

    private String namSinhNamMat;
    private String danToc;
    private String queQuan;
    private String namPhong;
    private String tieuSu;


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

    public anhHungVuTrang(String ten, String namSinhNamMat, String danToc, String queQuan, String namPhong, String tieuSu) {
        super(ten);
        this.namSinhNamMat = namSinhNamMat;
        this.danToc = danToc;
        this.queQuan = queQuan;
        this.namPhong = namPhong;
        this.tieuSu = tieuSu;
    }
}