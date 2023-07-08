package crawlData.crawltrieudai;
import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.info;

import java.util.ArrayList;
import java.util.HashMap;


public class trieuDai extends info {

    String moTa;
    String kinhDo;
    String quocHieu;
    String namBatDau;
    String namKetThuc;

    ArrayList<String> kings;
    HashMap<String, Vua> lienKetVua = new HashMap<>();

    HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();

    public trieuDai(String ten) {
        super(ten);
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public void setQuocHieu(String quocHieu) {
        this.quocHieu = quocHieu;
    }

    public trieuDai(String ten, String batDau, String ketThuc, String moTa, ArrayList<String> kings) {
        super(ten);
        this.namBatDau = batDau;
        this.namKetThuc = ketThuc;
        this.moTa = moTa;
        this.kings = kings;
    }

    public String getQuocHieu() {
        return quocHieu;
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
