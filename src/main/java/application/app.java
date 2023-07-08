package application;

import controllers.objectforui.GenerateUIObject;
import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class app extends Application {
    public static ArrayList<SuKienChienTranh> listSuKien = null; //List cac su kien
    public static ArrayList<trieuDai> listTrieuDai =null; //List cac trieu dai
    public static ArrayList<Vua> listVua = null; //List cac vi vua
    public static ArrayList<NhanVatLichSu> listNhanVatLichSu = null; //List cac nhan vat
    public static ArrayList<LeHoi> listLeHoi = null; //List cac le hoi
    public static ArrayList<DiTichLichSu> listDiTich = null; //List cac di tich lich su
    public static ArrayList<NhanVat> listNhanVat = null;
    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws IOException {
        GenerateUIObject.gen();
        listDiTich = GenerateUIObject.listDiTich;
        listLeHoi = GenerateUIObject.listLeHoi;
        listNhanVat = GenerateUIObject.listNhanVat;
        listSuKien = GenerateUIObject.listSuKien;
        listTrieuDai = GenerateUIObject.listTrieuDai;

        //Main scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/MainScene.fxml"));
        //Parent root = fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Lịch sử Việt Nam");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}