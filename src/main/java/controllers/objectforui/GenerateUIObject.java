package controllers.objectforui;

import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.NhanVat;

import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;

import java.util.ArrayList;

public class GenerateUIObject {

    //Mang cac obj da duoc lien ket
    public static ArrayList<SuKienChienTranh> listSuKien; //List cac su kien
    public static ArrayList<trieuDai> listTrieuDai; //List cac trieu dai
    public static ArrayList<Vua> listVua; //List cac vi vua
    public static ArrayList<NhanVat> listNhanVat; //List cac nhan vat
    public static ArrayList<LeHoi> listLeHoi; //List cac le hoi
    public static ArrayList<DiTichLichSu> listDiTich; //List cac di tich lich su

    public static void gen(){
        //Json to obj converter
        JsonToObj gen = new JsonToObj();
        gen.generate();

        listSuKien = JsonToObj.listSuKien;
        listTrieuDai = JsonToObj.listTrieuDai;
        listVua = JsonToObj.listVua;
        listNhanVat = JsonToObj.listNhanVat;
        listLeHoi = JsonToObj.listLeHoi;
        listDiTich = JsonToObj.listDiTich;

        System.out.println(listNhanVat.size());

        //Linking obj after conversion
        LinkingObject linkingObject = new LinkingObject(
                JsonToObj.listSuKien,
                JsonToObj.listTrieuDai,
                JsonToObj.listVua,
                JsonToObj.listNhanVat,
                JsonToObj.listLeHoi
        );
        linkingObject.link();
    }

    //Test obj
/*    public static void main(String[] args) {
        JsonToObj gen = new JsonToObj();
        gen.generate();
        //System.out.println(GenerateObject.listLeHoi.get(10).getTen());
        LinkingObject linkingObject = new LinkingObject(
                JsonToObj.listSuKien,
                JsonToObj.listTrieuDai,
                JsonToObj.listVua,
                JsonToObj.listNhanVat,
                JsonToObj.listLeHoi
        );
        linkingObject.link();
        System.out.println(JsonToObj.listNhanVat.get(20).getLienKetTrieuDai());
        System.out.println(JsonToObj.listNhanVat.get(20).getThongTin());

    }*/
}
