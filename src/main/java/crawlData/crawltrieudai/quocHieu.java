package crawlData.crawltrieudai;

public class quocHieu {
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

    public quocHieu(String ten, String namBatDau, String namKetThuc) {
        this.ten = ten;
        this.namBatDau = namBatDau;
        this.namKetThuc = namKetThuc;
    }

}
