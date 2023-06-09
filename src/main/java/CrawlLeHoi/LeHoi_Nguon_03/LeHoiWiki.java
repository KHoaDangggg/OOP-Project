package CrawlLeHoi.LeHoi_Nguon_03;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LeHoiWiki {
    private String ten;

    private String mieuTa;
    private LinkedHashMap<String, String> dacDiemLeHoi;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public HashMap<String, String> getDacDiemLeHoi() {
        return dacDiemLeHoi;
    }

    public void setDacDiemLeHoi(LinkedHashMap<String, String> dacDiemLeHoi) {
        this.dacDiemLeHoi = dacDiemLeHoi;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public LeHoiWiki(String ten, String mieuTa, LinkedHashMap<String, String> dacDiemLeHoi) {
        this.ten = ten;
        this.mieuTa = mieuTa;
        this.dacDiemLeHoi = dacDiemLeHoi;
    }
}
