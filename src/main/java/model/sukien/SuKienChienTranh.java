package model.sukien;

public class SuKienChienTranh extends SuKienLichSu {
    private String pheTa;
    private String pheDich;
    private String chiHuyPheTa;
    private String chiHuyPheDich;
    private String lucLuongPheTa;
    private String lucLuongPheDich;
    private String tonThatTa;
    private String tonThatDich;
    private String nguyenNhan;

    public SuKienChienTranh(String ten) {
        super(ten);
    }

    public void setPheTa(String pheTa) {
        this.pheTa = pheTa;
    }

    public void setPheDich(String pheDich) {
        this.pheDich = pheDich;
    }

    public void setChiHuyPheTa(String chiHuyPheTa) {
        this.chiHuyPheTa = chiHuyPheTa;
    }

    public void setChiHuyPheDich(String chiHuyPheDich) {
        this.chiHuyPheDich = chiHuyPheDich;
    }

    public void setLucLuongPheTa(String lucLuongPheTa) {
        this.lucLuongPheTa = lucLuongPheTa;
    }

    public void setLucLuongPheDich(String lucLuongPheDich) {
        this.lucLuongPheDich = lucLuongPheDich;
    }

    public void setTonThatTa(String tonThatTa) {
        this.tonThatTa = tonThatTa;
    }

    public void setTonThatDich(String tonThatDich) {
        this.tonThatDich = tonThatDich;
    }

    public void setNguyenNhan(String nguyenNhan) {
        this.nguyenNhan = nguyenNhan;
    }
    public String getPheTa() {
        return pheTa;
    }

    public String getPheDich() {
        return pheDich;
    }

    public String getChiHuyPheTa() {
        return chiHuyPheTa;
    }

    public String getChiHuyPheDich() {
        return chiHuyPheDich;
    }

    public String getLucLuongPheTa() {
        return lucLuongPheTa;
    }

    public String getLucLuongPheDich() {
        return lucLuongPheDich;
    }

    public String getTonThatDich() {
        return tonThatDich;
    }

    public String getTonThatTa() {
        return tonThatTa;
    }
    public String getNguyenNhan() {
        return nguyenNhan;
    }

    public void createObject(){
        setThoiGian("Không rõ"); setDiaDiem("Không rõ"); setNguyenNhan("Không rõ");
        setPheTa("Không rõ"); setPheDich("Không rõ"); setChiHuyPheDich("Không rõ");
        setChiHuyPheTa("Không rõ"); setTonThatTa("Không rõ"); setTonThatDich("Không rõ");
        setKetQua("Không rõ");setLucLuongPheTa("Không rõ"); setLucLuongPheDich("Không rõ");

    }


    public String toString(){
        return "\n{\"tenSuKien\": "+"\""+ this.getTen() +"\",\n"+
                "\"thoiGian\": "+"\""+this.getThoiGian()+"\",\n"+
                "\"diaDiem\": "+"\""+this.getDiaDiem()+"\",\n"+
                "\"nguyenNhan\": "+"\""+nguyenNhan+"\",\n"+
                "\"pheTa\": "+"\""+pheTa+"\",\n"+
                "\"pheDich\": "+"\""+pheDich+"\",\n"+
                "\"chiHuyPheTa\": "+"\""+chiHuyPheTa+"\",\n"+
                "\"chiHuyPheDich\": "+"\""+chiHuyPheDich+"\",\n"+
                "\"tonThatPheTa\": "+"\""+tonThatTa+"\",\n"+
                "\"tonThatPheDich\": "+"\""+tonThatDich+"\",\n"+
                "\"ketQua\": "+"\""+this.getKetQua()+"\",\n"+
                "\"nhanVatLienQuan\": "+"\""+this.getNameRelativePerson()+"\",\n"+
                "\"diaDiemLienQuan\": "+"\""+this.getNameRelativeDinasty()+"\"\n}";
    }
}
