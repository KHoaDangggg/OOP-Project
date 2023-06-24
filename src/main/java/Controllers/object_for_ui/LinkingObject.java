package Controllers.object_for_ui;

import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

import java.util.ArrayList;
import java.util.HashMap;

public class LinkingObject implements LinkInterface {
    public ArrayList<SuKienChienTranh> listSuKien;
    public ArrayList<TrieuDai> listTrieuDai;
    public ArrayList<Vua> listVua;
    public ArrayList<NhanVat> listNhanVat;
    public ArrayList<LeHoi> listLeHoi;

    public LinkingObject(ArrayList<SuKienChienTranh> listSuKien, ArrayList<TrieuDai> listTrieuDai, ArrayList<Vua> listVua, ArrayList<NhanVat> listNhanVat, ArrayList<LeHoi> listLeHoi) {
        this.listSuKien = listSuKien;
        this.listTrieuDai = listTrieuDai;
        this.listVua = listVua;
        this.listNhanVat = listNhanVat;
        this.listLeHoi = listLeHoi;
    }

    public void link() {
        linkLeHoi(listLeHoi);
        System.out.println("Link le hoi thanh cong");
        linkSuKien(listSuKien);
        System.out.println("Link su kien thanh cong");
        linkNhanVat(listNhanVat);
        System.out.println("Link nhan vat thanh cong");
        linkTrieuDai(listTrieuDai);
        System.out.println("Link trieu dai thanh cong");
    }

    @Override
    public void linkLeHoi(ArrayList<LeHoi> arrLeHoi) {
        for (LeHoi leHoi : arrLeHoi) {
            HashMap<String, LeHoi> lienKetLeHoi = new HashMap<>();
            int count = 0;
            for (LeHoi lehoi : listLeHoi) {
                if(count==10) break;
                if (leHoi.getDiaDiem().equalsIgnoreCase(lehoi.getDiaDiem()) && !leHoi.getTen().equals(lehoi.getTen())) {
                    lienKetLeHoi.put(lehoi.getTen(), lehoi);
                    count++;
                }
            }
            leHoi.setLienKetLeHoi(lienKetLeHoi);
        }
    }

    @Override
    public void linkNhanVat(ArrayList<NhanVat> nhanVatLichSu) {

        //Link Nhan Vat voi Su Kien
        ArrayList<SuKienChienTranh> listSuKien = this.listSuKien;
        for (NhanVat nhanVat : nhanVatLichSu) {
            HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
            int i = 0;
            while (i < listSuKien.size()) {
                if (listSuKien.get(i).getNameRelativePerson().contains(nhanVat.getTen())) {
                    lienKetSuKien.putIfAbsent(listSuKien.get(i).getTen(), listSuKien.get(i));
                }
                i++;
            }
            nhanVat.setLienKetSuKien(lienKetSuKien);
        }

        //Link Nhan Vat voi Trieu Dai
        ArrayList<TrieuDai> listTrieuDai = this.listTrieuDai;
        for (NhanVat nhanVat : nhanVatLichSu) {
            HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();
            int i = 0;
            while (i < listTrieuDai.size()) {
                if (listTrieuDai.get(i).getKings().contains(nhanVat.getTen())) {
                    lienKetTrieuDai.putIfAbsent(listTrieuDai.get(i).getTen(), listTrieuDai.get(i));
                    break;
                }
                i++;
            }
            nhanVat.setLienKetTrieuDai(lienKetTrieuDai);
        }
    }

    @Override
    public void linkSuKien(ArrayList<SuKienChienTranh> suKienChienTranh) {

        //Link Su Kien voi Trieu Dai

        ArrayList<TrieuDai> listTrieuDai = this.listTrieuDai;
        for (SuKienChienTranh event : suKienChienTranh) {
            HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();
            for (String str : event.getNameRelativeDinasty()) {
                int i = 0;
                while (i < listTrieuDai.size()) {
                    if (str.equalsIgnoreCase(listTrieuDai.get(i).getTen()) || str.toLowerCase().contains(listTrieuDai.get(i).getTen().toLowerCase()) || listTrieuDai.get(i).getTen().toLowerCase().contains(str.toLowerCase())) {
                        lienKetTrieuDai.putIfAbsent(str, listTrieuDai.get(i));
                    }
                    i++;
                }
            }
            event.setLienKetTrieuDai(lienKetTrieuDai);
        }

        //Link Su Kien voi Nhan Vat
        ArrayList<NhanVat> listNhanVat = this.listNhanVat;
        for (SuKienChienTranh event : suKienChienTranh) {
            HashMap<String, NhanVat> lienKetNhanVat = new HashMap<>();
            for (String str : event.getNameRelativePerson()) {
                int i = 0;
                while (i < listNhanVat.size()) {
                    if (str.equalsIgnoreCase(listNhanVat.get(i).getTen())) {
                        lienKetNhanVat.putIfAbsent(str, listNhanVat.get(i));
                    }
                    i++;
                }
            }
            event.setLienKetNhanVat(lienKetNhanVat);
        }
    }


    @Override
    public void linkTrieuDai(ArrayList<TrieuDai> arrTrieuDai) {

        //Link Trieu Dai voi Su Kien
        ArrayList<SuKienChienTranh> listSuKien = this.listSuKien;
        for (TrieuDai trieuDai : arrTrieuDai) {
            HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
            int i = 0;
            while (i < listSuKien.size()) {
                for (String str : listSuKien.get(i).getNameRelativeDinasty()) {
                    if (trieuDai.getTen().equalsIgnoreCase(str.toLowerCase()) || trieuDai.getTen().toLowerCase().contains(str.toLowerCase()) || str.toLowerCase().contains(trieuDai.getTen().toLowerCase())) {
                        lienKetSuKien.putIfAbsent(listSuKien.get(i).getTen(), listSuKien.get(i));
                    }
                }
                i++;
            }
            trieuDai.setLienKetSuKien(lienKetSuKien);
        }

        //Link Trieu Dai voi Vua
        ArrayList<Vua> listVua = this.listVua;
        for (TrieuDai trieuDai : arrTrieuDai) {
            HashMap<String, Vua> lienKetVua = new HashMap<>();
            int i = 0;
            for (String k : trieuDai.getKings()) {
                while (i < listVua.size()) {
                    if (k.contains(listVua.get(i).getTen())) {
                        lienKetVua.putIfAbsent(listVua.get(i).getTen(), listVua.get(i));
                        //System.out.println(listVua.get(i).getTen());
                        break;
                    }
                    i++;
                }
            }
            trieuDai.setLienKetVua(lienKetVua);
        }
    }

}
