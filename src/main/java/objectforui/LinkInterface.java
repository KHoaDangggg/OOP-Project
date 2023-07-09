package objectforui;

import model.le_hoi.LeHoi;
import model.nhan_vat.NhanVat;
import model.su_kien.SuKienLichSu;
import model.trieu_dai.TrieuDai;

import java.util.ArrayList;

public interface LinkInterface {
    void linkLeHoi(ArrayList<LeHoi> leHoi);
    void linkNhanVat(ArrayList<NhanVat> nhanVatLichSu);

    void linkSuKien(ArrayList<SuKienLichSu> suKienChienTranh);

    void linkTrieuDai(ArrayList<TrieuDai> trieuDai);
}
