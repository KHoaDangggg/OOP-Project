package controllers;

import application.app;
import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.CrawlAnhHung.DanhNhan;
import crawlData.CrawlNhanVat.CrawlAnhHung.AnhHungVuTrang;
import crawlData.CrawlNhanVat.NhaVat_VanSu.NhanVatVanSu;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import crawlData.CrawlNhanVat.trangnguyenbangnhan.src.DanhHieu;
import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import crawlData.info;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private Button exitBtn;
    @FXML
    private ListView<info> listView;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane imageContainer;
    @FXML
    public Button saveBtn;
    private Scene lastScene;

    private Stage stage;

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
                    if(selectedItem!=null) handleRenderTextArea(selectedItem, field, textFlow, imageContainer);
                });
            }
        }, 1);
    }

    public void handleRenderListView(String text) {
        //listView.getSelectionModel().selectFirst();
        Runnable runnable = () -> Platform.runLater(() -> {
            ObservableList<info> nameList = FXCollections.observableArrayList();
            for (info info : nameSelectedList) {
                if (removeVietnameseAccent(info.getTen()).contains(removeVietnameseAccent(text)) && !nameList.contains(info.getTen())) {
                    nameList.add(info);
                }
            }
            if(!nameList.isEmpty()) listView.setItems(nameList);
            else listView.setItems(null);
        });
        Thread thread = new Thread(runnable);
        thread.start();

    }

    public void handleRenderTextArea(info selectedItem, String field, TextFlow textFlow, ScrollPane imageContainer) {
        textFlow.getChildren().clear();
        if (field.equals("Triều Đại")) {
            handleRenderTrieuDai(selectedItem, textFlow);
        }
        if (field.equals("Di tích lịch sử")) {
            handleRenderDiTich(selectedItem, textFlow);
        }
        if (field.equals("Lễ Hội")) {
            handleRenderLeHoi(selectedItem, textFlow, imageContainer);
        }
        if (field.equals("Nhân Vật Lịch Sử")) {
            handleRenderAnhHung(selectedItem, textFlow);
        }
        if (field.equals("Sự Kiện")) {
            handleRenderSuKien(selectedItem, textFlow);
        }
    }

    public void handleRenderTrieuDai(info tenTrieuDai, TextFlow relative) {
        trieuDai selectedTrieuDai = (trieuDai) tenTrieuDai;
        StringBuilder vua = new StringBuilder();
        ArrayList<String> vuas = selectedTrieuDai.getKings();
        for (String s : vuas) {
            vua.append(s).append("\n");
        }
        relative.getChildren().add(new Text("Tên: " + selectedTrieuDai.getTen() + "\n" +
                "Năm bắt đầu - kết thúc: " + selectedTrieuDai.getNamBatDau() + " - " + selectedTrieuDai.getNamKetThuc() + "\n" +
                "Quốc hiệu: : " + selectedTrieuDai.getKinhDo() + "\n" +
                "Kinh đô: " + selectedTrieuDai.getKinhDo() + "\n" +
                "Vua: " + vua + "\n" +
                "Mô tả: : " + selectedTrieuDai.getMoTa() + "\n"));
        relative.getChildren().add(new Text("Sự kiện liên quan: "));
        if(selectedTrieuDai.getLienKetSuKien().keySet().size() == 0) {
            relative.getChildren().add(new Text("Không rõ"));
        }
        for (String sk : selectedTrieuDai.getLienKetSuKien().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text(", "));
            link.setOnAction(e -> handleLabel("Sự Kiện", selectedTrieuDai.getLienKetSuKien().get(sk), sk, link, relative.getScene()));
        }
        relative.getChildren().add(new Text("\nVua liên quan: "));
        if(selectedTrieuDai.getLienKetVua().keySet().size() == 0) {
            relative.getChildren().add(new Text("Không rõ"));
        }
        for (String sk : selectedTrieuDai.getLienKetVua().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text(", "));
            link.setOnAction(e -> handleLabel("Nhân Vật Lịch Sử", selectedTrieuDai.getLienKetVua().get(sk), sk, link, relative.getScene()));

        }
    }

    public void handleRenderSuKien(info tenSuKien, TextFlow relative1) {
        SuKienChienTranh selectedSuKien = (SuKienChienTranh) tenSuKien;
        relative1.getChildren().add(new Text("Tên sự kiện: " + selectedSuKien.getTen() + "\n" +
                "Thời gian: " + selectedSuKien.getThoiGian() + "\n" +
                "Địa điểm: " + selectedSuKien.getDiaDiem() + "\n" +
                "Nguyên nhân: " + selectedSuKien.getNguyenNhan() + "\n" +
                "Chỉ huy phe địch: " + selectedSuKien.getChiHuyPheDich() + "\n" +
                "Lực lượng phe địch: " + selectedSuKien.getLucLuongPheDich() + "\n" +
                "Phe địch: " + selectedSuKien.getPheDich() + "\n" +
                "Chỉ huy phe ta: " + selectedSuKien.getChiHuyPheTa() + "\n" +
                "Lực lượng phe ta: " + selectedSuKien.getLucLuongPheTa() + "\n" +
                "Phe ta: " + selectedSuKien.getPheTa() + "\n" +
                "Kết quả: " + selectedSuKien.getKetQua() + "\n" +
                    "Tổn thất địch: " + selectedSuKien.getTonThatDich() + "\n" +
                    "Tổn thất ta: " + selectedSuKien.getTonThatTa()));
            relative1.getChildren().add(new Text("\nNhân vật liên quan: "));
            if(selectedSuKien.getLienKetNhanVat().keySet().size() == 0) {
                relative1.getChildren().add(new Text("Không rõ"));
            }
            for (String nv : selectedSuKien.getLienKetNhanVat().keySet()) {
                Hyperlink link = new Hyperlink(nv);
                relative1.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> handleLabel("Nhân Vật Lịch Sử", selectedSuKien.getLienKetNhanVat().get(nv), nv, link, relative1.getScene()));
            }
        relative1.getChildren().add(new Text("\nTriều đại liên quan: "));
        if(selectedSuKien.getLienKetTrieuDai().keySet().size() == 0) {
            relative1.getChildren().add(new Text("Không rõ"));
        }
        for (String td : selectedSuKien.getLienKetTrieuDai().keySet()) {
            Hyperlink link = new Hyperlink(td);
            link.setOnAction(e -> handleLabel("Triều Đại", selectedSuKien.getLienKetTrieuDai().get(td), td, link, relative1.getScene()));
            relative1.getChildren().addAll(link, new Text(", "));
        }
    }

    public void handleRenderDiTich(info tenDiTich, TextFlow relative) {
        DiTichLichSu selectedDiTich = (DiTichLichSu) tenDiTich;
        relative.getChildren().add(new Text("Tên di tích: " + selectedDiTich.getTen() + "\n" +
                "Loại di tích: " + selectedDiTich.getLoaiDiTich() + "\n" +
                "Năm công nhận: " + selectedDiTich.getNamCongNhan() + "\n" +
                "Địa điểm : " + selectedDiTich.getDiaDiem() + "\n" +
                "Mô tả di tích: " + selectedDiTich.getMoTa() + "\n"));
    }

    public void handleRenderLeHoi(info tenLeHoi, TextFlow relative, ScrollPane imageContainer) {
        LeHoi selectedLeHoi = (LeHoi) tenLeHoi;
        relative.getChildren().add(new Text("Lễ hội liên quan: "));
        for (String sk : selectedLeHoi.getLienKetLeHoi().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text(", "));
            link.setOnAction(e -> handleLabel("Lễ Hội", selectedLeHoi.getLienKetLeHoi().get(sk), sk, link, relative.getScene()));
        }
        relative.getChildren().add(new Text("Tên lễ hội: " + selectedLeHoi.getTen() + "\n" +
                "Địa điểm: " + selectedLeHoi.getDiaDiem() + "\n" +
                "Thời gian: " + selectedLeHoi.getThoiGian() + "\n" +
                "Thông tin: " + selectedLeHoi.getThongTinLeHoi() + "\n"));
        VBox imageBox = new VBox();
        imageBox.setSpacing(10);

        // Create a new ImageView object for each image and add it to the VBox
        Platform.runLater(() -> {
            for (String link : selectedLeHoi.getLinkAnh()) {
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(link));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageBox.getChildren().add(imageView);
            }
            // Set the content of the ScrollPane to the VBox
            imageContainer.setContent(imageBox);
        });
    }

    public void handleRenderAnhHung(info tenNhanVat, TextFlow relative) {
        NhanVat selectedNhanVat = (NhanVat) tenNhanVat;
        if (selectedNhanVat instanceof Vua) {
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Tên húy: " + ((Vua) selectedNhanVat).getTen_huy() + "\n" +
                    "Miếu hiệu: " + ((Vua) selectedNhanVat).getMieu_hieu() + "\n" +
                    "Niên hiệu: " + ((Vua) selectedNhanVat).getNien_hieu() + "\n" +
                    "Thúy hiệu: " + ((Vua) selectedNhanVat).getThuy_hieu() + "\n" +
                    "Thế thứ: " + ((Vua) selectedNhanVat).getThe_thu() + "\n" +
                    "Năm trị vì: " + ((Vua) selectedNhanVat).getNam_bat_dau_tri_vi() + " - " + ((Vua) selectedNhanVat).getNam_ket_thuc_tri_vi() + "\n"));
        }
        if (selectedNhanVat instanceof AnhHungVuTrang) {
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((AnhHungVuTrang) selectedNhanVat).getQueQuan() + "\n" +
                    "Dân tộc: " + ((AnhHungVuTrang) selectedNhanVat).getDanToc() + "\n" +
                    "Năm sinh - năm mất: " + ((AnhHungVuTrang) selectedNhanVat).getNamSinhNamMat() + "\n" +
                    "Năm phong: " + ((AnhHungVuTrang) selectedNhanVat).getNamPhong() + "\n" +
                    "Tiểu sử: " + ((AnhHungVuTrang) selectedNhanVat).getTieuSu() + "\n"));
        }
        if (selectedNhanVat instanceof DanhNhan) {
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((DanhNhan) selectedNhanVat).getQueQuan() + "\n" +
                    "Tư liệu: " + ((DanhNhan) selectedNhanVat).gettuLieu() + "\n" +
                    "Tóm tắt: " + ((DanhNhan) selectedNhanVat).gettomTat() + "\n"));
        }
        if (selectedNhanVat instanceof DanhHieu) {
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Năm sinh - năm mất: " + ((DanhHieu) selectedNhanVat).getNam_sinh() + " - " + ((DanhHieu) selectedNhanVat).getNam_mat() + "\n" +
                    "Quê quán: " + ((DanhHieu) selectedNhanVat).getQue_quan() + "\n" +
                    "Danh hiệu: " + ((DanhHieu) selectedNhanVat).getDanh_hieu() + "\n" +
                    "Năm đỗ: " + ((DanhHieu) selectedNhanVat).getNam_do() + "\n" +
                    "Đời vua:" + ((DanhHieu) selectedNhanVat).getDoi_vua() + "\n"));
        }
        if (selectedNhanVat instanceof NhanVatLichSu) {
            HashMap<String, String> thongTin = ((NhanVatLichSu) selectedNhanVat).getThongTin();
            HashMap<String, String> thongTinCoBan = ((NhanVatLichSu) selectedNhanVat).getThongTinCoBan();
            StringBuilder t = new StringBuilder();
            if (thongTin != null) {
                for (Map.Entry<String, String> entry : thongTin.entrySet()) {
                    t.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            }
            if (thongTinCoBan != null) {
                for (Map.Entry<String, String> entry : thongTinCoBan.entrySet()) {
                    t.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            }
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Miêu tả: " + ((NhanVatLichSu) selectedNhanVat).getMieuTa() + "\n" +
                    t));
            relative.getChildren().add(new Text("Sự kiện liên quan: "));
            if(selectedNhanVat.getLienKetSuKien().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetSuKien().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> handleLabel("Sự Kiện", selectedNhanVat.getLienKetSuKien().get(sk), sk, link, relative.getScene()));

            }
            relative.getChildren().add(new Text("\n"));
            relative.getChildren().add(new Text("Triều đại liên quan: "));
            if(selectedNhanVat.getLienKetTrieuDai().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetTrieuDai().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> handleLabel("Triều Đại", selectedNhanVat.getLienKetTrieuDai().get(sk), sk, link, relative.getScene()));

            }
            relative.getChildren().add(new Text("\n"));
        }

        if (selectedNhanVat instanceof NhanVatVanSu) {
            HashMap<String, String> thongTin = ((NhanVatVanSu) selectedNhanVat).getAtt();
            StringBuilder t = new StringBuilder();
            if (thongTin != null) {
                for (Map.Entry<String, String> entry : thongTin.entrySet()) {
                    t.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            }
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" + t));
            relative.getChildren().add(new Text("Sự kiện liên quan: "));
            if(selectedNhanVat.getLienKetSuKien().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetSuKien().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> handleLabel("Sự Kiện", selectedNhanVat.getLienKetSuKien().get(sk), sk, link, relative.getScene()));

            }
            relative.getChildren().add(new Text("\n"));
            relative.getChildren().add(new Text("Triều đại liên quan: "));
            if(selectedNhanVat.getLienKetTrieuDai().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetTrieuDai().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> handleLabel("Triều Đại", selectedNhanVat.getLienKetTrieuDai().get(sk), sk, link, relative.getScene()));
            }
            relative.getChildren().add(new Text("\n"));
        }
    }

    public void handleLabel(String field, info info, String text, Hyperlink prelink, Scene currentScene) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
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



    private void handlePreviousButton(Scene baseScene) throws IOException {
        stage.setScene(baseScene);
    }

    public void handleExitButton() throws IOException {
        stage.setScene(lastScene);
    }

    @FXML
    private void saveInfo() {
        info selectedItem = listView.getSelectionModel().getSelectedItem();
        if (!mainSceneController.saveData.contains(selectedItem) && selectedItem != null) {
            mainSceneController.saveData.add(selectedItem);
            System.out.println("Save " + selectedItem.getTen() + " to favorite!");
        } else if (mainSceneController.saveData.contains(selectedItem) && selectedItem != null)
            System.out.println("Already save " + selectedItem.getTen() + " to favorite");
    }
}
