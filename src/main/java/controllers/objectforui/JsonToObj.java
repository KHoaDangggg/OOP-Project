package controllers.objectforui;

import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.CrawlAnhHung.DanhNhan;
import crawlData.CrawlNhanVat.CrawlAnhHung.anhHungVuTrang;
import crawlData.CrawlNhanVat.NhaVat_VanSu.NhanVatVanSu;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import crawlData.CrawlNhanVat.trangnguyenbangnhan.src.DanhHieu;
import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@SuppressWarnings("ALL")
public class JsonToObj {

    public static ArrayList<SuKienChienTranh> listSuKien = new ArrayList<>();
    public static ArrayList<trieuDai> listTrieuDai = new ArrayList<>();
    public static ArrayList<Vua> listVua = new ArrayList<>();
    public static ArrayList<NhanVat> listNhanVat = new ArrayList<>();
    public static ArrayList<NhanVatVanSu> listNhanVatVanSu = new ArrayList<>();
    public static ArrayList<LeHoi> listLeHoi = new ArrayList<>();
    public static ArrayList<DiTichLichSu> listDiTich = new ArrayList<>();
    public static ArrayList<DanhNhan> listDanhNhan = new ArrayList<>();
    public static ArrayList<DanhHieu> listTrangNguyenBangNhan = new ArrayList<>();
    public static ArrayList<anhHungVuTrang> listAnhHungVuTrang = new ArrayList<>();

    public static ArrayList<SuKienChienTranh> listEvents = new ArrayList<>();

    public void generate() {
        //Get su kien
        JsonToObj1(listSuKien);
        //Get trieu dai
        JsonToObj2(listTrieuDai);
        //Get Vua
        JsonToObj3(listVua);
        //Get nhan vat lich su
        JsonToObj5(listNhanVat);
        //Get Le hoi
        JsonToObj4(listLeHoi);
        //Get Di tich lich su
        JsonToObj6("src/JSON_Data/DiTichLichSu.json", listDiTich);
        //Get danh nhan, trang nguyen, anh hung vu trang
        JsonToObj7();
        //Get nhan vat from Van Su
        JsonToObj8();

        JsonToObj9("src/JSON_Data/SuKien.json",listEvents);
        cleanSuKien();
        //Clean and merge nhanvatlichsu
        cleanNhanVat();

        //Clean all
        listNhanVat = removeDuplicates(listNhanVat);
        listLeHoi = removeDuplicates(listLeHoi);
        listDiTich = removeDuplicates(listDiTich);
        listSuKien = removeDuplicates(listSuKien);
        listTrieuDai = removeDuplicates(listTrieuDai);
        System.out.println("Clean all list successfully");
    }

    private void cleanNhanVat() {
        ArrayList<NhanVat> nhanVats = new ArrayList<>();
        nhanVats.addAll(listDanhNhan);
        nhanVats.addAll(listTrangNguyenBangNhan);
        nhanVats.addAll(listAnhHungVuTrang);
        nhanVats.addAll(listNhanVatVanSu);

        ArrayList<NhanVat> temp = new ArrayList<>();
        for (NhanVat nv : nhanVats) {
            for (NhanVat n : listNhanVat) {
                if (!nv.getTen().equals(n.getTen()) && !temp.contains(nv)) {
                    temp.add(nv);
                    //System.out.println(n.getTen());
                }
            }
        }
        listNhanVat.addAll(temp);
        System.out.println("Clean successfully");
    }

    public void cleanSuKien(){
        ArrayList<SuKienChienTranh> tongHopSuKien = new ArrayList<>();

        ArrayList<SuKienChienTranh> temp = new ArrayList<>();
        for(SuKienChienTranh suKien: listEvents){
            int count = 0;
            for(SuKienChienTranh sk: listSuKien){
                if(suKien.getTen().equalsIgnoreCase(sk.getTen())||sk.getTen().contains(suKien.getTen())){
                    count ++;
                }
            }
            if(count == 0) temp.add(suKien);
        }
        listSuKien.addAll(temp);
        System.out.println("Clean succcessfully");
    }

    private static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // return the new list
        return newList;
    }


    private FileReader reader(String path) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileReader;
    }

/*    public <T> void JsonToObj(String path, ArrayList<T> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<T>>() {
        }.getType();
        System.out.println(objectType.getTypeName());
        ArrayList<T> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }*/

    private void JsonToObj1(ArrayList<SuKienChienTranh> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader("src/JSON_Data/ChienTranh.json");
        Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>() {
        }.getType();
        ArrayList<SuKienChienTranh> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj2(ArrayList<trieuDai> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader("src/JSON_Data/trieuDai.json");
        Type objectType = new TypeToken<ArrayList<trieuDai>>() {
        }.getType();
        ArrayList<trieuDai> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj3(ArrayList<Vua> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader("src/JSON_Data/Vua.json");
        Type objectType = new TypeToken<ArrayList<Vua>>() {
        }.getType();
        ArrayList<Vua> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj4(ArrayList<LeHoi> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader("src/JSON_Data/LeHoi_Nguon_05.json");
        Type objectType = new TypeToken<ArrayList<LeHoi>>() {
        }.getType();
        ArrayList<LeHoi> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj5(ArrayList<NhanVat> list) {
        String input;
        try {
            input = Files.readString(Path.of("src/JSON_Data/NhanVatLichSu.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<JSONObject> objects = new ArrayList<>();
        JSONArray array = new JSONArray(input);
        for (int i = 0; i < array.length(); i++) objects.add(array.getJSONObject(i));

        for (JSONObject obj : objects) {
            HashMap<String, String> thongTin = new HashMap<>();
            String ten = obj.getString("ten");
            String mieuta = obj.getString("mieuTa");
            for (String key : obj.keySet()) {
                if (!key.equals("ten") && !key.equals("mieuTa"))
                    thongTin.put(key, obj.getString(key));
            }
            list.add(new NhanVatLichSu(ten, mieuta, thongTin));
        }
    }

    private void JsonToObj6(String path, ArrayList<DiTichLichSu> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<DiTichLichSu>>() {
        }.getType();
        ArrayList<DiTichLichSu> convertedList = gson.fromJson(fileReader, objectType);
        listDiTich.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj7() {
        Gson gson = new Gson();
        FileReader fileReader = reader("src/JSON_Data/danhNhanThoiDinh.json");
        Type objectType = new TypeToken<ArrayList<DanhNhan>>() {
        }.getType();
        ArrayList<DanhNhan> convertedList = gson.fromJson(fileReader, objectType);
        listDanhNhan.addAll(convertedList);

        fileReader = reader("src/JSON_Data/TrangNguyen&BangNhan.json");
        objectType = new TypeToken<ArrayList<DanhHieu>>() {
        }.getType();
        ArrayList<DanhHieu> convertedList1 = gson.fromJson(fileReader, objectType);
        listTrangNguyenBangNhan.addAll(convertedList1);

        fileReader = reader("src/JSON_Data/vuTrang.json");
        objectType = new TypeToken<ArrayList<anhHungVuTrang>>() {
        }.getType();
        ArrayList<anhHungVuTrang> convertedList2 = gson.fromJson(fileReader, objectType);
        listAnhHungVuTrang.addAll(convertedList2);
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj8() {
        String input;
        try {
            input = Files.readString(Path.of("src/JSON_Data/NhanVatLichSu_VanSu.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<JSONObject> objects = new ArrayList<>();
        JSONArray array = new JSONArray(input);
        for (int i = 0; i < array.length(); i++) objects.add(array.getJSONObject(i));

        for (JSONObject obj : objects) {
            LinkedHashMap<String, String> thongTin = new LinkedHashMap<>();
            String ten = obj.getString("ten");
            for (String key : obj.keySet()) {
                if (!key.equals("ten"))
                    thongTin.put(key, obj.getString(key));
            }
            listNhanVatVanSu.add(new NhanVatVanSu(ten, thongTin));
        }
    }

    public void JsonToObj9(String path, ArrayList<SuKienChienTranh>list){
        Gson  gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>(){}.getType();
        ArrayList<SuKienChienTranh> convertList = gson.fromJson(fileReader,objectType);
        list.addAll(convertList);
        System.out.println("Convert to obj successfull");

    }
}