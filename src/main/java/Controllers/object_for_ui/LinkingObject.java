package Controllers.object_for_ui;

import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

import java.util.ArrayList;
import java.util.HashMap;

public class LinkingObject implements LinkInterface {
    public ArrayList<SuKienChienTranh> listSuKien;
    public ArrayList<TrieuDai> listTrieuDai;
    public ArrayList<Vua> listVua;
    public ArrayList<NhanVatLichSu> listNhanVat;
    public ArrayList<LeHoi> listLeHoi;

    public LinkingObject(ArrayList<SuKienChienTranh> listSuKien, ArrayList<TrieuDai> listTrieuDai, ArrayList<Vua> listVua, ArrayList<NhanVatLichSu> listNhanVat, ArrayList<LeHoi> listLeHoi) {
        this.listSuKien = listSuKien;
        this.listTrieuDai = listTrieuDai;
        this.listVua = listVua;
        this.listNhanVat = listNhanVat;
        this.listLeHoi = listLeHoi;
    }

    public void link() {
        linkLeHoi(listLeHoi);
        linkSuKien(listSuKien);
        linkNhanVat(listNhanVat);
        linkTrieuDai(listTrieuDai);
    }

    @Override
    public void linkLeHoi(ArrayList<LeHoi> arrLeHoi) {
        HashMap<String, LeHoi> lienKetLeHoi = new HashMap<>();
        for (LeHoi leHoi : arrLeHoi) {
            for (LeHoi lehoi : listLeHoi) {
                if (leHoi.getDiaDiem().equalsIgnoreCase(lehoi.getDiaDiem())) {
                    lienKetLeHoi.putIfAbsent(lehoi.getTen(), lehoi);
                }
            }
            leHoi.setLienKetLeHoi(lienKetLeHoi);
        }
    }

    @Override
    public void linkNhanVat(ArrayList<NhanVatLichSu> nhanVatLichSu) {

        //Link Nhan Vat voi Su Kien
        HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
        ArrayList<SuKienChienTranh> listSuKien = this.listSuKien;
        for (NhanVatLichSu nhanVat : nhanVatLichSu) {
            int i = 0;
            while (i < listSuKien.size()) {
                if (listSuKien.get(i).getNameRelativePerson().contains(nhanVat.getTen())) {
                    lienKetSuKien.putIfAbsent(listSuKien.get(i).getTenSuKien(), listSuKien.get(i));
                }
                i++;
            }
            nhanVat.setLienKetSuKien(lienKetSuKien);
        }

        //Link Nhan Vat voi Trieu Dai
        HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();
        ArrayList<TrieuDai> listTrieuDai = this.listTrieuDai;
        for (NhanVatLichSu nhanVat : nhanVatLichSu) {
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
        HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();
        ArrayList<TrieuDai> listTrieuDai = this.listTrieuDai;
        for (SuKienChienTranh event : suKienChienTranh) {
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
        HashMap<String, NhanVatLichSu> lienKetNhanVat = new HashMap<>();
        ArrayList<NhanVatLichSu> listNhanVat = this.listNhanVat;
        for (SuKienChienTranh event : suKienChienTranh) {
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
        HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
        ArrayList<SuKienChienTranh> listSuKien = this.listSuKien;
        for (TrieuDai trieuDai : arrTrieuDai) {
            int i = 0;
            while (i < listSuKien.size()) {
                for (String str : listSuKien.get(i).getNameRelativeDinasty()) {
                    if (trieuDai.getTen().equalsIgnoreCase(str.toLowerCase()) || trieuDai.getTen().toLowerCase().contains(str.toLowerCase()) || str.toLowerCase().contains(trieuDai.getTen().toLowerCase())) {
                        lienKetSuKien.putIfAbsent(listSuKien.get(i).getTenSuKien(), listSuKien.get(i));
                    }
                }
                i++;
            }
            trieuDai.setLienKetSuKien(lienKetSuKien);
        }

        //Link Trieu Dai voi Vua
        HashMap<String, Vua> lienKetVua = new HashMap<>();
        ArrayList<Vua> listVua = this.listVua;
        for (TrieuDai trieuDai : arrTrieuDai) {
            int i = 0;
            while (i < listVua.size()) {
                if (trieuDai.getKings().contains(listVua.get(i).getTen())) {
                    lienKetVua.putIfAbsent(listVua.get(i).getTen(), listVua.get(i));
                }
                i++;
            }
            trieuDai.setLienKetVua(lienKetVua);
        }
    }

}
