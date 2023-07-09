package CrawlData.CrawlDiTich;


public class DiTichLichSu extends DiTich {
    private  String namCongNhan;
    private  String moTa;

    public DiTichLichSu(String ten) {
        super(ten);
    }

    public DiTichLichSu(String ten, String loaiDiTich, String diaDiem, String namCongNhan, String moTa) {
        super(ten, loaiDiTich, diaDiem);
        this.namCongNhan = namCongNhan;
        this.moTa = moTa;
    }

    public void setNamCongNhan(String namCongNhan) {
        this.namCongNhan = namCongNhan;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }


    public String getNamCongNhan() {
        return namCongNhan;
    }

    public String getMoTa() {
        return moTa;
    }
}
