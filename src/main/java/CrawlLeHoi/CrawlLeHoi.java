package CrawlLeHoi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CrawlLeHoi {
    private final static String URL = "https://vi.m.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam#Danh_s%C3%A1ch_m%E1%BB%99t_s%E1%BB%91_l%E1%BB%85_h%E1%BB%99i";

    public static void main(String[] args) {
        crawlFromWiki();
    }

    static void crawlFromWiki() {
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //html extracting
        Element content = doc.getElementById("mf-section-6");
        Elements elements = null;
        if (content != null) {
            elements = content.getElementsByTag("tr");
        }
        ArrayList<Elements> td = new ArrayList<>();
        if (elements != null) {
            for (Element e : elements) {
                //System.out.println(e);
                Elements s = e.getElementsByTag("td");
                td.add(s);
            }
        }

        //System.out.println(td.get(1).get(4).text());
        //convert html to object type "LeHoi"
        ArrayList<LeHoi> lehoi = new ArrayList<>();
        for (int i = 1; i < td.size(); i++) {
            lehoi.add(new LeHoi(
                    td.get(i).get(0).text(),
                    td.get(i).get(1).text(),
                    td.get(i).get(2).text(),
                    td.get(i).get(3).text(),
                    td.get(i).get(4).text(),
                    td.get(i).get(5).text()
            ));
        }

        //Print out Le hoi
        for (LeHoi leHoi : lehoi) System.out.println(leHoi.toString());

        //Convert to data to json
        writeToJSON(lehoi);
    }

    static void writeToJSON(ArrayList<LeHoi> lehoi) {
        JSONArray jsonArray = new JSONArray();
        for (LeHoi leHoi : lehoi) {
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("ten", t.getTen().replaceAll("[\t\n]", ""));

            jsonObject.put("thoi_gian", leHoi.getThoiGian());
            jsonObject.put("dia_diem", leHoi.getDiaDiem());
            jsonObject.put("ten_le_hoi", leHoi.getTenLeHoi());
            jsonObject.put("nam_to_chuc_dau_tien", leHoi.getNamToChucDauTien());
            jsonObject.put("nhan_vat_lien_quan", leHoi.getNhanVatLQ());
            jsonObject.put("ghi_chu", leHoi.getGhiChu());
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_Data/LeHoi.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}