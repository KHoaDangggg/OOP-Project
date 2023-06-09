package CrawlLeHoi.LeHoi_Nguon_05;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Thread_X implements Runnable {
    private final String url;

    public Thread_X(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        getLeHoiInfo(url);
    }

    private void getLeHoiInfo(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ten = doc.select("h1").first().text();
        Element e = doc.getElementsByClass("breadcrumbs info-detail").first();
        String diaDiem = null;
        if (e != null && e.childrenSize() > 1) diaDiem = e.child(1).text();
        Element content = doc.getElementsByClass("content-detail").first();
        String thoiGian = null;
        ArrayList<String> anh = new ArrayList<>();
        String thongTinLeHoi = null;
        if (content != null) {
            StringBuilder builder = new StringBuilder();
            Element leHoiTime = content.getElementsByClass("lehoi-time").first();
            if (leHoiTime != null) {
                thoiGian = leHoiTime.text();
            }
            Elements img = content.select("img");
            for (Element i : img) {
                if (i != null) anh.add(i.attr("src"));
            }
            Elements info = content.select("div");
            for(Element in: info){
                builder.append(in.text());
                Elements infoIn = in.select("div");
                for(Element infoin: infoIn){
                    if(infoin.childrenSize()<3) builder.append(infoin.text());
                }
            }
            thongTinLeHoi = builder.toString();
        }
        LeHoi leHoi = new LeHoi(ten, diaDiem, thoiGian, anh, thongTinLeHoi);
        System.out.println(leHoi.getTen());
        CrawlLeHoiNguon05.listCacLeHoi.add(leHoi);
    }

}
