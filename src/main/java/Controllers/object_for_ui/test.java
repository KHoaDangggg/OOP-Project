package Controllers.object_for_ui;


import CrawlData.CrawlDiTich.DiTichLichSu;

public class test {
    public static void main(String[] args) {
        GenerateUIObject.gen();
       for(DiTichLichSu t: GenerateUIObject.listDiTich){
            System.out.println(t.getTen());
            System.out.println(t.getDiaDiem());
        }
        System.out.println(GenerateUIObject.listDiTich.size());
    }
}