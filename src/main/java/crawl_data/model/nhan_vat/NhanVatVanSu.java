package crawl_data.model.nhan_vat;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class NhanVatVanSu extends NhanVat {
    private final LinkedHashMap<String, String> att;
    public NhanVatVanSu(String ten, LinkedHashMap<String, String> att) {
        super(ten);
        this.att = att;
    }

    public HashMap<String, String> getAtt() {
        return att;
    }
}
