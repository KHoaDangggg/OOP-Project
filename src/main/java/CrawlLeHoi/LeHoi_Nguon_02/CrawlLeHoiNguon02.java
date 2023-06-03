package CrawlLeHoi.LeHoi_Nguon_02;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CrawlLeHoiNguon02 {
    private final String URL = "https://wolverineair.com/cac-le-hoi-o-viet-nam/";

    public void wolCrawl() {
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements dataContainers = doc.getElementsByClass("post-content description cf entry-content has-share-float content-spacious-full");

        Element dataContainer = dataContainers.get(0);

        ArrayList<LeHoiWol> leHoiWols = new ArrayList<>();
        LeHoiWol leHoiWol = new LeHoiWol(null, null, null);
        for (int i = 13; i < dataContainer.childrenSize() - 2; i++) {
            if (checkHeader(dataContainer.child(i))) {
                String ten = dataContainer.child(i).text().substring(3);
                String anh = dataContainer.child(i + 1).select("img").attr("src");
                leHoiWol.setTen(ten);
                leHoiWol.setAnh(anh);
                i++;
                //continue;
            } else continue;
            StringBuilder builder = new StringBuilder();
            while (i < dataContainer.childrenSize() - 3 && checkParagraph(dataContainer.child(i + 1))) {
                builder.append(dataContainer.child(i + 1).text());
                i++;
            }
            leHoiWol.setNoiDung(builder.toString());

            leHoiWols.add(leHoiWol);
            leHoiWol = new LeHoiWol(null, null, null);
        }

        //Add additional img for special case
        leHoiWols.get(2).setAnh(dataContainer.child(22).select("img").attr("src"));

        //Test print out data
        for (LeHoiWol lehoi : leHoiWols) {
            System.out.println(lehoi.getTen());
            System.out.println(lehoi.getAnh());
            System.out.println(lehoi.getNoiDung());
        }

        //Write data to json file
        writeToJSON(leHoiWols);

    }

    /* private boolean checkElement(Element e) {
        return !e.child(0).hasAttr("id");
    }*/

    private boolean checkParagraph(Element e) {
        return e.is("p");
    }

    private boolean checkHeader(Element e) {
        return e.is("h4") && e.child(0).is("span");
    }

    private void writeToJSON(ArrayList<LeHoiWol> lehoi) {
        JSONArray jsonArray = new JSONArray();
        for (LeHoiWol leHoi : lehoi) {
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("ten", t.getTen().replaceAll("[\t\n]", ""));

            jsonObject.put("ten_le_hoi", leHoi.getTen());
            jsonObject.put("anh_le_hoi", leHoi.getAnh());
            jsonObject.put("noi_dung_le_hoi", leHoi.getNoiDung());
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_DATA/LeHoi_Nguon_02.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
