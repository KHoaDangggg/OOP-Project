package controllers;

import model.di_tich.DiTichLichSu;
import model.le_hoi.LeHoi;
import model.nhan_vat.DanhNhan;
import model.nhan_vat.AnhHungVuTrang;
import model.nhan_vat.NhanVat;
import model.nhan_vat.DanhHieu;
import model.nhan_vat.Vua;
import model.su_kien.SuKienChienTranh;
import model.su_kien.SuKienLichSu;
import model.trieu_dai.TrieuDai;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Utils {
    public static String textTrieuDai(TrieuDai selectedTrieuDai) {
        StringBuilder text = new StringBuilder();
        StringBuilder vua = new StringBuilder();

        ArrayList<String> vuas = selectedTrieuDai.getKings();
        for (String s : vuas) {
            vua.append(s).append("\n");
        }
        text.append("Tên: ")
                .append(selectedTrieuDai.getTen()).append("\n")
                .append("Năm bắt đầu - kết thúc: ").append(selectedTrieuDai.getNamBatDau()).append(" - ").append(selectedTrieuDai.getNamKetThuc()).append("\n")
                .append("Quốc hiệu: : ").append(selectedTrieuDai.getKinhDo()).append("\n")
                .append("Kinh đô: ").append(selectedTrieuDai.getKinhDo()).append("\n")
                .append("Vua: ").append(vua).append("\n")
                .append("Mô tả: : ").append(selectedTrieuDai.getMoTa()).append("\n");
        return text.toString();
    }
    public static String textSuKien(SuKienLichSu selectedSuKien) {
        if(selectedSuKien instanceof SuKienChienTranh)
         return "Tên sự kiện: " + selectedSuKien.getTen() + "\n" +
                "Thời gian: " + selectedSuKien.getThoiGian() + "\n" +
                "Địa điểm: " + selectedSuKien.getDiaDiem() + "\n" +
                "Nguyên nhân: " + ((SuKienChienTranh) selectedSuKien).getNguyenNhan() + "\n" +
                "Chỉ huy phe địch: " + ((SuKienChienTranh) selectedSuKien).getChiHuyPheDich() + "\n" +
                "Lực lượng phe địch: " + ((SuKienChienTranh) selectedSuKien).getLucLuongPheDich() + "\n" +
                "Phe địch: " + ((SuKienChienTranh) selectedSuKien).getPheDich() + "\n" +
                "Chỉ huy phe ta: " + ((SuKienChienTranh) selectedSuKien).getChiHuyPheTa() + "\n" +
                "Lực lượng phe ta: " + ((SuKienChienTranh) selectedSuKien).getLucLuongPheTa() + "\n" +
                "Phe ta: " + ((SuKienChienTranh) selectedSuKien).getPheTa() + "\n" +
                "Kết quả: " + selectedSuKien.getKetQua() + "\n" +
                "Tổn thất địch: " + ((SuKienChienTranh) selectedSuKien).getTonThatDich() + "\n" +
                "Tổn thất ta: " + ((SuKienChienTranh) selectedSuKien).getTonThatTa();
        else return "Tên sự kiện: " + selectedSuKien.getTen() + "\n" +
                "Thời gian: " + selectedSuKien.getThoiGian() + "\n" +
                "Địa điểm: " + selectedSuKien.getDiaDiem() + "\n" +
                "Kết quả: " + selectedSuKien.getKetQua() + "\n";
    }
    public static String textDiTich(DiTichLichSu selectedDiTich) {
        return "Tên di tích: " + selectedDiTich.getTen() + "\n" +
                "Loại di tích: " + selectedDiTich.getLoaiDiTich() + "\n" +
                "Năm công nhận: " + selectedDiTich.getNamCongNhan() + "\n" +
                "Địa điểm : " + selectedDiTich.getDiaDiem() + "\n" +
                "Mô tả di tích: " + selectedDiTich.getMoTa() + "\n";
    }

    public static String textLeHoi(LeHoi selectedLeHoi) {
        return "Tên lễ hội: " + selectedLeHoi.getTen() + "\n" +
                "Địa điểm: " + selectedLeHoi.getDiaDiem() + "\n" +
                "Thời gian: " + selectedLeHoi.getThoiGian() + "\n" +
                "Thông tin: " + selectedLeHoi.getThongTinLeHoi() + "\n";
    }

    public static String textNhanVat(NhanVat selectedNhanVat) {
        if (selectedNhanVat instanceof Vua) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Tên húy: " + ((Vua) selectedNhanVat).getTen_huy() + "\n" +
                    "Miếu hiệu: " + ((Vua) selectedNhanVat).getMieu_hieu() + "\n" +
                    "Niên hiệu: " + ((Vua) selectedNhanVat).getNien_hieu() + "\n" +
                    "Thúy hiệu: " + ((Vua) selectedNhanVat).getThuy_hieu() + "\n" +
                    "Thế thứ: " + ((Vua) selectedNhanVat).getThe_thu() + "\n" +
                    "Năm trị vì: " + ((Vua) selectedNhanVat).getNam_bat_dau_tri_vi() + " - " + ((Vua) selectedNhanVat).getNam_ket_thuc_tri_vi() + "\n";
        }
        if (selectedNhanVat instanceof AnhHungVuTrang) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((AnhHungVuTrang) selectedNhanVat).getQueQuan() + "\n" +
                    "Dân tộc: " + ((AnhHungVuTrang) selectedNhanVat).getDanToc() + "\n" +
                    "Năm sinh - năm mất: " + ((AnhHungVuTrang) selectedNhanVat).getNamSinhNamMat() + "\n" +
                    "Năm phong: " + ((AnhHungVuTrang) selectedNhanVat).getNamPhong() + "\n" +
                    "Tiểu sử: " + ((AnhHungVuTrang) selectedNhanVat).getTieuSu() + "\n";
        }
        if (selectedNhanVat instanceof DanhNhan) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((DanhNhan) selectedNhanVat).getQueQuan() + "\n" +
                    "Tư liệu: " + ((DanhNhan) selectedNhanVat).gettuLieu() + "\n" +
                    "Tóm tắt: " + ((DanhNhan) selectedNhanVat).gettomTat() + "\n";
        }
        if (selectedNhanVat instanceof DanhHieu) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Năm sinh - năm mất: " + ((DanhHieu) selectedNhanVat).getNamSinh() + " - " + ((DanhHieu) selectedNhanVat).getNamMat() + "\n" +
                    "Quê quán: " + ((DanhHieu) selectedNhanVat).getQueQuan() + "\n" +
                    "Danh hiệu: " + ((DanhHieu) selectedNhanVat).getDanhHieu() + "\n" +
                    "Năm đỗ: " + ((DanhHieu) selectedNhanVat).getNamDo() + "\n" +
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
}
