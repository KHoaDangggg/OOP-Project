package CrawlData.CrawlNhanVat;

public abstract class NhanVat {
    protected String ten;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public NhanVat(){

    }

    public NhanVat(String ten) {
        this.ten = ten;
    }
}
