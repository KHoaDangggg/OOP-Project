package controllers;

import crawlData.CrawlDiTich.DiTichLichSu;
import crawlData.CrawlLeHoi.LeHoi_Nguon_05.LeHoi;
import crawlData.CrawlNhanVat.CrawlAnhHung.DanhNhan;
import crawlData.CrawlNhanVat.CrawlAnhHung.anhHungVuTrang;
import crawlData.CrawlNhanVat.NhanVat;
import crawlData.CrawlNhanVat.trangnguyenbangnhan.src.DanhHieu;
import crawlData.CrawlNhanVat.vua.src.Vua;
import crawlData.CrawlSuKien.SuKienChienTranh;
import crawlData.crawltrieudai.trieuDai;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class utils {
    public static String textTrieuDai(trieuDai selectedTrieuDai) {
        StringBuilder text = new StringBuilder();
        StringBuilder vua = new StringBuilder();

        ArrayList<String> vuas = selectedTrieuDai.getKings();
        for (String s : vuas) {
            vua.append(s).append("\n");
        }
        text.append("Tên: " + selectedTrieuDai.getTen() + "\n" +
                "Năm bắt đầu - kết thúc: " + selectedTrieuDai.getNamBatDau() + " - " + selectedTrieuDai.getNamKetThuc() + "\n" +
                "Quốc hiệu: : " + selectedTrieuDai.getKinhDo() + "\n" +
                "Kinh đô: " + selectedTrieuDai.getKinhDo() + "\n" +
                "Vua: " + vua + "\n" +
                "Mô tả: : " + selectedTrieuDai.getMoTa() + "\n"
                 );
        return text.toString();
    }
    public static String textSuKien(SuKienChienTranh selectedSuKien) {
        return "Tên sự kiện: " + selectedSuKien.getTen() + "\n" +
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
                "Tổn thất ta: " + selectedSuKien.getTonThatTa();
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
        if (selectedNhanVat instanceof anhHungVuTrang) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((anhHungVuTrang) selectedNhanVat).getQueQuan() + "\n" +
                    "Dân tộc: " + ((anhHungVuTrang) selectedNhanVat).getDanToc() + "\n" +
                    "Năm sinh - năm mất: " + ((anhHungVuTrang) selectedNhanVat).getNamSinhNamMat() + "\n" +
                    "Năm phong: " + ((anhHungVuTrang) selectedNhanVat).getNamPhong() + "\n" +
                    "Tiểu sử: " + ((anhHungVuTrang) selectedNhanVat).getTieuSu() + "\n";
        }
        if (selectedNhanVat instanceof DanhNhan) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Quê quán: " + ((DanhNhan) selectedNhanVat).getQueQuan() + "\n" +
                    "Tư liệu: " + ((DanhNhan) selectedNhanVat).gettuLieu() + "\n" +
                    "Tóm tắt: " + ((DanhNhan) selectedNhanVat).gettomTat() + "\n";
        }
        if (selectedNhanVat instanceof DanhHieu) {
            return "Tên: " + selectedNhanVat.getTen() + "\n" +
                    "Năm sinh - năm mất: " + ((DanhHieu) selectedNhanVat).getNam_sinh() + " - " + ((DanhHieu) selectedNhanVat).getNam_mat() + "\n" +
                    "Quê quán: " + ((DanhHieu) selectedNhanVat).getQue_quan() + "\n" +
                    "Danh hiệu: " + ((DanhHieu) selectedNhanVat).getDanh_hieu() + "\n" +
                    "Năm đỗ: " + ((DanhHieu) selectedNhanVat).getNam_do() + "\n" +
                    "Đời vua:" + ((DanhHieu) selectedNhanVat).getDoi_vua() + "\n";
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
