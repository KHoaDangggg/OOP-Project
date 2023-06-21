package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

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
            renderNewScence("Triều Đại");
        }
        if (event.getSource() == btnRelic) {
            renderNewScence("Di tích lịch sử");
        }
        if (event.getSource() == btnFestival) {
            renderNewScence("Lễ Hội");
        }
        if (event.getSource() == btnEvent) {
            renderNewScence("Sự Kiện");
        }
        if (event.getSource() == btnFigures) {
            renderNewScence("Nhân Vật Lịch Sử");
        }
        if (event.getSource() == btnExit) {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        }
    }
    private void renderNewScence(String buttonName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
            Parent root = loader.load();
            ChoiceBox<String> choiceBox = (ChoiceBox<String>) root.lookup("#choiceBox");
            choiceBox.setValue(buttonName);
            Scene currentScene = btnDynasty.getScene();
            Button btn = (Button) root.lookup("#exitBtn");
            currentScene.setRoot(root);
            SearchSceneController controller = loader.getController();
            controller.initialize(getClass().getResource("../fxml/SearchScene.fxml"), null);
            btn.setOnAction(event -> {
                try {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../fxml/MainScene.fxml"));
                    Parent root1 = loader1.load();
                    currentScene.setRoot(root1);
                    MainSceneController controller1 = loader1.getController();
                    controller1.initialize(getClass().getResource("../fxml/MainScene.fxml"), null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void renderNewScence(String buttonName) {
//        try {
//            FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
//            Parent root = newLoader.load();
//            SearchSceneController controller = newLoader.getController();
//            Scene currentScene = btnExit.getScene();
//            Stage stage = (Stage) currentScene.getWindow();
//            controller.setScene(currentScene);
//            Scene newScene = new Scene(root);
//            stage.setScene(newScene);
//            ChoiceBox<String> choiceBox = (ChoiceBox<String>) root.lookup("#choiceBox");
//            choiceBox.setValue(buttonName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}