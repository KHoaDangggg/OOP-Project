package model.nhanvat;

public class AnhHungVuTrang extends NhanVat {

    private final String namSinhNamMat;
    private final String danToc;
    private final String queQuan;
    private final String namPhong;
    private final String tieuSu;


    public String getNamSinhNamMat() {
        return namSinhNamMat;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getNamPhong() {
        return namPhong;
    }

    public String getTieuSu() {
        return tieuSu;
    }

    public AnhHungVuTrang(String ten, String namSinhNamMat, String danToc, String queQuan, String namPhong, String tieuSu) {
        super(ten);
        this.namSinhNamMat = namSinhNamMat;
        this.danToc = danToc;
        this.queQuan = queQuan;
        this.namPhong = namPhong;
        this.tieuSu = tieuSu;
    }
}