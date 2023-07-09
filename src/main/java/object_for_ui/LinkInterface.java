package object_for_ui;

import crawl_data.model.le_hoi.LeHoi;
import crawl_data.model.nhan_vat.NhanVat;
import crawl_data.model.su_kien.SuKienLichSu;
import crawl_data.model.trieu_dai.TrieuDai;

import java.util.ArrayList;

public interface LinkInterface {
    void linkLeHoi(ArrayList<LeHoi> leHoi);
    void linkNhanVat(ArrayList<NhanVat> nhanVatLichSu);

    void linkSuKien(ArrayList<SuKienLichSu> suKienChienTranh);

    void linkTrieuDai(ArrayList<TrieuDai> trieuDai);
}
