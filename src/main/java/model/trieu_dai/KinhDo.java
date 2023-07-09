package model.trieu_dai;


public class KinhDo {
    public String ten;
    public String namBatDau;
    public String namKetThuc;
    public String trieuDai;
    public String getTen() {
        return ten;
    }

    public String getNamBatDau() {
        return namBatDau;
    }

    public String getNamKetThuc() {
        return namKetThuc;
    }

    public void setNamBatDau(String namBatDau) {
        this.namBatDau = namBatDau;
    }

    public void setNamKetThuc(String namKetThuc) {
        this.namKetThuc = namKetThuc;
    }

    public KinhDo(String ten, String nam, String trieuDai) {
        this.ten = ten;
        this.trieuDai = trieuDai;
        if(nam.contains("-")) {
            int mid = nam.lastIndexOf('-');
            this.setNamBatDau(nam.substring(0,mid-1).replaceAll(" ",""));
            String namKetThuc = nam.substring(mid+2).replaceAll(" ","");
            if(namKetThuc.equals("nay")) {
                this.setNamKetThuc("2023");
            }
            else {
                this.setNamKetThuc(namKetThuc);
            }
        }
        else {
            this.setNamBatDau(nam.replaceAll(" ",""));
            this.setNamKetThuc(nam.replaceAll(" ",""));
        }
        if(nam.contains("Thế kỷ")) {
            this.setNamBatDau("701");
            this.setNamKetThuc("799");
        }
    }

}

