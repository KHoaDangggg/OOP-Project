package CrawlData.CrawlSuKien;

import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlTrieuDai.TrieuDai;
import org.openqa.selenium.json.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import com.google.gson.Gson;



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
    private ArrayList<String> nameRelativePlace = new ArrayList<>();
    private ArrayList<String> nameRelativeDinasty = new ArrayList<>();
    private HashMap<String, Vua> lienKetNhanVat = new HashMap<>();
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

    public void setTonThatDich(String tonThatDich) {
        this.tonThatDich = tonThatDich;
    }

    public void setTonThatTa(String tonThatTa) {
        this.tonThatTa = tonThatTa;
    }

    public String getTonThatDich() {
        return tonThatDich;
    }

    public String getTonThatTa() {
        return tonThatTa;
    }

    public void setNguyenNhan(String nguyenNhan) {
        this.nguyenNhan = nguyenNhan;
    }

    public void setNameRelativePerson(ArrayList<String> nameRelativePerson) {
        this.nameRelativePerson = nameRelativePerson;
    }

    public void setNameRelativePlace(ArrayList<String> nameRelativePlace) {
        this.nameRelativePlace = nameRelativePlace;
    }

    public void setNameRelativeDinasty(ArrayList<String> nameRelativeDinasty) {
        this.nameRelativeDinasty = nameRelativeDinasty;
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

    public ArrayList<String> getNameRelativePlace() {
        return nameRelativePlace;
    }

    public HashMap<String, Vua> getLienKetNhanVat() {
        return lienKetNhanVat;
    }

    public void setLienKetNhanVat(HashMap<String, Vua> lienKetNhanVat) {
        this.lienKetNhanVat = lienKetNhanVat;
    }

    public void setLienKetTrieuDai(HashMap<String, TrieuDai> lienKetTrieuDai) {
        this.lienKetTrieuDai = lienKetTrieuDai;
    }

    public HashMap<String, TrieuDai> getLienKetTrieuDai() {
        return lienKetTrieuDai;
    }

    public void linkNhanVat() throws IOException {
        FileReader fileReader = new FileReader("D:\\CodeViet\\OOP-Project\\src\\JSON_Data\\NhanVatLichSu.json");
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<Vua>>(){}.getType();
        ArrayList<Vua> listVua = gson.fromJson(fileReader,objectType);
        for(String str: nameRelativePerson){
            int i=0;
            while(i < listVua.size()){
                if(str.equals(listVua.get(i).getTen())){
                    lienKetNhanVat.putIfAbsent(str,listVua.get(i));
                    break;
                }
                i = i+1;
            }
        }
    }

    void hienThiLienKetNhanVat(){
        for(String name: lienKetNhanVat.keySet()){
            System.out.println("Ten nhan vat: "+name+"Data: "+lienKetNhanVat.get(name));
        }
    }

    public void linkTrieuDai() throws  IOException{
        FileReader fileReader = new FileReader("src/JSON_Data/trieuDai.json");
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<TrieuDai>>(){}.getType();
        ArrayList<TrieuDai> listTrieuDai = gson.fromJson(fileReader,objectType);
        for(String str: nameRelativeDinasty){
            int i=0;
            while(i< listTrieuDai.size()){
                if(str.equalsIgnoreCase(listTrieuDai.get(i).getTen())||listTrieuDai.get(i).getTen().contains(str)){
                    lienKetTrieuDai.putIfAbsent(str,listTrieuDai.get(i));
                    break;
                }
                i = i+1;
            }
        }
    }

    void hienThiLienKetTrieuDai(){
        for(String name: lienKetTrieuDai.keySet()){
            System.out.println("Ten trieu dai: "+name+"Data: "+lienKetTrieuDai.get(name));
        }
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
                "\"trieuDaiLienQuan\": "+"\""+nameRelativeDinasty+"\",\n"+
                "\"diaDiemLienQuan\": "+"\""+nameRelativePlace+"\"\n}";
    }
}
