package Controllers;

import CrawlData.CrawlSuKien.SuKienChienTranh;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TestLienKet {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader("src/JSON_Data/ChienTranh.json");
        Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>(){}.getType();
        ArrayList<SuKienChienTranh> listSuKien = gson.fromJson(fileReader,objectType);
        LinkDataSuKien lienKet = new LinkDataSuKien();
        lienKet.link(listSuKien.get(11));
        System.out.println(listSuKien.get(11).getLienKetTrieuDai());
        System.out.println(listSuKien.get(11).getLienKetNhanVat());
    }
}
