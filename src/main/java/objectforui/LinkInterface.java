package objectforui;

import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;

import java.util.ArrayList;

public interface LinkInterface {
    void linkLeHoi(ArrayList<LeHoi> leHoi);
    void linkNhanVat(ArrayList<NhanVat> nhanVatLichSu);

    void linkSuKien(ArrayList<SuKienLichSu> suKienChienTranh);

    void linkTrieuDai(ArrayList<TrieuDai> trieuDai);
}
