package CrawlLeHoi.LeHoi_Nguon_01;

public class LeHoi {
    private String thoiGian;
    private String diaDiem;
    private String tenLeHoi;
    private String namToChucDauTien;
    private String nhanVatLQ;
    private String ghiChu;

    public LeHoi(String thoiGian, String diaDiem, String tenLeHoi, String namToChucDauTien, String nhanVatLQ, String ghiChu) {
        this.thoiGian = thoiGian;
        this.diaDiem = diaDiem;
        this.tenLeHoi = tenLeHoi;
        this.namToChucDauTien = namToChucDauTien;
        this.nhanVatLQ = nhanVatLQ;
        this.ghiChu = ghiChu;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getTenLeHoi() {
        return tenLeHoi;
    }

    public void setTenLeHoi(String tenLeHoi) {
        this.tenLeHoi = tenLeHoi;
    }

    public String getNamToChucDauTien() {
        return namToChucDauTien;
    }

    public void setNamToChucDauTien(String namToChucDauTien) {
        this.namToChucDauTien = namToChucDauTien;
    }

    public String getNhanVatLQ() {
        return nhanVatLQ;
    }

    public void setNhanVatLQ(String nhanVatLQ) {
        this.nhanVatLQ = nhanVatLQ;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "Thoi gian: " + thoiGian + "\n" +
                "Dia diem: " + diaDiem + "\n" +
                "Ten Le hoi: " + tenLeHoi + "\n" +
                "Nam to chuc dau tien: " + namToChucDauTien + "\n" +
                "Cac nhan vat lien quan: " + nhanVatLQ + "\n" +
                "Ghi chu: " + ghiChu + "\n";
    }
}
