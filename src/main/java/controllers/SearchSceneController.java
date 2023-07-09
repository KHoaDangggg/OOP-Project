package controllers;

import application.App;
import model.DuLieuLichSu;
import model.ditich.DiTich;
import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;

//import static controllers.buttonsController.saveInfo;
import static controllers.TextAreaController.handleRenderTextArea;
import static controllers.Utils.removeVietnameseAccent;

public class SearchSceneController implements Initializable {
    public Button backBtn;
    @FXML
    private Label title;
    @FXML
    private TextField textField;
    private static ObservableList<SuKienLichSu> suKienList = FXCollections.observableArrayList();
    private static ObservableList<DiTich> diTichList = FXCollections.observableArrayList();
    private static ObservableList<LeHoi> leHoiList = FXCollections.observableArrayList();
    private static ObservableList<NhanVat> nhanVatList = FXCollections.observableArrayList();
    private final ObservableList<DuLieuLichSu> nameSelectedList = FXCollections.observableArrayList();
    private static ObservableList<TrieuDai> trieuDaiList = FXCollections.observableArrayList();
    @FXML
    ListView<DuLieuLichSu> listView;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane imageContainer;
    @FXML
    public Button saveBtn;
    static Scene lastScene;
    static Stage stage;

    public void setLastScene(Scene lastScene) {
        SearchSceneController.lastScene = lastScene;
    }

    public void setStage(Stage stage) {
        SearchSceneController.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trieuDaiList = FXCollections.observableList(App.listTrieuDai);
        suKienList = FXCollections.observableList(App.listSuKien);
        diTichList = FXCollections.observableList(App.listDiTich);
        leHoiList = FXCollections.observableList(App.listLeHoi);
        nhanVatList = FXCollections.observableList(App.listNhanVat);
        nameSelectedList.clear();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String field = title.getText();
                if (field.equals("Triều Đại")) {
                    nameSelectedList.addAll(trieuDaiList);
                }
                if (field.equals("Di tích lịch sử")) {
                    nameSelectedList.addAll(diTichList);
                }
                if (field.equals("Lễ Hội")) {
                    nameSelectedList.addAll(leHoiList);
                }
                if (field.equals("Nhân Vật Lịch Sử")) {
                    nameSelectedList.addAll(nhanVatList);
                }
                if (field.equals("Sự Kiện")) {
                    nameSelectedList.addAll(suKienList);
                }
                listView.setItems(nameSelectedList);
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
                //imageContainer = (ScrollPane) imageContainer.getScene().getRoot().lookup("#imageCon");
                textField.textProperty().addListener((obs, oldText, newText) -> handleRenderListView(newText));
                listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    DuLieuLichSu selectedItem = listView.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) handleRenderTextArea(selectedItem, field, textFlow, imageContainer);
                });
                saveBtn.setOnAction(e -> saveInfo());
            }
        }, 1);
    }

    public void handleRenderListView(String text) {
        Runnable runnable = () -> Platform.runLater(() -> {
            ObservableList<DuLieuLichSu> nameList = FXCollections.observableArrayList();
            for (DuLieuLichSu information : nameSelectedList) {
                if (removeVietnameseAccent(information.getTen()).contains(removeVietnameseAccent(text)) && !nameList.contains(information)) {
                    nameList.add(information);
                }
            }
            if (!nameList.isEmpty()) {
                listView.setItems(nameList);
            }
            else {
                listView.setItems(null);
            }
        });
        Thread thread = new Thread(runnable);
        thread.start();
    }

    void saveInfo() {
        DuLieuLichSu selectedItem = listView.getSelectionModel().getSelectedItem();
        if (!MainSceneController.saveData.contains(selectedItem) && selectedItem != null) {
            MainSceneController.saveData.add(selectedItem);
            System.out.println("Save " + selectedItem.getTen() + " to favorite!");
        } else if (MainSceneController.saveData.contains(selectedItem) && selectedItem != null)
            System.out.println("Already save " + selectedItem.getTen() + " to favorite");
    }
}

