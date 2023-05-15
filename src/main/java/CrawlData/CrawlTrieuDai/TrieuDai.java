package CrawlData.CrawlTrieuDai;


import java.util.ArrayList;
public class TrieuDai {
    String kinhDo;
    public String ten;
    public String namBatDau;
    public String namKetThuc;
    public String thoiKy;
    private ArrayList<King> Kings = new ArrayList<>();
    public void setTen(String ten) {
        this.ten = ten;
    }
    public void setNamBatDau(String namBatDau) {
        this.namBatDau = namBatDau;
    }
    public void setNamKetThuc(String namKetThuc) {
        this.namKetThuc = namKetThuc;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public void setKings(ArrayList<King> kings) {
        Kings = kings;
    }

    public void addKing(King element) {
        Kings.add(element);
    }
    public void setThoiKy(String thoiKy) {
        this.thoiKy = thoiKy;
    }
    public TrieuDai( String thoiKy, String ten) {
        this.ten = ten;
        this.thoiKy = thoiKy;
    }
    public TrieuDai( String ten) {
        this.ten = ten;
    }
    public String getTen() {
        return ten;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public String getThoiKy() {
        return thoiKy;
    }
    public ArrayList<King> getKings() {
        return Kings;
    }
    public String getNamBatDau() {
        return namBatDau;
    }
    public String getNamKetThuc() {
        return namKetThuc;
    }
    void cleanTen(String ten, boolean special) {
        if(special) {
            this.setTen(this.getTen().replaceAll("<br>", ""));
            this.setNamBatDau("Không rõ");
            this.setNamKetThuc("Không rõ");
            return;
        }
        if(ten.equals("&nbsp;")) {
            this.setTen("Không rõ");
            this.setNamBatDau("Không rõ");
            this.setNamKetThuc("Không rõ");
            return;
        }
        ten = ten.replaceAll("&nbsp;","");
        this.setTen(ten);
        if(ten.contains("<br>")) {
            String[] words = ten.split("<br>");
            for(int i=0; i<2; i++) {
                this.setTen(words[0]);
                if(words.length > 1) {
                    int start = words[1].indexOf("(");
                    int end = words[1].indexOf(")");
                    int mid = words[1].indexOf("-");
                    this.setNamBatDau(words[1].substring(start+1, mid-1));
                    this.setNamKetThuc(words[1].substring(mid+2, end));
                }
            }
        } else {
            this.setNamBatDau("Không rõ");
            this.setNamKetThuc("Không rõ");
        }
    }
    void cleanThoiKy(String thoiKy) {
        if(thoiKy.equals("&nbsp;")) {
            this.setThoiKy("Không rõ");
            return;
        }
        this.setThoiKy(thoiKy.replaceAll("&nbsp;", ""));
    }
    void cleanKing(ArrayList<King> kings) {
        for(King king: kings) {
            king.setName(king.getName().replaceAll("<br>", ""));
            king.setName(king.getName().replaceAll("&nbsp;", ""));
        }
        if(kings.get(0).getTime().equals("&nbsp;")) return;
        if(this.getNamBatDau().equals("Không rõ")) {
            String batDau = kings.get(0).getTime().split("-")[0].replaceAll(" ", "");
            if(batDau.contains("(")) {
                batDau = batDau.substring(0,batDau.indexOf("("));
            }
            this.setNamBatDau(batDau);
        }
        if(this.getNamKetThuc().equals("Không rõ")) {
            if(kings.get(kings.size() - 1).getTime().contains("-")) {
                String ketThuc = kings.get(kings.size() - 1).getTime().split("-")[1].replaceAll(" ", "");
                this.setNamKetThuc(ketThuc);
            } else {
                String ketThuc = kings.get(kings.size() - 1).getTime().split("-")[0].replaceAll(" ", "");
                this.setNamKetThuc(ketThuc);
            }
        }
    }

}
