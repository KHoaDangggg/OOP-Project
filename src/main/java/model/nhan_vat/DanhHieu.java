package model.nhan_vat;

public class DanhHieu extends NhanVat {

    private final String namSinh;
    private final String namMat;
    private final String queQuan;
    private final String namDo;
    private String danhHieu;
    private final String doiVua;
    private final String ghiChu;


    public void setDanhHieu(String danhHieu){
        this.danhHieu = danhHieu;
    }

    public DanhHieu(String ten, String sinhmat, String queQuan, String namDo, String doi_vua, String ghi_chu) {
        super(ten);

        if(sinhmat.isBlank()) {
            this.namSinh = "không rõ";
            this.namMat = "không rõ";
        }
        else{
            String[] s = sinhmat.split("-", -1);
            if(s[0].contains("?")) this.namSinh = "không rõ";
            else this.namSinh = s[0];

            if(s[1].contains("?")) this.namMat = "không rõ";
            else this.namMat = s[1];
        }

        this.queQuan = queQuan;
        this.namDo = namDo;
        this.doiVua = doi_vua;
        this.ghiChu = clearGhichu(ghi_chu);
    }

    public String clearGhichu(String ghichu){
        if(ghichu.isBlank()) ghichu = "không có";
        else{
            if(ghichu.contains("[")){
                StringBuilder s = new StringBuilder(ghichu);
                int index1 = s.indexOf("[");
                int index2 = s.indexOf("]");
                s.delete(index1, index2+1);
                ghichu = s.toString();
            }
        }
        return ghichu;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public String getNamMat() {
        return namMat;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getNamDo() {
        return namDo;
    }

    public String getDanhHieu() {
        return danhHieu;
    }

    public String getDoiVua() {
        return doiVua;
    }

    public String getGhiChu() {
        return ghiChu;
    }
}
