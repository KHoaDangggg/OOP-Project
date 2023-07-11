package model.nhanvat;

public class Vua extends NhanVat {
    private final String img;
    private final String mieuHieu;
    private final String thuyHieu;
    private final String nienHieu;
    private final String tenHuy;
    private final String theThu;
    private final String namBatDauTriVi;
    //private String middle;
    private final String namKetThucTriVi;

    private String namSinh;
    private String namMat;

    public Vua(String img, String name, String mieu_hieu, String thuyhieu, String nienhieu, String tenhuy, String thethu, String start, String end) {
        super(name);
        this.img = clearImg(img);
        this.mieuHieu = clear(mieu_hieu);
        this.thuyHieu = clear(thuyhieu);
        this.nienHieu = clear(nienhieu);
        this.tenHuy = clear(tenhuy);
        this.theThu = clear(thethu);
        this.namBatDauTriVi = clear(start);
        //this.middle = clear(middle);
        this.namKetThucTriVi = clear(end);
    }

    public String clearImg(String img) {
        if (img.equalsIgnoreCase("")) return null;
        return img;
    }

    public String clear(String str) {

        boolean modified = true;
        while (modified) {
            int index1 = str.indexOf("(");
            int index2 = str.indexOf(")");
            if (index1 >= 0 && index2 >= 0) {
                String s = str.substring(index1, index2 + 1);
                str = str.replace(s, "");
            } else modified = false;
        }

        modified = true;
        while (modified) {
            int index1 = str.indexOf("[");
            int index2 = str.indexOf("]");
            if (index1 >= 0 && index2 >= 0) {
                String s = str.substring(index1, index2 + 1);
                str = str.replace(s, "");
            } else modified = false;
        }

        return str;
    }

    public String getMieuHieu() {
        return mieuHieu;
    }

    public String getImg() {
        return img;
    }

    public String getThuyHieu() {
        return thuyHieu;
    }

    public String getNienHieu() {
        return nienHieu;
    }

    public String getTenHuy() {
        return tenHuy;
    }

    public String getTheThu() {
        return theThu;
    }

    public String getNamBatDauTriVi() {
        return namBatDauTriVi;
    }

    public String getNamKetThucTriVi() {
        return namKetThucTriVi;
    }
}
