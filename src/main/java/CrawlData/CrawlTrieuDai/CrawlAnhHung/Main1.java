package CrawlData.CrawlTrieuDai.CrawlAnhHung;

import java.io.IOException;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main1 {
    static List<AnhHung> dsAnhHung = new ArrayList<AnhHung>();
    public static void main(String[] args) {
        crawlAnhHung();
        crawl();
        savetoJson();

    }

   static void crawlAnhHung() {
            try{
                String url = "https://vi.wikipedia.org/wiki/Danh_nh%C3%A2n_th%E1%BB%9Di_%C4%90inh?fbclid=IwAR0oYcxArES-fdtY_YiGvo3mc52Xanze0a3DO3Ye0H97KQYr62-PZoflUYc#V%C3%B5_t%C6%B0%E1%BB%9Bng_th%E1%BB%9Di_%C4%90inh";
                Document doc = Jsoup.connect(url).timeout(5000).get();
                Elements tables = doc.select("table.wikitable.sortable");
                System.out.println(tables.size());



    //            List<CrawlData.CrawlTuongThoiDinh.AnhHung> dsAnhHung = new ArrayList<CrawlData.CrawlTuongThoiDinh.AnhHung>();

                for (Element table : tables)
                {
                    Elements tbody = table.select("tbody");
                    Elements rows = tbody.select("tr");
                    rows.remove(0);
                    for (Element row : rows)
                    {
                        Elements cells = row.select("td");
                        String hoVaTen = cells.get(0).text();
                        String tuLieu = cells.get(1).text();
                        String queQuan = cells.get(2).text();
                        String tomTat = cells.get(3).text();
                        AnhHung anhhung = new AnhHung(hoVaTen, tuLieu, queQuan, tomTat, url);
                        dsAnhHung.add(anhhung);
                    }
                }



                int count = 0;
                for (AnhHung anhhung : dsAnhHung)
                {
                    System.out.println(anhhung.getHoVaTen() );
                    System.out.println(anhhung.gettuLieu() );
                    System.out.println(anhhung.getQueQuan() );
                    System.out.println(anhhung.gettomTat() );
                    // add link
                    System.out.println(anhhung.getLink());

                    System.out.println("\n");
                    count++;
                }
                System.out.println(count);

    //            ObjectMapper objectMapper = new ObjectMapper();
    //            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    //            objectMapper.writeValue(new File("danhNhanThoiDinh.json"), dsAnhHung);

            }


            catch (IOException e){
                e.printStackTrace();
            }
        }
        static void crawl() {
        }

        static void savetoJson(){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.writeValue(new File("danhNhanThoiDinh.json"), dsAnhHung);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
}
