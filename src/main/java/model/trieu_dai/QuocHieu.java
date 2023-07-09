package model.trieu_dai;

public class QuocHieu {
    String ten;
    String namBatDau;
    String namKetThuc;

    public String getTen() {
        return ten;
    }

    public String getNamBatDau() {
        return namBatDau;
    }

    public String getNamKetThuc() {
        return namKetThuc;
    }

    public void setNamKetThuc(String namKetThuc) {
        this.namKetThuc = namKetThuc;
    }

    public QuocHieu(String ten, String namBatDau, String namKetThuc) {
        this.ten = ten;
        this.namBatDau = namBatDau;
        this.namKetThuc = namKetThuc;
    }

}
