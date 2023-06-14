package Controllers;

import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class LinkDataTrieuDai implements  Link{
    @Override
    public void link(Object obj) {
        Gson gson = new Gson();
        FileReader fileReader;
        if(obj instanceof TrieuDai){
            TrieuDai trieuDai = (TrieuDai) obj;
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
                for(String str: listSuKien.get(i).getNameRelativeDinasty()){
                    if(trieuDai.getTen().toLowerCase().equalsIgnoreCase(str.toLowerCase())||trieuDai.getTen().toLowerCase().contains(str.toLowerCase())||str.toLowerCase().contains(trieuDai.getTen().toLowerCase())){
                        lienKetSuKien.putIfAbsent(listSuKien.get(i).getTenSuKien(),listSuKien.get(i));
                    }
                }
                i++;
            }
            trieuDai.setLienKetSuKien(lienKetSuKien);

            HashMap<String, Vua> lienKetVua = new HashMap<>();
            try {
                fileReader = new FileReader("src/JSON_Data/Vua.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            objectType = new TypeToken<ArrayList<Vua>>(){}.getType();
            ArrayList<Vua>  listVua = gson.fromJson(fileReader,objectType);
            i = 0;
            while(i < listVua.size()){
                if(trieuDai.getKings().contains(listVua.get(i).getTen())){
                    lienKetVua.putIfAbsent(listVua.get(i).getTen(),listVua.get(i));
                }
                i++;
            }
            trieuDai.setLienKetVua(lienKetVua);
        }
    }
}
