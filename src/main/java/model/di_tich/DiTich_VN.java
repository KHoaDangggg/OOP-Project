package model.di_tich;

import java.util.LinkedHashMap;

public class DiTich_VN extends DiTich{
    private final String img;
    private final LinkedHashMap<String, String> thongTin;
    public DiTich_VN(String ten, String loaiDiTich, String diaDiem, LinkedHashMap<String, String> thongTin, String img) {
        super(ten, loaiDiTich, diaDiem);
        this.thongTin = thongTin;
        this.img = img;
    }


    public String getImg() {
        return img;
    }

    public LinkedHashMap<String, String> getThongTin() {
        return thongTin;
    }
}
