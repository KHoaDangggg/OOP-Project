package Controllers.object_for_ui;

import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlSuKien.SuKienLichSu;
import CrawlData.CrawlTrieuDai.TrieuDai;

import java.util.ArrayList;

public interface LinkInterface {
    void linkLeHoi(ArrayList<LeHoi> leHoi);
    void linkNhanVat(ArrayList<NhanVat> nhanVatLichSu);
    void linkSuKien(ArrayList<SuKienLichSu> suKienChienTranh);
    void linkTrieuDai(ArrayList<TrieuDai> trieuDai);
}
