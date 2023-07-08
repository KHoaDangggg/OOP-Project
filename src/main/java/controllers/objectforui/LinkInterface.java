package controllers.objectforui;

import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;

import java.util.ArrayList;

public interface LinkInterface {
    void linkLeHoi(ArrayList<LeHoi> leHoi);
    void linkNhanVat(ArrayList<NhanVat> nhanVatLichSu);
    void linkSuKien(ArrayList<SuKienChienTranh> suKienChienTranh);
    void linkTrieuDai(ArrayList<trieuDai> trieuDai);
}
