package crawldata.crawllehoi;

import model.lehoi.LeHoi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ThreadX implements Runnable {
    private final String url;

    public ThreadX(String url) {
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
        String ten = null;
        Element h1 = doc.select("h1").first();
        if (h1 != null)
            ten = h1.text();
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
                if (i != null) anh.add(CrawlLeHoiManager.BASE_URL + i.attr("src"));
            }
            Elements info = content.select("div");
            Elements paragraph = content.select("p");
            for (Element in : info) {
                if (in.select("ins").first() == null && in.select("img").first() == null)
                    builder.append(in.text()).append(" ");
            }
            for (Element p : paragraph) {
                builder.append(p.text()).append(" ");
            }
            thongTinLeHoi = builder.toString();
        }
        LeHoi leHoi = new LeHoi(ten, diaDiem, thoiGian, anh, thongTinLeHoi, url);
        System.out.println(leHoi.getTen());
        CrawlLeHoiManager.listCacLeHoi.add(leHoi);
    }

}
