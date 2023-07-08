package controllers;

import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.CrawlAnhHung.DanhNhan;
import crawlData.CrawlNhanVat.CrawlAnhHung.anhHungVuTrang;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import crawlData.CrawlNhanVat.trangnguyenbangnhan.src.DanhHieu;
import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class utils {
    public static ObservableList<trieuDai> trieuDaiList = FXCollections.observableArrayList();
    public static ObservableList<DiTichLichSu> diTichList = FXCollections.observableArrayList();
    public static ObservableList<LeHoi> leHoiList = FXCollections.observableArrayList();
    public static ObservableList<NhanVat> nhanVatList = FXCollections.observableArrayList();
    public static ObservableList<SuKienChienTranh> suKienList = FXCollections.observableArrayList();
    public static void main(String[] args) {

    }

    public static void readData() {
        Gson gson = new Gson();
        String trieuDaiPath = "src/JSON_Data/trieuDai.json";
        String leHoiPath = "src/JSON_Data/LeHoi_Nguon_05.json";
        String diTichPath = "src/JSON_Data/DiTichLichSu.json";
        String suKienPath = "src/JSON_Data/ChienTranh.json";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/JSON_Data/Vua.json"))) {
            Vua[] objects = gson.fromJson(reader, Vua[].class);
            nhanVatList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("src/JSON_Data/vuTrang.json"))) {
            anhHungVuTrang[] objects = gson.fromJson(reader, anhHungVuTrang[].class);
            nhanVatList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("src/JSON_Data/danhNhanThoiDinh.json"))) {
            DanhNhan[] objects = gson.fromJson(reader, DanhNhan[].class);
            nhanVatList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("src/JSON_Data/NhanVatLichSu.json"))) {
            NhanVatLichSu[] objects = gson.fromJson(reader, NhanVatLichSu[].class);
            nhanVatList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("src/JSON_Data/TrangNguyen&BangNhan.json"))) {
            DanhHieu[] objects = gson.fromJson(reader, DanhHieu[].class);
            nhanVatList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(trieuDaiPath))) {
            trieuDai[] objects = gson.fromJson(reader, trieuDai[].class);
            trieuDaiList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(leHoiPath))) {
            LeHoi[] objects = gson.fromJson(reader, LeHoi[].class);
            leHoiList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(suKienPath))) {
            SuKienChienTranh[] objects = gson.fromJson(reader, SuKienChienTranh[].class);
            suKienList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(diTichPath))) {
            DiTichLichSu[] objects = gson.fromJson(reader, DiTichLichSu[].class);
            diTichList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String removeVietnameseAccent(String str) {
        String nfc = Normalizer.normalize(str, Normalizer.Form.NFC);
        String regex = "[^\\p{L}0-9 ]";
        String noAccent = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS)
                .matcher(nfc)
                .replaceAll("");
        noAccent = noAccent.replaceAll("đ", "d")
                .replaceAll("[àáảãạâầấẩẫậăằắẳẵặ]", "a")
                .replaceAll("[èéẻẽẹêềếểễệ]", "e")
                .replaceAll("[ìíỉĩị]", "i")
                .replaceAll("[òóỏõọôồốổỗộơờớởỡợ]", "o")
                .replaceAll("[ùúủũụưừứửữự]", "u")
                .replaceAll("[ỳýỷỹỵ]", "y");
        return noAccent.toLowerCase().strip();
    }
}
