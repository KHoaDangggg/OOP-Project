package crawldata.crawlnhanvat.trangnguyenbangnhan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.nhanvat.DanhHieu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CrawlTrangNguyenBangNhanManager {
    private final ArrayList<DanhHieu> danhHieuArrayList = new ArrayList<>();
    private int count = 0;

    public void crawl() throws IOException {
        addTrangNguyen();
        addBangNhan();
        writeToJson();
        System.out.println(danhHieuArrayList.size());
        System.out.println(count);
    }

    private void addTrangNguyen() throws IOException {
        String link = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Tr%E1%BA%A1ng_nguy%C3%AAn_Vi%E1%BB%87t_Nam";
        Document doc = Jsoup.connect(link).get();
        Elements rows = doc.select("table.wikitable.sortable").select("tbody").select("tr");
        for (Element row : rows) {
            Elements columns = row.select("td");
            if (columns.size() < 7) continue;
            if (columns.get(1).text().contains("Nguyễn Giản Thanh")) {
                DanhHieu danhHieu1 = new DanhHieu(
                        "Nguyễn Giản Thanh",
                        columns.get(2).text(),
                        columns.get(3).text(),
                        columns.get(4).text(),
                        columns.get(5).text(),
                        "Trạng Me");
                danhHieu1.setDanhHieu("Trạng nguyên");
                danhHieuArrayList.add(danhHieu1);
                count++;

                DanhHieu danhHieu2 = new DanhHieu(
                        "Hứa Tam Tỉnh",
                        columns.get(2).text(),
                        columns.get(3).text(),
                        columns.get(4).text(),
                        columns.get(5).text(),
                        "Trạng Ngọt");
                danhHieu2.setDanhHieu("Trạng nguyên");
                danhHieuArrayList.add(danhHieu2);
                count++;
                continue;
            }
            DanhHieu danhHieu = new DanhHieu(
                    columns.get(1).text(),
                    columns.get(2).text(),
                    columns.get(3).text(),
                    columns.get(4).text(),
                    columns.get(5).text(),
                    columns.get(6).text());
            danhHieu.setDanhHieu("Trạng nguyên");
            danhHieuArrayList.add(danhHieu);
            count++;
        }
    }

    private void addBangNhan() throws IOException {
        String link = "https://vi.wikipedia.org/wiki/B%E1%BA%A3ng_nh%C3%A3n";
        Document doc = Jsoup.connect(link).get();
        Elements rows = doc.select("table.wikitable.sortable").select("tbody").select("tr");
        for (Element row : rows) {
            Elements columns = row.select("td");
            if (columns.size() < 6) continue;
            DanhHieu danhHieu = new DanhHieu(
                    columns.get(0).text(),
                    columns.get(1).text(),
                    columns.get(2).text(),
                    columns.get(3).text(),
                    columns.get(4).text(),
                    columns.get(5).text());
            danhHieu.setDanhHieu("Bảng nhãn");
            danhHieuArrayList.add(danhHieu);
            count++;
        }
    }

    private void writeToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(danhHieuArrayList);
        try {
            FileWriter file = new FileWriter("src/JSON_Data/TrangNguyen&BangNhan.json");
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}