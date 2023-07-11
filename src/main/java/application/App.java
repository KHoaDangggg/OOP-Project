package application;

import javafx.scene.image.Image;
import model.ditich.DiTich;
import model.sukien.SuKienLichSu;
import objectforui.GenerateUIObject;
import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.nhanvat.NhanVatLichSu;
import model.nhanvat.Vua;
import model.trieudai.TrieuDai;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class App extends Application {
    public static ArrayList<SuKienLichSu> listSuKien = null; //List cac su kien
    public static ArrayList<TrieuDai> listTrieuDai = null; //List cac trieu dai
    public static ArrayList<Vua> listVua = null; //List cac vi vua
    public static ArrayList<NhanVatLichSu> listNhanVatLichSu = null; //List cac nhan vat 1
    public static ArrayList<LeHoi> listLeHoi = null; //List cac le hoi
    public static ArrayList<DiTich> listDiTich = null; //List cac di tich lich su
    public static ArrayList<NhanVat> listNhanVat = null; //List cac nhan vat 2

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Preparing data for UI
        GenerateUIObject.gen();
        listDiTich = GenerateUIObject.listDiTich;
        listLeHoi = GenerateUIObject.listLeHoi;
        listNhanVat = GenerateUIObject.listNhanVat;
        listSuKien = GenerateUIObject.listSuKien;
        listTrieuDai = GenerateUIObject.listTrieuDai;

        //Main scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/MainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle(" Lịch sử Việt Nam");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("fxml/logo1.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}