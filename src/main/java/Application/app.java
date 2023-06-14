package Application;

import Controllers.object_for_ui.GenerateUIObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
@SuppressWarnings("unused")
public class app extends Application {
    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws IOException {

        //Load object for ui
        GenerateUIObject.gen();

        //Main scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/MainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Lịch sử Việt Nam");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
