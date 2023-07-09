package controllers;

import crawl_data.DuLieuLichSu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import java.io.IOException;
import java.util.Map;

import static controllers.SearchSceneController.*;
import static controllers.TextAreaController.handleRenderTextArea;

public class ButtonsController {
    static void handlePreviousButton(Scene baseScene) throws IOException {
        stage.setScene(baseScene);
    }

    static void handleExitButton() throws IOException {
        stage.setScene(lastScene);
    }

    static void handleLabel(String field, DuLieuLichSu duLieuLichSu, String text, Scene currentScene) {
        try {
            FXMLLoader loader = new FXMLLoader(SearchSceneController.class.getResource("../fxml/SearchScene.fxml"));
            Parent root = loader.load();
            SearchSceneController controller = loader.getController();
            controller.setStage(stage);
            controller.setLastScene(lastScene);
            Button saveBtn = (Button) root.lookup("#save");
            saveBtn.setOnAction(event -> {
                if (!MainSceneController.saveData.contains(duLieuLichSu) && duLieuLichSu != null) {
                    MainSceneController.saveData.add(duLieuLichSu);
                    System.out.println("Save " + duLieuLichSu.getTen() + " to favorite!");
                } else if (MainSceneController.saveData.contains(duLieuLichSu) && duLieuLichSu != null)
                    System.out.println("Already save " + duLieuLichSu.getTen() + " to favorite");
            });
            Label label = (Label) root.lookup("#title");
            label.setText(field);
            TextField textField1 = (TextField) root.lookup("#textField");
            textField1.setText(text);
            Map<String, Object> namespace = loader.getNamespace();
            TextFlow textFlow1 = (TextFlow) namespace.get("textFlow");
            ScrollPane pane = (ScrollPane) namespace.get("imageContainer");
            handleRenderTextArea(duLieuLichSu, field, textFlow1, pane);
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
