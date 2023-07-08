package controllers;

import application.app;
import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import crawlData.info;
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
import static controllers.textAreaController.handleRenderTextArea;
import static controllers.utils.removeVietnameseAccent;

public class searchSceneController implements Initializable {
    public Button backBtn;
    @FXML
    private Label title;
    @FXML
    private TextField textField;
    private static ObservableList<SuKienChienTranh> suKienList = FXCollections.observableArrayList();
    private static ObservableList<DiTichLichSu> diTichList = FXCollections.observableArrayList();
    private static ObservableList<LeHoi> leHoiList = FXCollections.observableArrayList();
    private static ObservableList<NhanVat> nhanVatList = FXCollections.observableArrayList();
    private final ObservableList<info> nameSelectedList = FXCollections.observableArrayList();
    private static ObservableList<trieuDai> trieuDaiList = FXCollections.observableArrayList();
    @FXML
    ListView<info> listView;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane imageContainer;
    @FXML
    public Button saveBtn;
    static Scene lastScene;
    static Stage stage;

    public void setLastScene(Scene lastScene) {
        this.lastScene = lastScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trieuDaiList = FXCollections.observableList(app.listTrieuDai);
        suKienList = FXCollections.observableList(app.listSuKien);
        diTichList = FXCollections.observableList(app.listDiTich);
        leHoiList = FXCollections.observableList(app.listLeHoi);
        nhanVatList = FXCollections.observableList(app.listNhanVat);
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
                    protected void updateItem(info item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null || item.getTen() == null) {
                            setText(null);
                        } else {
                            setText(item.getTen());
                        }
                    }

                });
                textField.textProperty().addListener((obs, oldText, newText) -> handleRenderListView(newText));
                listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    info selectedItem = listView.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) handleRenderTextArea(selectedItem, field, textFlow, imageContainer);
                });
                saveBtn.setOnAction(e -> {
                    saveInfo();
                });
            }
        }, 1);
    }

    public void handleRenderListView(String text) {
        Runnable runnable = () -> Platform.runLater(() -> {
            ObservableList<info> nameList = FXCollections.observableArrayList();
            for (info information : nameSelectedList) {
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
        info selectedItem = listView.getSelectionModel().getSelectedItem();
        if (!mainSceneController.saveData.contains(selectedItem) && selectedItem != null) {
            mainSceneController.saveData.add(selectedItem);
            System.out.println("Save " + selectedItem.getTen() + " to favorite!");
        } else if (mainSceneController.saveData.contains(selectedItem) && selectedItem != null)
            System.out.println("Already save " + selectedItem.getTen() + " to favorite");
    }
}

