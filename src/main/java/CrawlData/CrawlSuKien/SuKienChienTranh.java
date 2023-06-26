package CrawlData.CrawlSuKien;

import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlTrieuDai.TrieuDai;
import java.util.ArrayList;
import java.util.HashMap;


public class SuKienChienTranh {
    private String tenSuKien;
    private String thoiGian;
    private String diaDiem;
    private String ketQua;
    private String pheTa;
    private String pheDich;
    private String chiHuyPheTa;
    private String chiHuyPheDich;
    private String lucLuongPheTa;
    private String lucLuongPheDich;
    private String tonThatTa;
    private String tonThatDich;
    private String nguyenNhan;
    private ArrayList<String> nameRelativePerson = new ArrayList<>();
    private ArrayList<String> nameRelativeDinasty = new ArrayList<>();
    private HashMap<String, NhanVat> lienKetNhanVat = new HashMap<>();
    private HashMap<String, TrieuDai>lienKetTrieuDai = new HashMap<>();
    public void setTenSuKien(String tenSuKien) {
        this.tenSuKien = tenSuKien;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public void setPheTa(String pheTa) {
        this.pheTa = pheTa;
    }

    public void setPheDich(String pheDich) {
        this.pheDich = pheDich;
    }

    public void setChiHuyPheTa(String chiHuyPheTa) {
        this.chiHuyPheTa = chiHuyPheTa;
    }

    public void setChiHuyPheDich(String chiHuyPheDich) {
        this.chiHuyPheDich = chiHuyPheDich;
    }

    public void setLucLuongPheTa(String lucLuongPheTa) {
        this.lucLuongPheTa = lucLuongPheTa;
    }

    public void setLucLuongPheDich(String lucLuongPheDich) {
        this.lucLuongPheDich = lucLuongPheDich;
    }

    public void setTonThatTa(String tonThatTa) {
        this.tonThatTa = tonThatTa;
    }

    public void setTonThatDich(String tonThatDich) {
        this.tonThatDich = tonThatDich;
    }

    public void setNguyenNhan(String nguyenNhan) {
        this.nguyenNhan = nguyenNhan;
    }

    public void setNameRelativePerson(ArrayList<String> nameRelativePerson) {
        this.nameRelativePerson = nameRelativePerson;
    }

    public void setNameRelativeDinasty(ArrayList<String> nameRelativeDinasty) {
        this.nameRelativeDinasty = nameRelativeDinasty;
    }

    public void setLienKetNhanVat(HashMap<String, NhanVat> lienKetNhanVat) {
        this.lienKetNhanVat = lienKetNhanVat;
    }

    public void setLienKetTrieuDai(HashMap<String, TrieuDai> lienKetTrieuDai) {
        this.lienKetTrieuDai = lienKetTrieuDai;
    }

    public String getTenSuKien() {
        return tenSuKien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public String getKetQua() {
        return ketQua;
    }

    public String getPheTa() {
        return pheTa;
    }

    public String getPheDich() {
        return pheDich;
    }

    public String getChiHuyPheTa() {
        return chiHuyPheTa;
    }

    public String getChiHuyPheDich() {
        return chiHuyPheDich;
    }

    public String getLucLuongPheTa() {
        return lucLuongPheTa;
    }

    public String getLucLuongPheDich() {
        return lucLuongPheDich;
    }

    public String getTonThatDich() {
        return tonThatDich;
    }

    public String getTonThatTa() {
        return tonThatTa;
    }
    public String getNguyenNhan() {
        return nguyenNhan;
    }

    public ArrayList<String> getNameRelativePerson() {
        return nameRelativePerson;
    }

    public ArrayList<String> getNameRelativeDinasty() {
        return nameRelativeDinasty;
    }
    public HashMap<String, NhanVat> getLienKetNhanVat() {
        return lienKetNhanVat;
    }
    public HashMap<String, TrieuDai> getLienKetTrieuDai() {

        return lienKetTrieuDai;
    }

    public void createObject(){
        setThoiGian("Không rõ"); setDiaDiem("Không rõ"); setNguyenNhan("Không rõ");
        setPheTa("Không rõ"); setPheDich("Không rõ"); setChiHuyPheDich("Không rõ");
        setChiHuyPheTa("Không rõ"); setTonThatTa("Không rõ"); setTonThatDich("Không rõ");
        setKetQua("Không rõ");setLucLuongPheTa("Không rõ"); setLucLuongPheDich("Không rõ");

    }


    public String toString(){
        return "\n{\"tenSuKien\": "+"\""+tenSuKien+"\",\n"+
                "\"thoiGian\": "+"\""+thoiGian+"\",\n"+
                "\"diaDiem\": "+"\""+diaDiem+"\",\n"+
                "\"nguyenNhan\": "+"\""+nguyenNhan+"\",\n"+
                "\"pheTa\": "+"\""+pheTa+"\",\n"+
                "\"pheDich\": "+"\""+pheDich+"\",\n"+
                "\"chiHuyPheTa\": "+"\""+chiHuyPheTa+"\",\n"+
                "\"chiHuyPheDich\": "+"\""+chiHuyPheDich+"\",\n"+
                "\"tonThatPheTa\": "+"\""+tonThatTa+"\",\n"+
                "\"tonThatPheDich\": "+"\""+tonThatDich+"\",\n"+
                "\"ketQua\": "+"\""+ketQua+"\",\n"+
                "\"nhanVatLienQuan\": "+"\""+nameRelativePerson+"\",\n"+
                "\"trieuDaiLienQuan\": "+"\""+nameRelativeDinasty+"\"\n}";
    }
}
