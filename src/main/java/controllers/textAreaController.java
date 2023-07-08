package controllers;

import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.NhaVat_VanSu.NhanVatVanSu;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlNhanVat.NhanVat_NguoiKeSu.NhanVatLichSu;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;
import crawlData.info;
import javafx.application.Platform;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.HashMap;
import java.util.Map;

import static controllers.buttonsController.handleLabel;

public class textAreaController {
    public static void handleRenderTextArea(info selectedItem, String field, TextFlow textFlow, ScrollPane imageContainer) {
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

    public static void handleRenderTrieuDai(info tenTrieuDai, TextFlow relative) {
        trieuDai selectedTrieuDai = (trieuDai) tenTrieuDai;
        relative.getChildren().add(new Text(utils.textTrieuDai(selectedTrieuDai)));
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

    public static void handleRenderSuKien(info tenSuKien, TextFlow relative1) {
        SuKienChienTranh selectedSuKien = (SuKienChienTranh) tenSuKien;
        relative1.getChildren().add(new Text(utils.textSuKien(selectedSuKien)));
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

    public static void handleRenderDiTich(info tenDiTich, TextFlow relative) {
        DiTichLichSu selectedDiTich = (DiTichLichSu) tenDiTich;
        relative.getChildren().add(new Text(utils.textDiTich(selectedDiTich)));
    }

    public static void handleRenderLeHoi(info tenLeHoi, TextFlow relative, ScrollPane imageContainer) {
        LeHoi selectedLeHoi = (LeHoi) tenLeHoi;
        relative.getChildren().add(new Text(utils.textLeHoi(selectedLeHoi)));
        relative.getChildren().add(new Text("Lễ hội liên quan: "));
        for (String sk : selectedLeHoi.getLienKetLeHoi().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text(", "));
            link.setOnAction(e -> handleLabel("Lễ Hội", selectedLeHoi.getLienKetLeHoi().get(sk), sk, link, relative.getScene()));
        }
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

    public static void handleRenderAnhHung(info tenNhanVat, TextFlow relative) {
        NhanVat selectedNhanVat = (NhanVat) tenNhanVat;
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
        else if (selectedNhanVat instanceof NhanVatVanSu) {
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
        else {
            relative.getChildren().add(new Text(utils.textNhanVat(selectedNhanVat)));
        }

    }
}
