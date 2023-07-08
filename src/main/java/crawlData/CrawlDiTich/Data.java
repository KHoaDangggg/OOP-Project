package crawlData.CrawlDiTich;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    public static void main(String[] args) {
        String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";

        try {
            // Connect to the website and retrieve the HTML content
            Document document = Jsoup.connect(url).get();

            // Extract the table containing the national parks data
            Elements tables = document.select("table.wikitable");
            ArrayList<String[]> heritageList = new ArrayList<>();

            //int i = 0;
            for (Element table : tables) {
                // Extract the rows from the table
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    Elements columns = row.select("td");
                    if (!columns.isEmpty()) {
                        //Extract the Name, Location and Classification of the site
                        String name = columns.get(0).text();
                        System.out.println(name);
                        String area = columns.get(1).text();
                        String classify = columns.get(2).text();

                        //Extract the Year Of Recognition
                        String year;
                        if (columns.size() == 3) year = "Không rõ";
                        else {
                            year = columns.get(3).text();
                            if (year.isEmpty())
                                year = "Không rõ";
                        }

                        //Extract the Description
                        String description;
                        Element linkref = columns.get(0).selectFirst("a");
                        if(linkref != null){
                            String link = linkref.absUrl("href");
                            Document doc = Jsoup.connect(link).get();
                            Element descriptionElement = doc.selectFirst("div.mw-parser-output > p:first-of-type");
                            if (descriptionElement != null) {
                                description = descriptionElement.text();
                                description = description.replaceAll("\\[\\d+]", "");
                            } else continue;
                        } else continue;

                        //Add to the string list
                        String[] heritage = new String[100000];
                        heritage[0] = name;
                        heritage[1] = area;
                        heritage[2] = classify;
                        heritage[3] = year;
                        heritage[4] = description;
                        heritageList.add(heritage);
                        //i++;
                    }
                }
            }

           /* try (FileWriter file = new FileWriter("heritage2.json")) {
                JSONArray jsonArray = new JSONArray();
                for (String[] heritage : heritageList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Tên", heritage[0]);
                    jsonObject.put("Địa điểm", heritage[1]);
                    jsonObject.put("Loại di tích", heritage[2]);
                    jsonArray.add(jsonObject);
                }
                file.write(jsonArray.toJSONString());
                file.flush();
            } */

            //Writing Json file
            try (FileWriter file = new FileWriter("src/JSON_Data/DiTichLichSu.json")) {
                JSONArray jsonArray = new JSONArray();
                for (String[] heritage : heritageList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ten", heritage[0]);
                    jsonObject.put("diaDiem", heritage[1]);
                    jsonObject.put("loaiDiTich", heritage[2]);
                    jsonObject.put("namCongNhan", heritage[3]);
                    jsonObject.put("moTa", heritage[4]);
                    jsonArray.put(jsonObject);
                }
                file.write(jsonArray.toString());
                file.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
