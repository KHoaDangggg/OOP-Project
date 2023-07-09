package application;

import model.di_tich.DiTich;
import model.su_kien.SuKienLichSu;
import objectforui.GenerateUIObject;
import model.le_hoi.LeHoi;
import model.nhan_vat.NhanVat;
import model.nhan_vat.NhanVatLichSu;
import model.nhan_vat.Vua;
import model.trieu_dai.TrieuDai;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class App extends Application {
    public static ArrayList<SuKienLichSu> listSuKien = null; //List cac su kien
    public static ArrayList<TrieuDai> listTrieuDai =null; //List cac trieu dai
    public static ArrayList<Vua> listVua = null; //List cac vi vua
    public static ArrayList<NhanVatLichSu> listNhanVatLichSu = null; //List cac nhan vat
    public static ArrayList<LeHoi> listLeHoi = null; //List cac le hoi
    public static ArrayList<DiTich> listDiTich = null; //List cac di tich lich su
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
        //System.out.println(listNhanVat.size());
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