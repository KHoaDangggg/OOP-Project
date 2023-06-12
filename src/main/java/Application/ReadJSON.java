package Application;

import CrawlData.CrawlTrieuDai.TrieuDai;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadJSON {
    private static ObservableList<TrieuDai> trieuDaiList = FXCollections.observableArrayList();
    public static void main(String[] args) {
        readData();
    }

    public static ObservableList<TrieuDai> readData() {
        Gson gson = new Gson();
        String filePath = "src/JSON_Data/trieuDai.json";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the JSON file into an array of objects
            TrieuDai[] objects = gson.fromJson(reader, TrieuDai[].class);

            // Do something with the array of objects
            for (TrieuDai obj : objects) {
                System.out.println(obj.getTen());
            }
            trieuDaiList.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trieuDaiList;
    }
}
