package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainSceneController {

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
            renderNewScence("Nhân vật lịch sử");
        }
        if (event.getSource() == btnExit) {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        }
    }
    private void renderNewScence(String buttonName) {
        try {
            FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
            Parent root = newLoader.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            ChoiceBox<String> choiceBox = (ChoiceBox<String>) root.lookup("#choiceBox");
            choiceBox.setValue(buttonName);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}