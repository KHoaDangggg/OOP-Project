package CrawlData.CrawlSuKien;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class crawlChienTranh {
    public static void main(String[] args) throws IOException {
        //Get links in the first page
        String link = "https://nguoikesu.com/tu-lieu/quan-su?filter_tag[0]=";
        Document doc_first = Jsoup.connect(link).get();
        ArrayList<String> list_href = new ArrayList<>();
        Elements a_tags = doc_first.select("a[itemprop=url]");
        for (Element a_tag : a_tags) {
            list_href.add(a_tag.attr("href"));
        }

        //Get link in the other page
        for(int i=5;i<=70;i+=5){
            String links = link + "&start="+i;
            Document docs = Jsoup.connect(links).get();
            Elements a_tagss = docs.select("a[itemprop=url]");
            for(Element a: a_tagss){
                list_href.add(a.attr("href"));
            }
        }

        //Get data from each page

        ArrayList<SuKienChienTranh> list_events = new ArrayList<>();
        // Loop for all links

        for (int i = 0; i < list_href.size(); i++) {
            String link_expand = "https://nguoikesu.com" + list_href.get(i);
            Document doc_expand = Jsoup.connect(link_expand).get();

            //Get the relative objects
            ArrayList<String> nhanVatLienQuan = new ArrayList<>();
            ArrayList<String> trieuDaiLienQuan = new ArrayList<>();

            Elements link_tags = doc_expand.select("a[class=annotation]");
            for(Element link_tag: link_tags){
                if(link_tag.attr("href").contains("/nhan-vat/")){
                    if(link_tag.text().contains("nhà")){
                        if(!trieuDaiLienQuan.contains(link_tag.text())){
                            trieuDaiLienQuan.add(link_tag.text());
                        }
                    }
                    else{
                        if(!nhanVatLienQuan.contains(link_tag.text())){
                            nhanVatLienQuan.add(link_tag.text());
                        }
                    }
                }
            }

            //Get the other data
            ArrayList<String> data = new ArrayList<>();
            if(doc_expand.select("table[cellspacing=2]").size()!=0) {
                Element table = doc_expand.select("table[cellspacing=2]").first();
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    if (row.select("td").size() == 0) {
                        data.add(row.text());
                    } else {
                        Elements cells = row.select("td");
                        for (Element cell : cells) {
                            data.add(cell.text());
                        }
                    }
                }
            }
            SuKienChienTranh obj = new SuKienChienTranh();
            obj.createObject();
            for (int j = 0; j < data.size(); j++) {
                if (j == 0) {
                    obj.setTenSuKien(data.get(j));
                }
                if (data.get(j).equals("Thời gian")) {
                    obj.setThoiGian(data.get(j + 1));
                }
                if (data.get(j).equals("Địa điểm")) {
                    obj.setDiaDiem(data.get(j + 1));
                }
                if (data.get(j).equals("Kết quả")) {
                    obj.setKetQua(data.get(j + 1).replaceAll("\"",""));
                }
                if (data.get(j).equals("Tham chiến")) {
                    obj.setPheTa(data.get(j + 1));
                    obj.setPheDich(data.get(j + 2).replaceAll(".gif\" src=\"/images/wiki/tran-ngoc-hoi-dong-da/05869fc73a25b2f4c8999abd4386ab9b.gif\" />",""));
                }
                if (data.get(j).equals("Lực lượng")) {
                    obj.setLucLuongPheTa(data.get(j + 1));
                    obj.setLucLuongPheDich(data.get(j + 2));
                }
                if (data.get(j).equals("Chỉ huy")) {
                    obj.setChiHuyPheTa(data.get(j + 1).replaceAll("\"",""));
                    obj.setChiHuyPheDich(data.get(j + 2).replaceAll("\"",""));
                }
                if (data.get(j).equals("Tổn thất")) {
                    obj.setTonThatTa(data.get(j + 1));
                    obj.setTonThatDich(data.get(j + 2));
                }
                if(data.get(j).equals("Nguyên nhân bùng nổ")){
                    obj.setNguyenNhan(data.get(j+1));
                }
                obj.setNameRelativePerson(nhanVatLienQuan);
                obj.setNameRelativeDinasty(trieuDaiLienQuan);
            }
            if(obj.getTenSuKien()!=null) {
                list_events.add(obj);
            }
        }

        //Write to the json file
        /*
        FileWriter file = new FileWriter("CT.json");
        file.write(list_events.toString());
        file.flush();
        file.close();
        */
       JSONArray jsonArray = new JSONArray();
        for(SuKienChienTranh event: list_events){
            JSONObject eventJson = new JSONObject();
            eventJson.put("tenSuKien",event.getTenSuKien());
            eventJson.put("thoiGian",event.getThoiGian());
            eventJson.put("diaDiem",event.getDiaDiem());
            eventJson.put("nguyenNhan",event.getNguyenNhan());
            eventJson.put("pheTa",event.getPheTa());
            eventJson.put("pheDich",event.getPheDich());
            eventJson.put("chiHuyPheTa",event.getChiHuyPheTa());
            eventJson.put("chiHuyPheDich",event.getChiHuyPheDich());
            eventJson.put("tonThatTa",event.getTonThatTa());
            eventJson.put("tonThatDich",event.getTonThatDich());
            eventJson.put("ketQua",event.getKetQua());
            eventJson.put("nameRelativePerson",event.getNameRelativePerson());
            eventJson.put("nameRelativeDinasty",event.getNameRelativeDinasty());
            jsonArray.put(eventJson);
        }
        try{
            FileWriter fileWriter = new FileWriter("src/JSON_Data/ChienTranh.json");
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}