package Controllers.object_for_ui;

import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlSuKien.SuKienLichSu;
import CrawlData.CrawlTrieuDai.TrieuDai;

public class testgen {
    private JsonToObj gen = new JsonToObj();
    private LinkingObject link = new LinkingObject(gen.listSuKien,gen.listTrieuDai,gen.listVua,gen.listNhanVat,gen.listLeHoi);
    private void test(){
        gen.generate();
        link.link();
        int count = 0;
        for(LeHoi leHoi: gen.listLeHoi){
            System.out.println(leHoi.getLienKetLeHoi().size());
            count += leHoi.getLienKetLeHoi().size();
        }
        System.out.println("\n\n"+count);
    }

    public static void main(String[] args) {
        testgen t = new testgen();
        t.test();
    }
}
