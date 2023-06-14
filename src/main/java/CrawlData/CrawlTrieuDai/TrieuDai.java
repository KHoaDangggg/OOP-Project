package CrawlData.CrawlTrieuDai;
import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;

import java.util.ArrayList;
import java.util.HashMap;


public class TrieuDai {
    String moTa;
    String kinhDo;
    String quocHieu;
    String ten;
    String namBatDau;
    String namKetThuc;

    ArrayList<String> kings;
    HashMap<String, Vua> lienKetVua = new HashMap<>();

    HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public void setQuocHieu(String quocHieu) {
        this.quocHieu = quocHieu;
    }

    public TrieuDai(String ten, String batDau, String ketThuc, String moTa, ArrayList<String> kings) {
        this.ten = ten;
        this.namBatDau = batDau;
        this.namKetThuc = ketThuc;
        this.moTa = moTa;
        this.kings = kings;
    }

    public String getQuocHieu() {
        return quocHieu;
    }

    public String getTen() {
        return ten;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public String getNamBatDau() {
        return namBatDau;
    }

    public String getNamKetThuc() {
        return namKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public ArrayList<String> getKings() {
        return kings;
    }

    public void setLienKetSuKien(HashMap<String, SuKienChienTranh> lienKetSuKien) {
        this.lienKetSuKien = lienKetSuKien;
    }

    public void setLienKetVua(HashMap<String, Vua> lienKetVua) {
        this.lienKetVua = lienKetVua;
    }

    public HashMap<String, SuKienChienTranh> getLienKetSuKien() {
        return lienKetSuKien;
    }

    public HashMap<String, Vua> getLienKetVua() {
        return lienKetVua;
    }
}
