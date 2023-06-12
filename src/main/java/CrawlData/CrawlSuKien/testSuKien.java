package CrawlData.CrawlSuKien;
import  com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class testSuKien {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("CT.json");
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<SuKienChienTranh>>(){}.getType();
        ArrayList<SuKienChienTranh> events = gson.fromJson(fileReader, objectType);
        for (SuKienChienTranh event:events) {
            event.linkTrieuDai();
            System.out.println(event.toString());
            event.hienThiLienKetTrieuDai();
            System.out.println();
        }
    }
}
