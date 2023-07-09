package model.trieu_dai;
import model.nhan_vat.Vua;
import model.DuLieuLichSu;
import model.su_kien.SuKienLichSu;

import java.util.ArrayList;
import java.util.HashMap;


public class TrieuDai extends DuLieuLichSu {

    String moTa;
    String kinhDo;
    String quocHieu;
    String namBatDau;
    String namKetThuc;

    ArrayList<String> kings;
    HashMap<String, Vua> lienKetVua = new HashMap<>();

    HashMap<String, SuKienLichSu> lienKetSuKien = new HashMap<>();

    public TrieuDai(String ten) {
        super(ten);
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public void setQuocHieu(String quocHieu) {
        this.quocHieu = quocHieu;
    }

    public TrieuDai(String ten, String batDau, String ketThuc, String moTa, ArrayList<String> kings) {
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

    public void setLienKetSuKien(HashMap<String, SuKienLichSu> lienKetSuKien) {
        this.lienKetSuKien = lienKetSuKien;
    }

    public void setLienKetVua(HashMap<String, Vua> lienKetVua) {
        this.lienKetVua = lienKetVua;
    }

    public HashMap<String, SuKienLichSu> getLienKetSuKien() {
        return lienKetSuKien;
    }

    public HashMap<String, Vua> getLienKetVua() {
        return lienKetVua;
    }
}
