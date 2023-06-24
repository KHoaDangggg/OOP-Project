package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {
    @FXML
    public WebView webView;
    @FXML
    public Button backBtn;

    private Scene lastScene;

    public void setLastScene(Scene lastScene) {
        this.lastScene = lastScene;
    }

    public void openWebPane(){
        WebEngine engine = webView.getEngine();
        engine.load("https://www.google.com/");
    }

    @FXML
    public void back(){
        Scene scene = backBtn.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.setScene(lastScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            openWebPane();
    }
}
