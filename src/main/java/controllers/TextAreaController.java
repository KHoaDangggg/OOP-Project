package controllers;

import javafx.application.Platform;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.DuLieuLichSu;
import model.ditich.DiTichLichSu;
import model.ditich.DiTichVN;
import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.nhanvat.NhanVatLichSu;
import model.nhanvat.NhanVatVanSu;
import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static controllers.ButtonsController.handleLabel;
import static controllers.Utils.loadImage;

public class TextAreaController {

    public static void handleRenderTextArea(DuLieuLichSu selectedItem, String field, TextFlow textFlow, ScrollPane imageContainer) {
        textFlow.getChildren().clear();
        if (field.equals("Triều Đại")) {
            handleRenderTrieuDai(selectedItem, textFlow);
        }
        if (field.equals("Di tích lịch sử")) {
            handleRenderDiTich(selectedItem, textFlow, imageContainer);
        }
        if (field.equals("Lễ Hội")) {
            handleRenderLeHoi(selectedItem, textFlow, imageContainer);
        }
        if (field.equals("Nhân Vật Lịch Sử")) {
            handleRenderNhanVat(selectedItem, textFlow);
        }
        if (field.equals("Sự Kiện")) {
            handleRenderSuKien(selectedItem, textFlow);
        }
    }

    public static void handleRenderTrieuDai(DuLieuLichSu tenTrieuDai, TextFlow relative) {
        TrieuDai selectedTrieuDai = (TrieuDai) tenTrieuDai;
        relative.getChildren().add(new Text(Utils.textTrieuDai(selectedTrieuDai)));
        relative.getChildren().add(new Text("Sự kiện liên quan: "));
        if (selectedTrieuDai.getLienKetSuKien().keySet().size() == 0) {
            relative.getChildren().add(new Text("Không rõ"));
        }
        for (String sk : selectedTrieuDai.getLienKetSuKien().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text("\n"));
            link.setOnAction(e -> handleLabel("Sự Kiện", selectedTrieuDai.getLienKetSuKien().get(sk), sk, relative.getScene()));
        }
        relative.getChildren().add(new Text("\nVua liên quan: "));
        if (selectedTrieuDai.getLienKetVua().keySet().size() == 0) {
            relative.getChildren().add(new Text("Không rõ"));
        }
        for (String sk : selectedTrieuDai.getLienKetVua().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text("\n"));
            link.setOnAction(e -> handleLabel("Nhân Vật Lịch Sử", selectedTrieuDai.getLienKetVua().get(sk), sk, relative.getScene()));
        }
    }

    public static void handleRenderSuKien(DuLieuLichSu tenSuKien, TextFlow relative1) {
        SuKienLichSu selectedSuKien = (SuKienLichSu) tenSuKien;
        relative1.getChildren().add(new Text(Utils.textSuKien(selectedSuKien)));
        relative1.getChildren().add(new Text("\nNhân vật liên quan: "));
        if (selectedSuKien.getLienKetNhanVat().keySet().size() == 0) {
            relative1.getChildren().add(new Text("Không rõ"));
        }
        for (String nv : selectedSuKien.getLienKetNhanVat().keySet()) {
            Hyperlink link = new Hyperlink(nv);
            relative1.getChildren().addAll(link, new Text("\n"));
            link.setOnAction(e -> handleLabel("Nhân Vật Lịch Sử", selectedSuKien.getLienKetNhanVat().get(nv), nv, relative1.getScene()));
        }
        relative1.getChildren().add(new Text("\nTriều đại liên quan: "));
        if (selectedSuKien.getLienKetTrieuDai().keySet().size() == 0) {
            relative1.getChildren().add(new Text("Không rõ"));
        }
        for (String td : selectedSuKien.getLienKetTrieuDai().keySet()) {
            Hyperlink link = new Hyperlink(td);
            link.setOnAction(e -> handleLabel("Triều Đại", selectedSuKien.getLienKetTrieuDai().get(td), td, relative1.getScene()));
            relative1.getChildren().addAll(link, new Text("\n"));
        }
    }

    public static void handleRenderDiTich(DuLieuLichSu tenDiTich, TextFlow relative, ScrollPane imageContainer) {
        if (tenDiTich instanceof DiTichLichSu) {
            DiTichLichSu selectedDiTich = (DiTichLichSu) tenDiTich;
            relative.getChildren().add(new Text(Utils.textDiTich(selectedDiTich)));
        } else if (tenDiTich instanceof DiTichVN) {
            DiTichVN selectedDiTich = (DiTichVN) tenDiTich;
            StringBuilder builder = new StringBuilder();
            LinkedHashMap<String, String> tt = selectedDiTich.getThongTin();
            for (String key : tt.keySet()) {
                builder.append(key).append(": ").append(tt.get(key)).append("\n\n");
            }
            String info = builder.toString();
            relative.getChildren().add(new Text("Tên di tích: " + selectedDiTich.getTen() + "\n\n" +
                    "Loại di tích: " + selectedDiTich.getLoaiDiTich() + "\n\n" +
                    "Địa điểm : " + selectedDiTich.getDiaDiem() + "\n\n" +
                    info));


            // Create a new ImageView object for each image and add it to the VBox
            String link = selectedDiTich.getImg();
            ArrayList<String> linkAnh = new ArrayList<>();
            linkAnh.add(link);
            Platform.runLater(() -> loadImage(imageContainer, linkAnh));
        }
    }

    public static void handleRenderLeHoi(DuLieuLichSu tenLeHoi, TextFlow relative, ScrollPane imageContainer) {
        LeHoi selectedLeHoi = (LeHoi) tenLeHoi;
        relative.getChildren().add(new Text(Utils.textLeHoi(selectedLeHoi)));
        relative.getChildren().add(new Text("\nLễ hội liên quan: "));
        for (String sk : selectedLeHoi.getLienKetLeHoi().keySet()) {
            Hyperlink link = new Hyperlink(sk);
            // Add the Text and Hyperlink nodes to the TextFlow node
            relative.getChildren().addAll(link, new Text("\n"));
            link.setOnAction(e -> handleLabel("Lễ Hội", selectedLeHoi.getLienKetLeHoi().get(sk), sk, relative.getScene()));
        }
        loadImage(imageContainer, selectedLeHoi.getLinkAnh());
    }

    public static void handleRenderNhanVat(DuLieuLichSu tenNhanVat, TextFlow relative) {
        NhanVat selectedNhanVat = (NhanVat) tenNhanVat;
        if (selectedNhanVat instanceof NhanVatLichSu) {
            HashMap<String, String> thongTin = ((NhanVatLichSu) selectedNhanVat).getThongTin();
            HashMap<String, String> thongTinCoBan = ((NhanVatLichSu) selectedNhanVat).getThongTinCoBan();
            StringBuilder t = new StringBuilder();
            if (thongTin != null) {
                for (Map.Entry<String, String> entry : thongTin.entrySet()) {
                    t.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n\n");
                }
            }
            if (thongTinCoBan != null) {
                for (Map.Entry<String, String> entry : thongTinCoBan.entrySet()) {
                    t.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n\n");
                }
            }
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n\n" +
                    "Miêu tả: " + ((NhanVatLichSu) selectedNhanVat).getMieuTa() + "\n\n" +
                    t));
            relative.getChildren().add(new Text("Sự kiện liên quan: "));
            if (selectedNhanVat.getLienKetSuKien().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetSuKien().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text("\n"));
                link.setOnAction(e -> handleLabel("Sự Kiện", selectedNhanVat.getLienKetSuKien().get(sk), sk, relative.getScene()));

            }
            relative.getChildren().add(new Text("\n"));
            relative.getChildren().add(new Text("Triều đại liên quan: "));
            if (selectedNhanVat.getLienKetTrieuDai().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetTrieuDai().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text("\n"));
                link.setOnAction(e -> handleLabel("Triều Đại", selectedNhanVat.getLienKetTrieuDai().get(sk), sk, relative.getScene()));

            }
            relative.getChildren().add(new Text("\n"));
        } else if (selectedNhanVat instanceof NhanVatVanSu) {
            HashMap<String, String> thongTin = ((NhanVatVanSu) selectedNhanVat).getAtt();
            StringBuilder t = new StringBuilder();
            if (thongTin != null) {
                for (Map.Entry<String, String> entry : thongTin.entrySet()) {
                    t.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n\n");
                }
            }
            relative.getChildren().add(new Text("Tên: " + selectedNhanVat.getTen() + "\n\n" + t));
            relative.getChildren().add(new Text("Sự kiện liên quan: "));
            if (selectedNhanVat.getLienKetSuKien().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetSuKien().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text("\n"));
                link.setOnAction(e -> handleLabel("Sự Kiện", selectedNhanVat.getLienKetSuKien().get(sk), sk, relative.getScene()));

            }
            relative.getChildren().add(new Text("\n"));
            relative.getChildren().add(new Text("Triều đại liên quan: "));
            if (selectedNhanVat.getLienKetTrieuDai().keySet().size() == 0) {
                relative.getChildren().add(new Text("Không rõ"));
            }
            for (String sk : selectedNhanVat.getLienKetTrieuDai().keySet()) {
                Hyperlink link = new Hyperlink(sk);
                // Add the Text and Hyperlink nodes to the TextFlow node
                relative.getChildren().addAll(link, new Text("\n"));
                link.setOnAction(e -> handleLabel("Triều Đại", selectedNhanVat.getLienKetTrieuDai().get(sk), sk, relative.getScene()));
            }
            relative.getChildren().add(new Text("\n"));
        } else {
            relative.getChildren().add(new Text(Utils.textNhanVat(selectedNhanVat)));
        }
    }
}
