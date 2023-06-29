package CrawlData.CrawlNhanVat.NhaVat_VanSu;

public class Main {

    public static void main(String[] args) {
/*        String url = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/58/bui-duong-lich";
        getInfo1(url);
        NhanVatVanSu nv = listNhanVat.get(0);
        System.out.println(nv.getTen());
        for(String k: nv.getAtt().keySet()){
            System.out.println(k+": "+nv.getAtt().get(k));
        }*/
        CrawlFromVanSu crawl = new CrawlFromVanSu();
        crawl.crawl();
    }

    /*public static void getInfo(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element ten = doc.body().selectFirst("div[class=\"active section\"]");
        Elements rows = doc.body().select("tbody").select("tr");
        Elements ghichu = rows.last().select("td").select("p");
        if (ten != null) {
            nhanVatCuaHai nhanVat = new nhanVatCuaHai(ten.text());

            for (int i = 0; i < rows.size() - 1; i++) {
                Elements subRows = rows.get(i).select("td");
                String key = subRows.get(0).text();
                StringBuilder value = new StringBuilder();
                for (int j = 1; j < subRows.size(); j++) {
                    if (j == 1) {
                        value.append(subRows.get(j).text());
                    } else {
                        value.append(" \\ ").append(subRows.get(j).text());
                    }
                }

                nhanVat.addKeyValue(key);
                nhanVat.addKeyValue(value.toString());
            }

            for (Element element : ghichu) {
                String str = element.text();
                nhanVat.addGhiChu(str);
            }

            //listNhanVat.add(nhanVat);
            nhanVat.print();
        }
    }

    private static void getInfo1(String url){
        LinkedHashMap<String, String> attr = new LinkedHashMap<>();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ten = doc.body().selectFirst("div[class=\"active section\"]").text();
        Elements rows = doc.body().select("tbody").select("tr");
        for (Element row : rows) {
            Elements td = row.children();
            if (td.size() > 1) {
                attr.put(td.get(0).text(), td.get(1).text());
            }
        }
        Element tt = rows.last();
        if(tt!=null) attr.put("Thông tin", tt.text());

        NhanVatVanSu nv = new NhanVatVanSu(ten, attr);
        listNhanVat.add(nv);
    }*/
}