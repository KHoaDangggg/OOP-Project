package model.su_kien;

import model.DuLieuLichSu;
import model.nhan_vat.NhanVat;
import model.trieu_dai.TrieuDai;

import java.util.ArrayList;
import java.util.HashMap;

public class SuKienLichSu extends DuLieuLichSu {
    public SuKienLichSu(String ten) {
        super(ten);
    }

    private String tenSuKien;
    private String thoiGian;
    private String diaDiem;
    private String ketQua;
    private ArrayList<String> nameRelativePerson;
    private ArrayList<String> nameRelativeDinasty;
    private HashMap<String, NhanVat> lienKetNhanVat = new HashMap<>();
    private HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();

    @Override
    public void setTen(String tenSuKien) {
        this.tenSuKien = tenSuKien;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
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

    @Override
    public String getTen() {
        return tenSuKien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getKetQua() {
        return ketQua;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public ArrayList<String> getNameRelativeDinasty() {
        return nameRelativeDinasty;
    }

    public ArrayList<String> getNameRelativePerson() {
        return nameRelativePerson;
    }

    public HashMap<String, NhanVat> getLienKetNhanVat() {
        return lienKetNhanVat;
    }

    public HashMap<String, TrieuDai> getLienKetTrieuDai() {
        return lienKetTrieuDai;
    }

    public void createObject() {
        setThoiGian("Không rõ");
        setDiaDiem("Không rõ");
        setKetQua("Không rõ");
    }
}
