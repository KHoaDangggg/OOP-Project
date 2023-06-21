package Controllers.object_for_ui;


import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;

public class test {
    public static void main(String[] args) {
        GenerateUIObject.gen();
       for(TrieuDai t: GenerateUIObject.listTrieuDai){
            System.out.println(t.getLienKetVua().keySet());
            System.out.println(t.getLienKetVua().size());
        }
       for(SuKienChienTranh suKienChienTranh: GenerateUIObject.listSuKien){
           System.out.println(suKienChienTranh.getLienKetNhanVat());
           System.out.println(suKienChienTranh.getLienKetNhanVat().size());
       }
    }
}
