package crawlData.CrawlLeHoi.LeHoi_Nguon_05;

import crawlData.info;

import java.util.ArrayList;
import java.util.HashMap;

public class LeHoi extends info {
    private String diaDiem;
    private String thoiGian;

    @Override
    public void setTen(String tenSuKien) {
        this.ten = tenSuKien;
    }
    private ArrayList<String> linkAnh;
    private String thongTinLeHoi;

    private final String linkLeHoi;
    private HashMap<String, LeHoi> lienKetLeHoi = new HashMap<>();
    public String getLinkLeHoi() {
        return linkLeHoi;
    }

    public void setLienKetLeHoi(HashMap<String, LeHoi> lienKetLeHoi) {
        this.lienKetLeHoi = lienKetLeHoi;
    }

    public HashMap<String, LeHoi> getLienKetLeHoi() {
        return lienKetLeHoi;
    }

    public LeHoi(String ten, String diaDiem, String thoiGian, ArrayList<String> anh, String thongTinLeHoi, String linkLeHoi) {
        super(ten);
        this.diaDiem = diaDiem;
        this.thoiGian = thoiGian;
        this.linkAnh = anh;
        this.thongTinLeHoi = thongTinLeHoi;
        this.linkLeHoi = linkLeHoi;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public ArrayList<String> getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(ArrayList<String> linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getThongTinLeHoi() {
        return thongTinLeHoi;
    }

    public void setThongTinLeHoi(String thongTinLeHoi) {
        this.thongTinLeHoi = thongTinLeHoi;
    }
}
