package CrawlData.CrawlNhanVat;

import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;
import CrawlData.Info;

import java.util.HashMap;

public abstract class NhanVat extends Info {
    public NhanVat(String ten) {
        super(ten);
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

}
