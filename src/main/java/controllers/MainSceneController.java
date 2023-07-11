package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DuLieuLichSu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Controller for the main scene
public class MainSceneController implements Initializable {
    public static ArrayList<DuLieuLichSu> saveData = new ArrayList<>();
    @FXML
    private Button btnDynasty;
    @FXML
    private Button btnRelic;
    @FXML
    private Button btnFestival;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnFigures;
    @FXML
    private Button btnExit;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnDynasty) {
            openSearchScene("Triều Đại");
        }
        if (event.getSource() == btnRelic) {
            openSearchScene("Di tích lịch sử");
        }
        if (event.getSource() == btnFestival) {
            openSearchScene("Lễ Hội");
        }
        if (event.getSource() == btnEvent) {
            openSearchScene("Sự Kiện");
        }
        if (event.getSource() == btnFigures) {
            openSearchScene("Nhân Vật Lịch Sử");
        }
        if (event.getSource() == btnExit) {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        }
    }

    //Open search scene using local database
    private void openSearchScene(String btnName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Label label = (Label) root.lookup("#title");
        label.setText(btnName);
        Stage stage = (Stage) btnDynasty.getScene().getWindow();
        SearchSceneController controller = loader.getController();
        controller.setLastScene(btnDynasty.getScene());
        controller.setStage(stage);
        Button btn = (Button) root.lookup("#exitBtn");
        btn.setOnAction(event -> stage.setScene(btnDynasty.getScene()));
        stage.setScene(new Scene(root));
    }

    //Open search online scene
    @FXML
    private void openOnlineSearchScene() {
        Stage stage = (Stage) btnDynasty.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Webview.fxml"));
        WebViewController controller = new WebViewController();
        controller.setLastScene(btnDynasty.getScene());
        loader.setController(controller);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //Open favorite scene
    @FXML
    private void openSaveScene() {
        Stage stage = (Stage) btnDynasty.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/FavoriteScene.fxml"));
        FavoriteSceneController controller = new FavoriteSceneController(btnDynasty.getScene());
        loader.setController(controller);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(root));
    }
}