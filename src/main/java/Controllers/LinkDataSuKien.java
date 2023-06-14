package Controllers;

import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class LinkDataSuKien implements Link{
    @Override
    public void link(Object obj) {
        Gson gson = new Gson();
        FileReader fileReader;
        if(obj instanceof SuKienChienTranh){
            HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();
            SuKienChienTranh event = (SuKienChienTranh) obj;
            try {
                fileReader = new FileReader("src/JSON_Data/trieuDai.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Type objectType = new TypeToken<ArrayList<TrieuDai>>() {}.getType();
            ArrayList<TrieuDai> listTrieuDai = gson.fromJson(fileReader,objectType);
            for(String str: event.getNameRelativeDinasty()){
                int i = 0;
                while(i < listTrieuDai.size()){
                    if(str.equalsIgnoreCase(listTrieuDai.get(i).getTen())||str.toLowerCase().contains(listTrieuDai.get(i).getTen().toLowerCase())||listTrieuDai.get(i).getTen().toLowerCase().contains(str.toLowerCase())){
                        lienKetTrieuDai.putIfAbsent(str,listTrieuDai.get(i));
                    }
                    i++;
                }
            }
            event.setLienKetTrieuDai(lienKetTrieuDai);



            HashMap<String, NhanVatLichSu> lienKetNhanVat = new HashMap<>();
            try{
                fileReader = new FileReader("src/JSON_Data/NhanVatLichSu.json");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            objectType = new TypeToken<ArrayList<NhanVatLichSu>>(){}.getType();
            ArrayList<NhanVatLichSu> listNhanVat = gson.fromJson(fileReader,objectType);
            for(String str: event.getNameRelativePerson()){
                int i = 0;
                while(i< listNhanVat.size()){
                    if(str.equalsIgnoreCase(listNhanVat.get(i).getTen())){
                        lienKetNhanVat.putIfAbsent(str,listNhanVat.get(i));
                    }
                    i++;
                }
            }
            event.setLienKetNhanVat(lienKetNhanVat);
        }
    }
}
