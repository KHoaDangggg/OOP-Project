package Controllers;

import Application.app;
import CrawlData.CrawlDiTich.DiTichLichSu;
import CrawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import CrawlData.CrawlNhanVat.CrawlAnhHung.DanhNhan;
import CrawlData.CrawlNhanVat.CrawlAnhHung.anhHungVuTrang;
import CrawlData.CrawlNhanVat.NhanVat;
import CrawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import CrawlData.CrawlNhanVat.trangnguyenbangnhan.src.DanhHieu;
import CrawlData.CrawlNhanVat.vua.src.Vua;
import CrawlData.CrawlSuKien.SuKienChienTranh;
import CrawlData.CrawlTrieuDai.TrieuDai;
import javafx.beans.value.ChangeListener;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static Controllers.Utils.removeVietnameseAccent;

public class SearchSceneController implements Initializable {
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField textField;
    private static ObservableList<SuKienChienTranh> suKienList = FXCollections.observableArrayList();
    private static ObservableList<DiTichLichSu> diTichList = FXCollections.observableArrayList();
    private static ObservableList<LeHoi> leHoiList = FXCollections.observableArrayList();
    private static ObservableList<NhanVat> nhanVatList = FXCollections.observableArrayList();
    private final ObservableList<String> nameSelectedList = FXCollections.observableArrayList();
    private static ObservableList<TrieuDai> trieuDaiList = FXCollections.observableArrayList();
    @FXML
    private Button exitBtn;
    @FXML
    private ListView listView;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane imageContainer;

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
                String field = (String) choiceBox.getValue();
                if (field.equals("Triều Đại")) {
                    for (TrieuDai t : trieuDaiList) {
                        nameSelectedList.add(t.getTen());
                    }
                }
                if (field.equals("Di tích lịch sử")) {
                    for (DiTichLichSu t : diTichList) {
                        nameSelectedList.add(t.getTenDiTich());
                    }
                }
                if (field.equals("Lễ Hội")) {
                    for (LeHoi t : leHoiList) {
                        nameSelectedList.add(t.getTen());
                    }
                }
                if (field.equals("Nhân Vật Lịch Sử")) {
                    for (NhanVat n : nhanVatList) {
                        nameSelectedList.add(n.getTen());
                    }
                }
                if (field.equals("Sự Kiện")) {
                    for (SuKienChienTranh t : suKienList) {
                        nameSelectedList.add(t.getTenSuKien());
                    }
                }
                textField.textProperty().addListener((obs, oldText, newText) -> {
                    handleRenderListView();
                });
                listView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                    String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                    handleRenderTextArea(selectedItem, field, textFlow);
                });
            }
        }, 100);
    }
    public void handleRenderListView() {
        String searchString = textField.getText();
        boolean found = false;
        ObservableList<String> nameList = FXCollections.observableArrayList();
        for(String str: nameSelectedList) {
            if (removeVietnameseAccent(str).contains(removeVietnameseAccent(searchString))
                    && !nameList.contains(str)) {
                nameList.add(str);
                found = true;
            }
        }
        listView.setItems(null);
        if (found) {
            listView.setItems(nameList);
        }
    }

    public void handleRenderTextArea(String selectedItem, String field, TextFlow relative1) {
        relative1.getChildren().clear();
        if (field.equals("Triều Đại")) {
            handleRenderTrieuDai(selectedItem, relative1);
        }
        if (field.equals("Di tích lịch sử")) {
            handleRenderDiTich(selectedItem, relative1);
        }
        if (field.equals("Lễ Hội")) {
            handleRenderLeHoi(selectedItem, relative1);
        }
        if (field.equals("Nhân Vật Lịch Sử")) {
            handleRenderAnhHung(selectedItem, relative1);
        }
        if (field.equals("Sự Kiện")) {
            handleRenderSuKien(selectedItem, relative1);
        }
    }

    public void handleRenderTrieuDai(String tenTrieuDai, TextFlow relative) {
        TrieuDai selectedTrieuDai = null;
        if (tenTrieuDai != null) {
            for (TrieuDai t : trieuDaiList) {
                if (removeVietnameseAccent(tenTrieuDai).equals(removeVietnameseAccent(t.getTen()))) {
                    selectedTrieuDai = t;
                }
            }
            StringBuffer vua = new StringBuffer();
            ArrayList<String> vuas = selectedTrieuDai.getKings();
            if (vuas.size() >= 0) {
                for (int i = 0; i < vuas.size(); i++) {
                    vua.append(vuas.get(i) + "\n");
                }
            }
            relative.getChildren().add(new Text("Tên: " + selectedTrieuDai.getTen() + "\n" +
                    "Năm bắt đầu - kết thúc: " + selectedTrieuDai.getNamBatDau() + " - " + selectedTrieuDai.getNamKetThuc() + "\n" +
                    "Quốc hiệu: : " + selectedTrieuDai.getKinhDo() + "\n" +
                    "Kinh đô: " + selectedTrieuDai.getKinhDo() + "\n" +
                    "Vua: " + vua + "\n" +
                    "Mô tả: : " + selectedTrieuDai.getMoTa() + "\n"));
            relative.getChildren().add(new Text("Sự kiện liên quan: "));
            for (String sk : selectedTrieuDai.getLienKetSuKien().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> {
                    handleLabel("Sự Kiện", sk, link);
                });
            }
            relative.getChildren().add(new Text("\nVua liên quan: "));
            for (String sk : selectedTrieuDai.getLienKetVua().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> {
                    handleLabel("Nhân Vật Lịch Sử", sk, link);
                });

            }
        }
    }

    public void handleRenderSuKien(String tenSuKien, TextFlow relative1) {
        SuKienChienTranh selectedSuKien = null;
        if (tenSuKien != null) {
            for (SuKienChienTranh t : suKienList) {
                if (tenSuKien.equals(t.getTenSuKien())) {
                    selectedSuKien = t;
                }
            }
            relative1.getChildren().add(new Text("Tên sự kiện: " + selectedSuKien.getTenSuKien() + "\n" +
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
            for (String nv : selectedSuKien.getLienKetNhanVat().keySet()) {
                Hyperlink link = new Hyperlink(nv);
                relative1.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> {
                    handleLabel("Nhân Vật Lịch Sử", nv, link);
                });
            }
            relative1.getChildren().add(new Text("\nTriều đại liên quan: "));
            for (String td : selectedSuKien.getLienKetTrieuDai().keySet()) {
                Hyperlink link = new Hyperlink(td);
                link.setOnAction(e -> {
                    handleLabel("Triều Đại", td, link);
                });
                relative1.getChildren().addAll(link, new Text(", "));
            }
        }
    }

    public void handleRenderDiTich(String tenDiTich, TextFlow relative) {
        DiTichLichSu selectedDiTich = null;
        if (tenDiTich != null) {
            for (DiTichLichSu t : diTichList) {
                if (tenDiTich.equals(t.getTenDiTich())) {
                    selectedDiTich = t;
                }
            }
        }
        relative.getChildren().add(new Text("Tên di tích: " + selectedDiTich.getTenDiTich() + "\n" +
                "Loại di tích: " + selectedDiTich.getLoaiDiTich() + "\n" +
                "Năm công nhận: " + selectedDiTich.getNamCongNhan() + "\n" +
                "Địa điểm : " + selectedDiTich.getDiaDiem() + "\n" +
                "Mô tả di tích: " + selectedDiTich.getMoTa() + "\n"));
    }

    public void handleRenderLeHoi(String tenLeHoi, TextFlow relative) {
        LeHoi selectedLeHoi = null;
        if (tenLeHoi != null){
            for(LeHoi l: leHoiList){
                if(tenLeHoi.equals(l.getTen())) selectedLeHoi = l;
            }
        }
            relative.getChildren().add(new Text("Lễ hội liên quan: "));
        for (String sk : selectedLeHoi.getLienKetLeHoi().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text(", "));
            link.setOnAction(e -> {
                handleLabel("Lễ Hội", sk, link);
            });
        }
        if (selectedLeHoi == null) return;
        relative.getChildren().add(new Text("Tên lễ hội: " + selectedLeHoi.getTen() + "\n" +
                "Địa điểm: " + selectedLeHoi.getDiaDiem() + "\n" +
                "Thời gian: " + selectedLeHoi.getThoiGian() + "\n" +
                "Thông tin: " + selectedLeHoi.getThongTinLeHoi() + "\n"));
        VBox imageBox = new VBox();
        imageBox.setSpacing(10);

        // Create a new ImageView object for each image and add it to the VBox
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
    }

    public void handleRenderAnhHung(String tenNhanVat, TextFlow relative) {
        NhanVat selectedNhanVat = null;
        if (tenNhanVat != null) {
            for (NhanVat t : nhanVatList) {
                if (tenNhanVat.equals(t.getTen())) {
                    selectedNhanVat = t;
                }
            }
        }
        if (selectedNhanVat instanceof Vua) {
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Tên húy: " + ((Vua) selectedNhanVat).getTen_huy() + "\n" +
                    "Miếu hiệu: " + ((Vua) selectedNhanVat).getMieu_hieu() + "\n" +
                    "Niên hiệu: " + ((Vua) selectedNhanVat).getNien_hieu() + "\n" +
                    "Thúy hiệu: " + ((Vua) selectedNhanVat).getThuy_hieu() + "\n" +
                    "Thế thứ: " + ((Vua) selectedNhanVat).getThe_thu() + "\n" +
                    "Năm trị vì: " + ((Vua) selectedNhanVat).getNam_bat_dau_tri_vi() + " - " + ((Vua) selectedNhanVat).getNam_ket_thuc_tri_vi() + "\n"));
        }
        if (selectedNhanVat instanceof anhHungVuTrang) {
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((anhHungVuTrang) selectedNhanVat).getQueQuan() + "\n" +
                    "Dân tộc: " + ((anhHungVuTrang) selectedNhanVat).getDanToc() + "\n" +
                    "Năm sinh - năm mất: " + ((anhHungVuTrang) selectedNhanVat).getNamSinhNamMat() + "\n" +
                    "Năm phong: " + ((anhHungVuTrang) selectedNhanVat).getNamPhong() + "\n" +
                    "Tiểu sử: " + ((anhHungVuTrang) selectedNhanVat).getTieuSu() + "\n"));
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
            StringBuffer t = new StringBuffer();
            if (thongTin != null) {
                for (Map.Entry<String, String> entry : thongTin.entrySet()) {
                    t.append(entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
            if (thongTinCoBan != null) {
                for (Map.Entry<String, String> entry : thongTinCoBan.entrySet()) {
                    t.append(entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Miêu tả: " + ((NhanVatLichSu) selectedNhanVat).getMieuTa() + "\n" +
                    t));
            relative.getChildren().add(new Text("Sự kiện liên quan: "));
            for (String sk : selectedNhanVat.getLienKetSuKien().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> {
                    handleLabel("Sự Kiện", sk, link);
                });

            }
            relative.getChildren().add(new Text("Triều đại liên quan: "));
            for (String sk : selectedNhanVat.getLienKetTrieuDai().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text(", "));
                link.setOnAction(e -> {
                    handleLabel("Triều Đại", sk, link);
                });

            }
        }
    }

    public void handleLabel(String field, String text, Hyperlink prelink) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
            Parent root = loader.load();
            ChoiceBox<String> choiceBox1 = (ChoiceBox<String>) root.lookup("#choiceBox");
            choiceBox1.setValue(field);
            TextField textField1 = (TextField) root.lookup("#textField");
            textField1.setText(text);
            Map<String, Object> namespace = loader.getNamespace();
            TextFlow textFlow1 = (TextFlow) namespace.get("textFlow");
            handleRenderTextArea(text, field, textFlow1);
            Button returnBtn = (Button) root.lookup("#backBtn");
            returnBtn.setOnAction(event -> {
                try {
                    handleBackButton(returnBtn, (String) listView.getSelectionModel().getSelectedItem(), (String) choiceBox.getValue());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Scene currentScene = prelink.getScene();
            currentScene.setRoot(root);
            SearchSceneController controller = loader.getController();
            controller.initialize(getClass().getResource("../fxml/SearchScene.fxml"), null);
            Button exitBtn = (Button) root.lookup("#exitBtn");
            exitBtn.setOnAction(event -> {
                try {
                    handleExitButton(currentScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBackButton(Button btn, String text, String field) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SearchScene.fxml"));
        Parent root = loader.load();
        ChoiceBox<String> choiceBox = (ChoiceBox<String>) root.lookup("#choiceBox");
        choiceBox.setValue(field);
        TextField textField1 = (TextField) root.lookup(("#textField"));
        textField1.setText(text);
        Map<String, Object> namespace = loader.getNamespace();
        TextFlow textFlow1 = (TextFlow) namespace.get("textFlow");
        handleRenderTextArea(text, field, textFlow1);
        Scene currentScene = btn.getScene();
        currentScene.setRoot(root);
        SearchSceneController controller = loader.getController();
        controller.initialize(getClass().getResource("../fxml/SearchScene.fxml"), null);

        Button exitBtn = (Button) root.lookup("#exitBtn");
        exitBtn.setOnAction(event -> {
            try {
                handleExitButton(currentScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void handleExitButton(Scene currentScene) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainScene.fxml"));
        Parent root = loader.load();
        currentScene.setRoot(root);
        MainSceneController controller = loader.getController();
        controller.initialize(getClass().getResource("../fxml/MainScene.fxml"), null);
    }
}
