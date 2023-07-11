package crawldata.crawlsukien;

import model.sukien.SuKienChienTranh;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("ALL")
public class CrawlChienTranhWiki {
    public void crawl() throws IOException {

        ArrayList<ArrayList<String>> events = new ArrayList<>();
        ArrayList<ArrayList<String>> listtenNhanVatLienQuan = new ArrayList<>();
        ArrayList<ArrayList<String>> listtenTrieuDaiLienQuan = new ArrayList<>();
        String link = "https://vi.wikipedia.org/wiki/C%C3%A1c_cu%E1%BB%99c_chi%E1%BA%BFn_tranh_Vi%E1%BB%87t_Nam_tham_gia";
        Document doc = Jsoup.connect(link).get();
        Elements list_table = doc.select("table[class=wikitable]");
        for (int i = 2; i < list_table.size(); i++) {
            Elements rows = list_table.get(i).select("tr");
            for (Element row : rows) {
                Elements columns = row.select("td");
                ArrayList<String> event = new ArrayList<>();
                if (!columns.isEmpty()) {
                    ArrayList<String> tenTrieuDaiLienQuan = new ArrayList<>();
                    ArrayList<String> tenNhanVatLienQuan = new ArrayList<>();
                    for (Element column : columns) {
                        event.add(column.text());
                    }
                    events.add(event);
                    for (int j = 1; j < columns.size(); j++) {

                        Elements a_tags = columns.get(j).select("a");
                        for (Element a_tag : a_tags) {
                            if (a_tag.text().contains("Nhà")) {
                                tenTrieuDaiLienQuan.add(a_tag.text());
                            } else if (a_tag.text().equals("Âu Lạc") || a_tag.text().equals("Lạc Việt") || a_tag.text().equals("Văn Lang") || a_tag.text().equals("Bách Việt") || a_tag.text().equals("Nam Việt") || a_tag.text().equals("Đại Cồ Việt") || a_tag.text().equals("Đại Việt")) {
                                tenTrieuDaiLienQuan.add(a_tag.text());
                            } else if (!a_tag.text().equals("Bắc thuộc lần 1") && !a_tag.text().contains("Quận")) {
                                tenNhanVatLienQuan.add(a_tag.text().replaceAll("\"", ""));
                            }
                        }
                    }
                    listtenTrieuDaiLienQuan.add(tenTrieuDaiLienQuan);
                    listtenNhanVatLienQuan.add(tenNhanVatLienQuan);
                }
            }
        }


        ArrayList<SuKienChienTranh> chienTranhVN = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            SuKienChienTranh chienTranh = new SuKienChienTranh(null);
            chienTranh.createObject();
            String time = thoiGianDienRa(events.get(i).get(0));
            chienTranh.setTen(events.get(i).get(0));
            chienTranh.setThoiGian(time);
            chienTranh.setPheTa(events.get(i).get(1).replaceAll("\"", ""));
            chienTranh.setPheDich(events.get(i).get(2));
            chienTranh.setKetQua(events.get(i).get(3));
            chienTranh.setNameRelativePerson(listtenNhanVatLienQuan.get(i));
            chienTranh.setNameRelativeDinasty(listtenTrieuDaiLienQuan.get(i));
            chienTranhVN.add(chienTranh);
        }

        FileWriter file = new FileWriter("src/JSON_Data/ChienTranh.json");
        JSONArray jsonArray = new JSONArray();
        for (SuKienChienTranh event : chienTranhVN) {
            JSONObject eventJson = new JSONObject();
            eventJson.put("tenSuKien", event.getTen());
            eventJson.put("thoiGian", event.getThoiGian());
            eventJson.put("diaDiem", event.getDiaDiem());
            eventJson.put("nguyenNhan", event.getNguyenNhan());
            eventJson.put("pheTa", event.getPheTa());
            eventJson.put("pheDich", event.getPheDich());
            eventJson.put("chiHuyPheTa", event.getChiHuyPheTa());
            eventJson.put("chiHuyPheDich", event.getChiHuyPheDich());
            eventJson.put("tonThatTa", event.getTonThatTa());
            eventJson.put("tonThatDich", event.getTonThatDich());
            eventJson.put("ketQua", event.getKetQua());
            eventJson.put("nameRelativePerson", event.getNameRelativePerson());
            eventJson.put("nameRelativeDinasty", event.getNameRelativeDinasty());
            jsonArray.put(eventJson);
        }
        file.write(jsonArray.toString());
        file.flush();
        file.close();
    }

    private String thoiGianDienRa(String time) {
        int startIndex = time.indexOf('(');
        int endIndex = time.indexOf(')');
        String thoigian;
        if (startIndex != -1 && endIndex != -1) {
            thoigian = time.substring(startIndex + 1, endIndex);
        } else {
            thoigian = "Không rõ";
        }
        return thoigian;
    }
}
