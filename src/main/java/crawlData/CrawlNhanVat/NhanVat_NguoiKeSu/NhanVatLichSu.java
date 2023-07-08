package crawlData.CrawlNhanVat.NhanVat_NguoiKeSu;

import crawlData.CrawlNhanVat.NhanVat;


import java.util.HashMap;

public class NhanVatLichSu extends NhanVat {

    private HashMap<String, String> thongTinCoBan;
    private String mieuTa;
    private HashMap<String, String> thongTin;

    public NhanVatLichSu(String ten, String mieuTa, HashMap<String, String> thongTin) {
        super(ten);
        this.mieuTa = mieuTa;
        this.thongTin = thongTin;
    }

    public HashMap<String, String> getThongTinCoBan() {
        return thongTinCoBan;
    }
    public void setThongTinCoBan(HashMap<String, String> thongTinCoBan) {
        this.thongTinCoBan = thongTinCoBan;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public HashMap<String, String> getThongTin() {
        return thongTin;
    }

    public void setThongTin(HashMap<String, String> thongTin) {
        this.thongTin = thongTin;
    }
    public NhanVatLichSu(String ten){
        super(ten);

    }
}
