package CrawlData.CrawlTrieuDai;
import java.util.ArrayList;


public class TrieuDai {
    String moTa;
    String kinhDo;
    String quocHieu;
    String ten;
    String namBatDau;
    String namKetThuc;

    ArrayList<String> kings;

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public void setQuocHieu(String quocHieu) {
        this.quocHieu = quocHieu;
    }

    public TrieuDai( String ten, String batDau, String ketThuc, String moTa, ArrayList<String> kings)
    {
        this.ten = ten;
        this.namBatDau = batDau;
        this.namKetThuc = ketThuc;
        this.moTa = moTa;
        this.kings = kings;
    }

    public String getQuocHieu() {
        return quocHieu;
    }

    public String getTen() {
        return ten;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public String getNamBatDau() {
        return namBatDau;
    }

    public String getNamKetThuc() {
        return namKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public ArrayList<String> getKings() {
        return kings;
    }

}
