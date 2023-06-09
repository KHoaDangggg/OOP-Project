package objectforui;

import model.ditich.DiTich;
import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.nhanvat.Vua;
import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;

import java.util.ArrayList;

public class GenerateUIObject {

    //Mang cac obj da duoc lien ket
    public static ArrayList<SuKienLichSu> listSuKien; //List cac su kien
    public static ArrayList<TrieuDai> listTrieuDai; //List cac trieu dai
    public static ArrayList<Vua> listVua; //List cac vi vua
    public static ArrayList<NhanVat> listNhanVat; //List cac nhan vat
    public static ArrayList<LeHoi> listLeHoi; //List cac le hoi
    public static ArrayList<DiTich> listDiTich; //List cac di tich lich su

    public static void gen() {
        //Json to obj converter
        JsonToObj gen = new JsonToObj();
        gen.generate();

        listSuKien = JsonToObj.listSuKien;
        listTrieuDai = JsonToObj.listTrieuDai;
        listVua = JsonToObj.listVua;
        listNhanVat = JsonToObj.listNhanVat;
        listLeHoi = JsonToObj.listLeHoi;
        listDiTich = JsonToObj.listDiTichLichSu;

        System.out.println("So Luong Du Lieu Lich Su da crawl: ");
        System.out.println("Su kien: " + listSuKien.size());
        System.out.println("Nhan Vat: " + listNhanVat.size());
        System.out.println("Le Hoi: " + listLeHoi.size());
        System.out.println("Di Tich Lich Su: " + listDiTich.size());
        System.out.println("Trieu Dai: " + listTrieuDai.size());

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

}
