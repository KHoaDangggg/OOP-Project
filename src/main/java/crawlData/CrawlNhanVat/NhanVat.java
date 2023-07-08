package crawlData.CrawlNhanVat;

import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import crawlData.info;

import java.util.HashMap;

public abstract class NhanVat extends info {
    public NhanVat(String ten) {
        super(ten);
    }

    public HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
    public HashMap<String, trieuDai> lienKetTrieuDai = new HashMap<>();

    public void setLienKetSuKien(HashMap<String, SuKienChienTranh> lienKetSuKien) {
        this.lienKetSuKien = lienKetSuKien;
    }

    public void setLienKetTrieuDai(HashMap<String, trieuDai> lienKetTrieuDai) {
        this.lienKetTrieuDai = lienKetTrieuDai;
    }

    public HashMap<String, SuKienChienTranh> getLienKetSuKien() {
        return lienKetSuKien;
    }

    public HashMap<String, trieuDai> getLienKetTrieuDai() {
        return lienKetTrieuDai;
    }

}
