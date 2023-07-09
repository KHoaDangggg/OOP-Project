package Controllers.object_for_ui;

import CrawlData.CrawlDiTich.DiTich;
import CrawlData.CrawlDiTich.DiTichLichSu;
import CrawlData.CrawlDiTich.DiTich_VN;
import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.CrawlAnhHung.DanhNhan;
import CrawlData.CrawlNhanVat.CrawlAnhHung.anhHungVuTrang;
import CrawlData.CrawlNhanVat.NhaVat_VanSu.NhanVatVanSu;
import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlNhanVat.trangnguyenbangnhan.src.DanhHieu;
import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

@SuppressWarnings("ALL")
public class JsonToObj {

    public static ArrayList<SuKienChienTranh> listSuKien = new ArrayList<>();
    public static ArrayList<TrieuDai> listTrieuDai = new ArrayList<>();
    public static ArrayList<Vua> listVua = new ArrayList<>();
    public static ArrayList<NhanVat> listNhanVat = new ArrayList<>();
    public static ArrayList<NhanVatVanSu> listNhanVatVanSu = new ArrayList<>();
    public static ArrayList<LeHoi> listLeHoi = new ArrayList<>();
    public static ArrayList<DiTich> listDiTichLichSu = new ArrayList<>();
    public static ArrayList<DanhNhan> listDanhNhan = new ArrayList<>();
    public static ArrayList<DanhHieu> listTrangNguyenBangNhan = new ArrayList<>();
    public static ArrayList<anhHungVuTrang> listAnhHungVuTrang = new ArrayList<>();
    public static ArrayList<DiTich_VN> listDiTichVN = new ArrayList<>();

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
        JsonToObj6("src/JSON_Data/DiTichLichSu.json", listDiTichLichSu);
        JsonToObj10();
        cleanDiTich();
        //Get danh nhan, trang nguyen, anh hung vu trang
        JsonToObj7();
        //Get nhan vat from Van Su
        JsonToObj8();
        //Get Su Kien
        JsonToObj9("src/JSON_Data/SuKien.json",listEvents);
        cleanSuKien();
        //Clean and merge nhanvatlichsu
        cleanNhanVat();

        //Clean all
        listNhanVat = removeDuplicates(listNhanVat);
        listLeHoi = removeDuplicates(listLeHoi);
        listDiTichLichSu = removeDuplicates(listDiTichLichSu);
        listSuKien = removeDuplicates(listSuKien);
        listTrieuDai = removeDuplicates(listTrieuDai);
        System.out.println("Clean all list successfully");
    }

    private void cleanDiTich() {
        listDiTichLichSu.addAll(listDiTichVN);
        listDiTichLichSu.sort(new Comparator<DiTich>() {
            @Override
            public int compare(DiTich o1, DiTich o2) {
                return o1.getTen().compareToIgnoreCase(o2.getTen());
            }
        });
        ArrayList<DiTich> count = new ArrayList<>();
        for(int i = 0; i<listDiTichLichSu.size()-1; i++){
            if(listDiTichLichSu.get(i).getTen().equalsIgnoreCase(listDiTichLichSu.get(i+1).getTen())) count.add(listDiTichLichSu.get(i));
        }
        listDiTichLichSu.removeAll(count);
        System.out.println("Clean successfully");
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

    private void JsonToObj2(ArrayList<TrieuDai> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader("src/JSON_Data/trieuDai.json");
        Type objectType = new TypeToken<ArrayList<TrieuDai>>() {
        }.getType();
        ArrayList<TrieuDai> convertedList = gson.fromJson(fileReader, objectType);
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
        System.out.println("Convert to obj successful!");
    }

    private void JsonToObj6(String path, ArrayList<DiTich> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<DiTichLichSu>>() {
        }.getType();
        ArrayList<DiTichLichSu> convertedList = gson.fromJson(fileReader, objectType);
        listDiTichLichSu.addAll(convertedList);
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
        System.out.println("Convert to obj successful!");
    }

    public void JsonToObj9(String path, ArrayList<SuKienChienTranh>list){
        Gson  gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>(){}.getType();
        ArrayList<SuKienChienTranh> convertList = gson.fromJson(fileReader,objectType);
        list.addAll(convertList);
        System.out.println("Convert to obj successfull");
    }

    private void JsonToObj10() {
        String input;
        try {
            input = Files.readString(Path.of("src/JSON_Data/DiTichVN.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<JSONObject> objects = new ArrayList<>();
        JSONArray array = new JSONArray(input);
        for (int i = 0; i < array.length(); i++) objects.add(array.getJSONObject(i));

        for (JSONObject obj : objects) {
            LinkedHashMap<String, String> thongTin = new LinkedHashMap<>();
            ArrayList<String> keySets = new ArrayList<>();
            keySets.addAll(obj.keySet());
            String ten = null;
            String img= null;
            String loaiDiTich = null;
            String diaDiem = null;
            try{
                ten = obj.getString("ten");
                String s = obj.getString("img");
                String s1 = s.replaceAll("\\\\", "/");
                img = "http://ditich.vn" + s1;
                System.out.println(img);
                loaiDiTich = obj.getString("loaiDiTich");
                //diaDiem = obj.getString("diaDiem");
            }catch (Exception e){
                e.printStackTrace();
            }
            keySets.remove("ten");
            keySets.remove("img");
            keySets.remove("loaiDiTich");
            keySets.remove("diaDiem");
            for (String key : keySets) thongTin.put(key, obj.getString(key));
            listDiTichVN.add(new DiTich_VN(ten, loaiDiTich, diaDiem, thongTin, img));
        }
        System.out.println("Convert to obj successfull");
    }
}
