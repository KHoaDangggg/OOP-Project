package model.di_tich;


public class DiTichLichSu extends DiTich {
    private final String namCongNhan;
    private final String moTa;

    public DiTichLichSu(String ten, String loaiDiTich, String diaDiem, String namCongNhan, String moTa) {
        super(ten, loaiDiTich, diaDiem);
        this.namCongNhan = namCongNhan;
        this.moTa = moTa;
    }

    public String getNamCongNhan() {
        return namCongNhan;
    }

    public String getMoTa() {
        return moTa;
    }
}
