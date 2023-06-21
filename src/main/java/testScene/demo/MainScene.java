package testScene.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene {
    @FXML
    protected Button button;

    @FXML
    public void openWebPage(){
        Scene mainScene = button.getScene();
        Stage stage = (Stage) mainScene.getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/webView.fxml"));
        try {
            Pane root = fxmlLoader.load();
            webViewController controller = fxmlLoader.getController();
            controller.setLastScene(mainScene);
            controller.openWebPane();
            Scene scene = new Scene(root, 320, 240);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
