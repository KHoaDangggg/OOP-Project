package CrawlData.CrawlDiTich;

import CrawlData.Info;

public class DiTichLichSu extends Info {
    private  String loaiDiTich;
    private  String diaDiem;
    private  String namCongNhan;
    private  String moTa;

    public DiTichLichSu(String ten) {
        super(ten);
    }

    public void setLoaiDiTich(String loaiDiTich) {
        this.loaiDiTich = loaiDiTich;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setNamCongNhan(String namCongNhan) {
        this.namCongNhan = namCongNhan;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLoaiDiTich() {
        return loaiDiTich;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public String getNamCongNhan() {
        return namCongNhan;
    }

    public String getMoTa() {
        return moTa;
    }
}
