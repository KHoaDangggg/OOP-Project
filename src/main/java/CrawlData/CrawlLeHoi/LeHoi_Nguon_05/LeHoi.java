package CrawlData.CrawlLeHoi.LeHoi_Nguon_05;

import java.util.ArrayList;

public class LeHoi {
    private String ten;
    private String diaDiem;
    private String thoiGian;
    private ArrayList<String> linkAnh;
    private String thongTinLeHoi;

    private final String linkLeHoi;

    public String getLinkLeHoi() {
        return linkLeHoi;
    }


    public LeHoi(String ten, String diaDiem, String thoiGian, ArrayList<String> anh, String thongTinLeHoi, String linkLeHoi) {
        this.ten = ten;
        this.diaDiem = diaDiem;
        this.thoiGian = thoiGian;
        this.linkAnh = anh;
        this.thongTinLeHoi = thongTinLeHoi;
        this.linkLeHoi = linkLeHoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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
