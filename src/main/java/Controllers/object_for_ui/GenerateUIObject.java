package Controllers.object_for_ui;

import CrawlData.CrawlDiTich.DiTichLichSu;
import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

import java.util.ArrayList;

public class GenerateUIObject {


    //Mang cac obj da duoc lien ket
    public static ArrayList<SuKienChienTranh> listSuKien = JsonToObj.listSuKien; //List cac su kien
    public static ArrayList<TrieuDai> listTrieuDai = JsonToObj.listTrieuDai; //List cac trieu dai
    public static ArrayList<Vua> listVua = JsonToObj.listVua; //List cac vi vua
    public static ArrayList<NhanVatLichSu> listNhanVatLichSu = JsonToObj.listNhanVat; //List cac nhan vat
    public static ArrayList<LeHoi> listLeHoi = JsonToObj.listLeHoi; //List cac le hoi
    public static ArrayList<DiTichLichSu> listDiTich = JsonToObj.listDiTich; //List cac di tich lich su
    public static ArrayList<NhanVat> listNhanVat = new ArrayList<>();

    public static void gen() {
        //Json to obj converter
        JsonToObj gen = new JsonToObj();
        gen.generate();

        //Linking obj after conversion
        LinkingObject linkingObject = new LinkingObject(
                JsonToObj.listSuKien,
                JsonToObj.listTrieuDai,
                JsonToObj.listVua,
                JsonToObj.listNhanVat,
                JsonToObj.listLeHoi
        );
        linkingObject.link();
        listNhanVat.addAll(listVua);
        listNhanVat.addAll(listNhanVatLichSu);

    }

}
