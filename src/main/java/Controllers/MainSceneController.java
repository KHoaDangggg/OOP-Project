package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        if (event.getSource() == btnDynasty
                || event.getSource() == btnRelic
                || event.getSource() == btnFestival
                || event.getSource() == btnEvent
                || event.getSource() == btnFigures) {
                renderNewScence();
            System.out.println("btnDynasty clicked");
        } else if (event.getSource() == btnExit) {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
            System.out.println("btnExit clicked");
        }
    }
    private void renderNewScence() {
        try {
            FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
            Scene newScene = new Scene(newLoader.load());
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}