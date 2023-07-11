package model.ditich;

import java.util.LinkedHashMap;

public class DiTichVN extends DiTich {
    private final String img;
    private final LinkedHashMap<String, String> thongTin;

    public DiTichVN(String ten, String loaiDiTich, String diaDiem, LinkedHashMap<String, String> thongTin, String img) {
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
