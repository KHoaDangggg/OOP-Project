package model.nhanvat;

import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;
import model.DuLieuLichSu;

import java.util.HashMap;

public abstract class NhanVat extends DuLieuLichSu {
    public NhanVat(String ten) {
        super(ten);
    }

    public HashMap<String, SuKienLichSu> lienKetSuKien = new HashMap<>();
    public HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();

    public void setLienKetSuKien(HashMap<String, SuKienLichSu> lienKetSuKien) {
        this.lienKetSuKien = lienKetSuKien;
    }

    public void setLienKetTrieuDai(HashMap<String, TrieuDai> lienKetTrieuDai) {
        this.lienKetTrieuDai = lienKetTrieuDai;
    }

    public HashMap<String, SuKienLichSu> getLienKetSuKien() {
        return lienKetSuKien;
    }

    public HashMap<String, TrieuDai> getLienKetTrieuDai() {
        return lienKetTrieuDai;
    }

}
