package crawldata.crawlsukien;

import model.su_kien.SuKienLichSu;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CrawlThuVienLichSu {
    public static void main(String[] args) throws IOException {
        //Lấy toàn bộ link
        String link = "https://thuvienlichsu.vn/su-kien";
        ArrayList<String> link_events = new ArrayList<>();
        for (int i = 1; i <= 19; i++) {
            String link_page = link + "?page=" + i;
            Document doc = Jsoup.connect(link_page).get();
            Elements a_tags = doc.select("a[class=click]");
            for (Element a_tag : a_tags) {
                link_events.add(a_tag.attr("href"));
            }
        }
        //System.out.println(link_events);
        ArrayList<SuKienLichSu> listSuKien = new ArrayList<>(); //Khai báo danh sách sự kiện
        for (String link_event : link_events) {
            SuKienLichSu suKien = new SuKienLichSu(null);
            suKien.createObject();
            Document doc_event = Jsoup.connect("https://thuvienlichsu.vn"+link_event).get();
            Elements cards = doc_event.select("div[class=card mb-3]");//Tìm các thẻ thông tin
            Elements headers = cards.get(0).select("h3[class=header-edge]");//Thẻ tên sụ kiện, lấy tên sụ kiện (header) từ thẻ
            suKien.setTen(headers.get(0).text());
            suKien.setThoiGian(thoiGian(headers.get(0).text()).trim());

            ArrayList<String> listTrieuDai = new ArrayList<>();
            int nam = namDienra(suKien.getThoiGian());
            if(nam <= -258 && nam >= -999) listTrieuDai.add("Kỷ Hồng Bàng");
            if(nam >= -257 && nam < -208) listTrieuDai.add("Nhà Thục");
            if(nam >= -208 && nam <= 39) listTrieuDai.add("Giao Chỉ");
            if(nam >= 40 && nam <43) listTrieuDai.add("Trưng Nữ Vương");
            if(nam >=43 && nam <=543) listTrieuDai.add("Thời kỳ Bắc thuộc lần thứ II");
            if(nam >=544 && nam <602) listTrieuDai.add("Nhà Tiền Lý");
            if(nam >=602 && nam < 905) listTrieuDai.add("Thời kỳ Bắc thuộc lần thứ III");
            if(nam >= 905 && nam <=938) listTrieuDai.add("Thời kỳ tự chủ");
            if(nam >= 939 && nam <=965) listTrieuDai.add("Nhà Ngô");
            if(nam >=968 && nam <= 980) listTrieuDai.add("Nhà Đinh");
            if(nam >980 && nam <= 1009) listTrieuDai.add("Nhà Tiền Lê");
            if(nam >1009 && nam <=1225) listTrieuDai.add("Nhà Lý");
            if(nam > 1225 && nam <=1400) listTrieuDai.add("Nhà Trần");
            if(nam >1400 && nam <=1407) listTrieuDai.add("Nhà Hồ");
            if(nam >= 1413 && nam <=1428) listTrieuDai.add("Thời kỳ Bắc thuộc lần IV");
            if(nam >1428 && nam <1527) listTrieuDai.add("Nhà Hậu Lê");
            if(nam >= 1533 && nam <=1788) listTrieuDai.add("Thời kỳ Trịnh-Nguyễn phân tranh");
            if(nam >1778 && nam < 1802) listTrieuDai.add("Nhà Tây Sơn");
            if(nam > 1802 && nam < 1945) listTrieuDai.add("Nhà Nguyễn");
            if(nam > 1945 && nam < 1954) listTrieuDai.add("Nước Việt Nam");
            if(nam >= 1954 && nam <= 1975) listTrieuDai.add("Chiến tranh Việt Nam");
            if(nam > 1975) listTrieuDai.add("Cộng hòa xã hội chủ nghĩa Việt Nam");
            suKien.setNameRelativeDinasty(listTrieuDai);

            for(int i = 1; i < cards.size(); i++){// Duyệt các thẻ còn lại
                headers = cards.get(i).select("h3[class=header-edge]");//Kiểm tra loại thẻ
                if(headers.get(0).text().equals("Diễn biễn lịch sử")){
                    Elements cart_body = cards.get(i).select("div[class=card-body]");
                    suKien.setKetQua(cart_body.get(0).text());
                }
                if(headers.get(0).text().equals("Nhân vật liên quan")){
                    ArrayList<String> tenNhanVat = new ArrayList<>();
                    Elements tenLienQuan = cards.get(i).select("h4[class=card-title]");
                    for (Element ten: tenLienQuan) {
                        tenNhanVat.add(cleanData(ten.text()));
                    }
                    suKien.setNameRelativePerson(tenNhanVat);
                }
            }
            listSuKien.add(suKien);
        }
        //System.out.println(listSuKien);
        JSONArray jsonArray = new JSONArray();
        for(SuKienLichSu event: listSuKien){
            JSONObject eventJson = new JSONObject();
            eventJson.put("tenSuKien",event.getTen());
            eventJson.put("thoiGian",event.getThoiGian());
            eventJson.put("diaDiem",event.getDiaDiem());
            eventJson.put("ketQua",event.getKetQua());
            eventJson.put("nameRelativePerson",event.getNameRelativePerson());
            eventJson.put("nameRelativeDinasty",event.getNameRelativeDinasty());
            jsonArray.put(eventJson);
        }
        FileWriter fileWriter;
        try{
            //noinspection resource
            fileWriter = new FileWriter("src/JSON_Data/SuKien.json");
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String thoiGian(String tenSuKien) {
        int startIndex = tenSuKien.indexOf('(');
        int endIndex = tenSuKien.indexOf(')');
        String time;
        if(startIndex != -1 && endIndex != -1){
            time = tenSuKien.substring(startIndex+1, endIndex);
        }
        else{
            time = "Không rõ";
        }
        return time;
    }
    public static String cleanData(String ten){
        int startIndex = ten.indexOf('(');
        String tenNV;
        if(startIndex != -1){
            tenNV = ten.substring(0,startIndex);
        }
        else{
            tenNV = ten;
        }
        return tenNV;
    }
    public static int namDienra(String time) {
        int nam;
        int startIndex = time.indexOf('-');
        if(startIndex != -1){
            String s_nam = time.substring(0,startIndex);
            s_nam = s_nam.trim();
            try {
                nam = Integer.parseInt(s_nam);
            }
            catch (NumberFormatException e){
                nam = -10000;
            }
        }
        else{
            try {
                nam = Integer.parseInt(time.trim());
            }
            catch (NumberFormatException e){
                nam = -10000;
            }
        }
        return nam;
    }
}