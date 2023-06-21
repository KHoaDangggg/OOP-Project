package CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu;

import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

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
    public HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
    public HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();

    public void setLienKetSuKien(HashMap<String, SuKienChienTranh> lienKetSuKien) {
        this.lienKetSuKien = lienKetSuKien;
    }

    public void setLienKetTrieuDai(HashMap<String, TrieuDai> lienKetTrieuDai) {
        this.lienKetTrieuDai = lienKetTrieuDai;
    }

    public HashMap<String, SuKienChienTranh> getLienKetSuKien() {
        return lienKetSuKien;
    }

    public HashMap<String, TrieuDai> getLienKetTrieuDai() {
        return lienKetTrieuDai;
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

    public NhanVatLichSu(String ten, HashMap<String, String> thongTinCoBan, String mieuTa, HashMap<String, String> thongTin) {
        super(ten);
        this.thongTinCoBan = thongTinCoBan;
        this.mieuTa = mieuTa;
        this.thongTin = thongTin;
    }
    public NhanVatLichSu(){

    }
}