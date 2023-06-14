package Controllers;

import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class LinkDataLeHoi implements  Link{
    @Override
    public void link(Object obj) {
        Gson gson = new Gson();
        FileReader fileReader;
        if(obj instanceof LeHoi){
            LeHoi leHoi = (LeHoi) obj;
            HashMap<String, LeHoi> lienKetLeHoi = new HashMap<>();
            try {
                fileReader = new FileReader("src/JSON_Data/LeHoi_Nguon_05.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Type objectType = new TypeToken<ArrayList<LeHoi>>(){}.getType();
            ArrayList<LeHoi> listLeHoi = gson.fromJson(fileReader,objectType);
            for(LeHoi lehoi: listLeHoi){
                if(leHoi.getDiaDiem().equalsIgnoreCase(lehoi.getDiaDiem())){
                    lienKetLeHoi.putIfAbsent(lehoi.getTen(),lehoi);
                }
            }
            leHoi.setLienKetLeHoi(lienKetLeHoi);
        }
    }
}
