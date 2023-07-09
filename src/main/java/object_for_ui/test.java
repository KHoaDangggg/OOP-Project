package object_for_ui;


import crawl_data.model.di_tich.DiTich;

public class test {
    public static void main(String[] args) {
        GenerateUIObject.gen();
        for (DiTich t : GenerateUIObject.listDiTich) {
            System.out.println(t.getTen());
            System.out.println(t.getDiaDiem());
        }
        System.out.println(GenerateUIObject.listDiTich.size());
    }
}
