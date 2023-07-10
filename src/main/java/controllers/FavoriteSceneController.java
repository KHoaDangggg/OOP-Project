package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.DuLieuLichSu;
import model.ditich.DiTichLichSu;
import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.Utils.removeVietnameseAccent;

public class FavoriteSceneController implements Initializable {
    private ArrayList<DuLieuLichSu> duLieuLichSus = new ArrayList<>();
    @FXML
    ListView<DuLieuLichSu> listView;
    @FXML
    private Label title;
    @FXML
    private TextField textField;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane imageContainer;
    @FXML
    private Button backBtn;
    private final Scene scene;

    public FavoriteSceneController(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void backScene() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        duLieuLichSus = MainSceneController.saveData;
        ObservableList<DuLieuLichSu> favorInfoList = FXCollections.observableList(duLieuLichSus);
        textField.textProperty().addListener((obs, oldText, newText) -> handleRenderListView(newText));
        listView.setItems(favorInfoList);
        //System.out.println(nameSelectedList);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(DuLieuLichSu item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getTen() == null) {
                    setText(null);
                } else {
                    setText(item.getTen());
                }
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            DuLieuLichSu selectedItem = listView.getSelectionModel().getSelectedItem();
            String field = null;
            if (selectedItem instanceof TrieuDai) {
                field = "Triều Đại";
            }
            if (selectedItem instanceof DiTichLichSu) {
                field = "Di tích lịch sử";
            }
            if (selectedItem instanceof LeHoi) {
                field = "Lễ Hội";
            }
            if (selectedItem instanceof NhanVat) {
                field = "Nhân Vật Lịch Sử";
            }
            if (selectedItem instanceof SuKienLichSu) {
                field = "Sự Kiện";
            }
            title.setText(field);
            if (selectedItem != null && field != null)
                TextAreaController.handleRenderTextArea(selectedItem, field, textFlow, imageContainer);
        });
    }

    public void handleRenderListView(String text) {
        Runnable runnable = () -> Platform.runLater(() -> {
            ObservableList<DuLieuLichSu> nameList = FXCollections.observableArrayList();
            for (DuLieuLichSu information : duLieuLichSus) {
                if (removeVietnameseAccent(information.getTen()).contains(removeVietnameseAccent(text)) && !nameList.contains(information)) {
                    nameList.add(information);
                }
            }
            if (!nameList.isEmpty()) {
                listView.setItems(nameList);
            } else {
                listView.setItems(null);
            }
        });
        Thread thread = new Thread(runnable);
        thread.start();
    }

}