package Controllers.object_for_ui;

import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

import java.util.ArrayList;

public interface LinkInterface {
    void linkLeHoi(ArrayList<LeHoi> leHoi);
    void linkNhanVat(ArrayList<NhanVatLichSu> nhanVatLichSu);
    void linkSuKien(ArrayList<SuKienChienTranh> suKienChienTranh);
    void linkTrieuDai(ArrayList<TrieuDai> trieuDai);
}
