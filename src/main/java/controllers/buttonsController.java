package controllers;

import crawlData.info;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Map;

import static controllers.searchSceneController.*;
import static controllers.textAreaController.handleRenderTextArea;

public class buttonsController {
    static void handlePreviousButton(Scene baseScene) throws IOException {
        stage.setScene(baseScene);
    }

    static void handleExitButton() throws IOException {
        stage.setScene(lastScene);
    }

    static void handleLabel(String field, info info, String text, Hyperlink prelink, Scene currentScene) {
        try {
            FXMLLoader loader = new FXMLLoader(searchSceneController.class.getResource("../fxml/SearchScene.fxml"));
            Parent root = loader.load();
            searchSceneController controller = loader.getController();
            controller.setStage(stage);
            controller.setLastScene(lastScene);
            Button saveBtn = (Button) root.lookup("#save");
            saveBtn.setOnAction(event -> {
                if (!mainSceneController.saveData.contains(info) && info != null) {
                    mainSceneController.saveData.add(info);
                    System.out.println("Save " + info.getTen() + " to favorite!");
                } else if (mainSceneController.saveData.contains(info) && info != null)
                    System.out.println("Already save " + info.getTen() + " to favorite");
            });
            Label label = (Label) root.lookup("#title");
            label.setText(field);
            TextField textField1 = (TextField) root.lookup("#textField");
            textField1.setText(text);
            Map<String, Object> namespace = loader.getNamespace();
            TextFlow textFlow1 = (TextFlow) namespace.get("textFlow");
            ScrollPane pane = (ScrollPane) namespace.get("imageContainer");
            handleRenderTextArea(info, field, textFlow1, pane);
            Button backBtn = (Button) root.lookup("#backBtn");
            backBtn.setOnAction(event -> {
                try {
                    handlePreviousButton(currentScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Button exitBtn = (Button) root.lookup("#exitBtn");
            exitBtn.setOnAction(event -> {
                try {
                    handleExitButton();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
