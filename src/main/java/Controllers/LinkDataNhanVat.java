package Controllers;

import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class LinkDataNhanVat implements Link{
    @Override
    public void link(Object obj) {
        Gson gson = new Gson();
        FileReader fileReader;
        if(obj instanceof NhanVatLichSu){
            NhanVatLichSu nhanVat = (NhanVatLichSu) obj;
            HashMap<String, SuKienChienTranh> lienKetSuKien = new HashMap<>();
            try {
                fileReader = new FileReader("src/JSON_Data/ChienTranh.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>(){}.getType();
            ArrayList<SuKienChienTranh> listSuKien = gson.fromJson(fileReader,objectType);
            int i = 0;
            while(i < listSuKien.size()){
                if(listSuKien.get(i).getNameRelativePerson().contains(nhanVat.getTen())){
                    lienKetSuKien.putIfAbsent(listSuKien.get(i).getTenSuKien(),listSuKien.get(i));
                }
                i++;
            }
            nhanVat.setLienKetSuKien(lienKetSuKien);



            HashMap<String, TrieuDai> lienKetTrieuDai = new HashMap<>();
            try {
                fileReader = new FileReader("src/JSON_Data/trieuDai.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            objectType = new TypeToken<ArrayList<TrieuDai>>(){}.getType();
            ArrayList<TrieuDai> listTrieuDai = gson.fromJson(fileReader, objectType);
            i = 0;
            while(i < listTrieuDai.size()){
                if(listTrieuDai.get(i).getKings().contains(nhanVat.getTen())){
                    lienKetTrieuDai.putIfAbsent(listTrieuDai.get(i).getTen(),listTrieuDai.get(i));
                    break;
                }
            }
            nhanVat.setLienKetTrieuDai(lienKetTrieuDai);

        }
    }
}
