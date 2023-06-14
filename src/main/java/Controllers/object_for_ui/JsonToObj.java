package Controllers.object_for_ui;

import CrawlData.CrawlDiTich.DiTichLichSu;
import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
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
import java.util.HashMap;

public class JsonToObj {

    public static ArrayList<SuKienChienTranh> listSuKien = new ArrayList<>();
    public static ArrayList<TrieuDai> listTrieuDai = new ArrayList<>();
    public static ArrayList<Vua> listVua = new ArrayList<>();
    public static ArrayList<NhanVatLichSu> listNhanVat = new ArrayList<>();
    public static ArrayList<LeHoi> listLeHoi = new ArrayList<>();
    public static ArrayList<DiTichLichSu> listDiTich = new ArrayList<>();

    public void generate() {
        //Get su kien
        JsonToObj1("src/JSON_Data/ChienTranh.json", listSuKien);
        //Get trieu dai
        JsonToObj2("src/JSON_Data/trieuDai.json", listTrieuDai);
        //Get Vua
        JsonToObj3("src/JSON_Data/Vua.json", listVua);
        //Get nhan vat lich su
        JsonToObj5("src/JSON_Data/NhanVatLichSu.json", listNhanVat);
        //Get Le hoi
        JsonToObj4("src/JSON_Data/LeHoi_Nguon_05.json", listLeHoi);
        //Get Di tich lich su
        JsonToObj6("src/JSON_Data/DiTichLichSu.json", listDiTich);
    }


    public FileReader reader(String path) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileReader;
    }

    public <T> void JsonToObj(String path, ArrayList<T> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<T>>() {
        }.getType();
        System.out.println(objectType.getTypeName());
        ArrayList<T> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    public void JsonToObj1(String path, ArrayList<SuKienChienTranh> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>() {
        }.getType();
        ArrayList<SuKienChienTranh> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    public void JsonToObj2(String path, ArrayList<TrieuDai> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<TrieuDai>>() {
        }.getType();
        ArrayList<TrieuDai> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    public void JsonToObj3(String path, ArrayList<Vua> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<Vua>>() {
        }.getType();
        ArrayList<Vua> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    public void JsonToObj4(String path, ArrayList<LeHoi> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<LeHoi>>() {
        }.getType();
        ArrayList<LeHoi> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }

    public void JsonToObj5(String path, ArrayList<NhanVatLichSu> list) {
        String input;
        try {
            input = Files.readString(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<JSONObject> objects = new ArrayList<>();
        JSONArray array = new JSONArray(input);
        for(int i = 0; i<array.length(); i++) objects.add(array.getJSONObject(i));

        for (JSONObject obj : objects) {
            HashMap<String, String> thongTin = new HashMap<>();
            String ten = obj.getString("ten");
            String mieuta = obj.getString("mieuTa");
            for (String key : obj.keySet()) {
                if (key.equals("ten") || key.equals("mieuTa")) continue;
                else {
                    thongTin.put(key, obj.getString(key));
                }
            }
            list.add(new NhanVatLichSu(ten, mieuta, thongTin));
        }
    }

    public void JsonToObj6(String path, ArrayList<DiTichLichSu> list) {
        Gson gson = new Gson();
        FileReader fileReader = reader(path);
        Type objectType = new TypeToken<ArrayList<DiTichLichSu>>() {
        }.getType();
        ArrayList<DiTichLichSu> convertedList = gson.fromJson(fileReader, objectType);
        list.addAll(convertedList);
        System.out.println("Convert to obj successful!");
    }


}
