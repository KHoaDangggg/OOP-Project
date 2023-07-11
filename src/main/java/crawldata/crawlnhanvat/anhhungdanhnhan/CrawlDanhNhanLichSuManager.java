package crawldata.crawlnhanvat.anhhungdanhnhan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.nhanvat.DanhNhan;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlDanhNhanLichSuManager {
    private final List<DanhNhan> dsAnhHung = new ArrayList<>();

    public void crawl() {
        crawlAnhHung();
        saveToJson();

    }

    private void crawlAnhHung() {
        try {
            String url = "https://vi.wikipedia.org/wiki/Danh_nh%C3%A2n_th%E1%BB%9Di_%C4%90inh?fbclid=IwAR0oYcxArES-fdtY_YiGvo3mc52Xanze0a3DO3Ye0H97KQYr62-PZoflUYc#V%C3%B5_t%C6%B0%E1%BB%9Bng_th%E1%BB%9Di_%C4%90inh";
            Document doc = Jsoup.connect(url).timeout(5000).get();
            Elements tables = doc.select("table.wikitable.sortable");
            System.out.println(tables.size());

            for (Element table : tables) {
                Elements tbody = table.select("tbody");
                Elements rows = tbody.select("tr");
                rows.remove(0);
                for (Element row : rows) {
                    Elements cells = row.select("td");
                    String hoVaTen = cells.get(0).text();
                    String tuLieu = cells.get(1).text();
                    String queQuan = cells.get(2).text();
                    String tomTat = cells.get(3).text();

                    if (hoVaTen.isBlank() || hoVaTen.isEmpty()) {
                        hoVaTen = "Không rõ";
                    } else if (tuLieu.isBlank() || tuLieu.isEmpty()) {
                        tuLieu = "Không rõ";
                    } else if (queQuan.isBlank() || queQuan.isEmpty()) {
                        queQuan = "Không rõ";
                    } else if (tomTat.isEmpty() || tomTat.isBlank()) {
                        tomTat = "Không rõ";
                    }

                    DanhNhan anhhung = new DanhNhan(hoVaTen, tuLieu, queQuan, tomTat);
                    dsAnhHung.add(anhhung);
                }
            }

            int count = 0;
            for (DanhNhan anhhung : dsAnhHung) {
                System.out.println(anhhung.getTen());
                System.out.println(anhhung.gettuLieu());
                System.out.println(anhhung.getQueQuan());
                System.out.println(anhhung.gettomTat());

                System.out.println("\n");
                count++;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("src/JSON_Data/danhNhanThoiDinh.json"), dsAnhHung);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}