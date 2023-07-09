package crawl_data.model.di_tich;

import crawl_data.DuLieuLichSu;

public class DiTich extends DuLieuLichSu {
    private  String loaiDiTich;
    private  String diaDiem;

    public DiTich(String ten) {
        super(ten);
    }

    public DiTich(String ten, String loaiDiTich, String diaDiem) {
        super(ten);
        this.loaiDiTich = loaiDiTich;
        this.diaDiem = diaDiem;
    }

    public String getLoaiDiTich() {
        return loaiDiTich;
    }


    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
