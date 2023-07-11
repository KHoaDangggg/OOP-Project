package controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.ditich.DiTichLichSu;
import model.lehoi.LeHoi;
import model.nhanvat.*;
import model.sukien.SuKienChienTranh;
import model.sukien.SuKienLichSu;
import model.trieudai.TrieuDai;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Utils {
    private static final ArrayList<Thread> threadManager = new ArrayList<>();

    public static String textTrieuDai(TrieuDai selectedTrieuDai) {
        StringBuilder text = new StringBuilder();
        StringBuilder vua = new StringBuilder();
        ArrayList<String> vuas = selectedTrieuDai.getKings();
        for (String s : vuas) {
            vua.append(s).append("\n");
        }
        text.append("Tên: ")
                .append(selectedTrieuDai.getTen()).append("\n\n")
                .append("Năm bắt đầu - kết thúc: ").append(selectedTrieuDai.getNamBatDau()).append(" - ").append(selectedTrieuDai.getNamKetThuc()).append("\n\n")
                .append("Quốc hiệu: : ").append(selectedTrieuDai.getKinhDo()).append("\n\n")
                .append("Kinh đô: ").append(selectedTrieuDai.getKinhDo()).append("\n\n")
                .append("Vua: ").append(vua).append("\n\n")
                .append("Mô tả: : ").append(selectedTrieuDai.getMoTa()).append("\n");
        return text.toString();
    }

    public static String textSuKien(SuKienLichSu selectedSuKien) {
        if (selectedSuKien instanceof SuKienChienTranh)
            return "Tên sự kiện: " + selectedSuKien.getTen() + "\n\n" +
                    "Thời gian: " + selectedSuKien.getThoiGian() + "\n\n" +
                    "Địa điểm: " + selectedSuKien.getDiaDiem() + "\n\n" +
                    "Nguyên nhân: " + ((SuKienChienTranh) selectedSuKien).getNguyenNhan() + "\n\n" +
                    "Chỉ huy phe địch: " + ((SuKienChienTranh) selectedSuKien).getChiHuyPheDich() + "\n\n" +
                    "Lực lượng phe địch: " + ((SuKienChienTranh) selectedSuKien).getLucLuongPheDich() + "\n\n" +
                    "Phe địch: " + ((SuKienChienTranh) selectedSuKien).getPheDich() + "\n\n" +
                    "Chỉ huy phe ta: " + ((SuKienChienTranh) selectedSuKien).getChiHuyPheTa() + "\n\n" +
                    "Lực lượng phe ta: " + ((SuKienChienTranh) selectedSuKien).getLucLuongPheTa() + "\n\n" +
                    "Phe ta: " + ((SuKienChienTranh) selectedSuKien).getPheTa() + "\n\n" +
                    "Kết quả: " + selectedSuKien.getKetQua() + "\n\n" +
                    "Tổn thất địch: " + ((SuKienChienTranh) selectedSuKien).getTonThatDich() + "\n" +
                    "Tổn thất ta: " + ((SuKienChienTranh) selectedSuKien).getTonThatTa();
        else return "Tên sự kiện: " + selectedSuKien.getTen() + "\n\n" +
                "Thời gian: " + selectedSuKien.getThoiGian() + "\n\n" +
                "Địa điểm: " + selectedSuKien.getDiaDiem() + "\n\n" +
                "Kết quả: " + selectedSuKien.getKetQua() + "\n";
    }

    public static String textDiTich(DiTichLichSu selectedDiTich) {
        return "Tên di tích: " + selectedDiTich.getTen() + "\n\n" +
                "Loại di tích: " + selectedDiTich.getLoaiDiTich() + "\n\n" +
                "Năm công nhận: " + selectedDiTich.getNamCongNhan() + "\n\n" +
                "Địa điểm : " + selectedDiTich.getDiaDiem() + "\n\n" +
                "Mô tả di tích: " + selectedDiTich.getMoTa() + "\n";
    }

    public static String textLeHoi(LeHoi selectedLeHoi) {
        if(selectedLeHoi.getThoiGian()==null) selectedLeHoi.setThoiGian("Không rõ");
        return "Tên lễ hội: " + selectedLeHoi.getTen() + "\n\n" +
                "Địa điểm: " + selectedLeHoi.getDiaDiem() + "\n\n" +
                "Thời gian: " + selectedLeHoi.getThoiGian() + "\n\n" +
                "Thông tin: " + selectedLeHoi.getThongTinLeHoi() + "\n";
    }

    public static String textNhanVat(NhanVat selectedNhanVat) {
        if (selectedNhanVat instanceof Vua) {
            return "Tên: " + selectedNhanVat.getTen() + "\n\n" +
                    "Tên húy: " + ((Vua) selectedNhanVat).getTenHuy() + "\n\n" +
                    "Miếu hiệu: " + ((Vua) selectedNhanVat).getMieuHieu() + "\n\n" +
                    "Niên hiệu: " + ((Vua) selectedNhanVat).getNienHieu() + "\n\n" +
                    "Thúy hiệu: " + ((Vua) selectedNhanVat).getThuyHieu() + "\n\n" +
                    "Thế thứ: " + ((Vua) selectedNhanVat).getTheThu() + "\n\n" +
                    "Năm trị vì: " + ((Vua) selectedNhanVat).getNamBatDauTriVi() + " - " + ((Vua) selectedNhanVat).getNamKetThucTriVi() + "\n";
        }
        if (selectedNhanVat instanceof AnhHungVuTrang) {
            return "Tên: " + selectedNhanVat.getTen() + "\n\n" +
                    "Quê quán: " + ((AnhHungVuTrang) selectedNhanVat).getQueQuan() + "\n\n" +
                    "Dân tộc: " + ((AnhHungVuTrang) selectedNhanVat).getDanToc() + "\n\n" +
                    "Năm sinh - năm mất: " + ((AnhHungVuTrang) selectedNhanVat).getNamSinhNamMat() + "\n\n" +
                    "Năm phong: " + ((AnhHungVuTrang) selectedNhanVat).getNamPhong() + "\n\n" +
                    "Tiểu sử: " + ((AnhHungVuTrang) selectedNhanVat).getTieuSu() + "\n";
        }
        if (selectedNhanVat instanceof DanhNhan) {
            return "Tên: " + selectedNhanVat.getTen() + "\n\n" +
                    "Quê quán: " + ((DanhNhan) selectedNhanVat).getQueQuan() + "\n\n" +
                    "Tư liệu: " + ((DanhNhan) selectedNhanVat).gettuLieu() + "\n\n" +
                    "Tóm tắt: " + ((DanhNhan) selectedNhanVat).gettomTat() + "\n";
        }
        if (selectedNhanVat instanceof DanhHieu) {
            return "Tên: " + selectedNhanVat.getTen() + "\n\n" +
                    "Năm sinh - năm mất: " + ((DanhHieu) selectedNhanVat).getNamSinh() + " - " + ((DanhHieu) selectedNhanVat).getNamMat() + "\n\n" +
                    "Quê quán: " + ((DanhHieu) selectedNhanVat).getQueQuan() + "\n\n" +
                    "Danh hiệu: " + ((DanhHieu) selectedNhanVat).getDanhHieu() + "\n\n" +
                    "Năm đỗ: " + ((DanhHieu) selectedNhanVat).getNamDo() + "\n\n" +
                    "Đời vua:" + ((DanhHieu) selectedNhanVat).getDoiVua() + "\n";
        }
        return "";
    }

    public static String removeVietnameseAccent(String str) {
        String nfc = Normalizer.normalize(str, Normalizer.Form.NFC);
        String regex = "[^\\p{L}0-9 ]";
        String noAccent = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS)
                .matcher(nfc)
                .replaceAll("");
        noAccent = noAccent.replaceAll("đ", "d")
                .replaceAll("[àáảãạâầấẩẫậăằắẳẵặ]", "a")
                .replaceAll("[èéẻẽẹêềếểễệ]", "e")
                .replaceAll("[ìíỉĩị]", "i")
                .replaceAll("[òóỏõọôồốổỗộơờớởỡợ]", "o")
                .replaceAll("[ùúủũụưừứửữự]", "u")
                .replaceAll("[ỳýỷỹỵ]", "y");
        return noAccent.toLowerCase().strip();
    }

    //Loading image in background
    public static void loadImage(ScrollPane imageContainer, ArrayList<String> url) {
        if (!threadManager.isEmpty()) {
            for (Thread thread : threadManager) thread.interrupt();
            threadManager.clear();
        }
        Task<ArrayList<Image>> imageLoadingTask = new Task<>() {
            @Override
            protected ArrayList<Image> call() {
                ArrayList<Image> images = new ArrayList<>();
                for (String u : url) {
                    images.add(new Image(u));
                }
                return images;
            }
        };

        // Set the image once it's loaded
        imageLoadingTask.setOnSucceeded(event -> {
            ArrayList<Image> loadedImage = imageLoadingTask.getValue();
            //System.out.println(loadedImage.getUrl());
            VBox imageBox = new VBox();
            imageBox.setSpacing(10);
            for (Image image : loadedImage) {
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageBox.getChildren().add(imageView);
            }
            // Set the content of the ScrollPane to the VBox
            Platform.runLater(() -> imageContainer.setContent(imageBox));
            System.out.println("Load image successfully!");
        });

        // Show an error message if the image loading fails
        imageLoadingTask.setOnFailed(event -> {
            Throwable exception = imageLoadingTask.getException();
            System.err.println("Failed to load image: " + exception.getMessage());
        });

        // Start the image loading task in a separate thread
        Thread thread = new Thread(imageLoadingTask);
        thread.start();
        threadManager.add(thread);
    }
}
