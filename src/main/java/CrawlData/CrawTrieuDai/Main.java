package CrawlData.CrawTrieuDai;

import CrawlData.CrawTrieuDai.King;
import CrawlData.CrawTrieuDai.KinhDo;
import CrawlData.CrawTrieuDai.trieuDai;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ArrayList<trieuDai> listTrieuDai = new ArrayList<>();
    static ArrayList<KinhDo> listKinhDo = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException {
        crawlTrieuDai();
        addKinhDo();
        addKinhDoToTrieuDai();
        writeToJSON();
    }
    static void crawlTrieuDai() throws UnsupportedEncodingException {
        WebElement table = crawlHTML("http://vietycotruyen.com.vn/cac-trieu-dai-viet-nam-qua-tung-thoi-ky-lich-su", 5, "tbody");;
        String tableHtml = table.getAttribute("innerHTML");
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows.remove(0);
        int index = 0;
        for (int j=0; j<rows.size(); j++) {
            List<WebElement> ps = rows.get(j).findElements(By.tagName("p"));
            if(ps.size() > 5 || j == 72) {
                for(int i= ps.size()-1; i >= 0; i--) {
                    if(ps.get(i).getAttribute("innerHTML").equals("&nbsp;")) {
                        ps.remove(i);
                    }
                }
            }
            ArrayList<String> cells = new ArrayList<>();
            for(WebElement p: ps) {
                String text = p.getAttribute("innerHTML");
                cells.add(text);
            }
            if(cells.size() == 5) {
                trieuDai trieudai = new trieuDai(cells.get(0),cells.get(1));
                King newKing = new King(cells.get(2), cells.get(3));
                trieudai.addKing(newKing);
                listTrieuDai.add(trieudai);
                index++;
            }
            if(cells.size() == 4) {
                trieuDai trieudai = new trieuDai(cells.get(0));
                trieudai.setThoiKy(listTrieuDai.get(index-1).getThoiKy());
                King newKing = new King(cells.get(1), cells.get(2));
                trieudai.addKing(newKing);
                listTrieuDai.add(trieudai);
                index++;
            }
            if(cells.size() == 3) {
                King newKing = new King(cells.get(0), cells.get(1));
                listTrieuDai.get(index - 1).addKing(newKing);
            }
        }
        for(trieuDai a: listTrieuDai) {
            if(a.getTen().contains("Triều Hậu Lê")) {
                a.cleanTen(a.getTen(), true);
            } else {
                a.cleanTen(a.getTen(), false);
            }
            a.cleanThoiKy(a.getThoiKy());
            a.cleanKing(a.getKings());
        }
    }
    static void addKinhDo() throws UnsupportedEncodingException {
        WebElement div = crawlHTML("https://quynhluu2.edu.vn/Giao-vien/DANH-SACH-CAC-KINH-DO-THU-DO-CUA-VIET-NAM-765.html", 5, "tbody");
        List<WebElement> rows = div.findElements(By.tagName("tr"));
        rows.remove(0);
        for(int i=0; i<rows.size(); i++) {
            List<WebElement> fonts = rows.get(i).findElements(By.cssSelector("font"));
            String[] trieuDais = fonts.get(1).getAttribute("innerHTML").split("<br>");
            String[] time = fonts.get(2).getAttribute("innerHTML").split("<br>");
            if(trieuDais.length >= 2 && time.length >=2 ) {
                for(int j=0; j<trieuDais.length; j++) {
                    KinhDo newKinhDo = new KinhDo(fonts.get(0).getText(), time[j],trieuDais[j]);
                    listKinhDo.add(newKinhDo);
                }
            }
            else {
                KinhDo newKinhDo = new KinhDo(fonts.get(0).getText(),fonts.get(2).getText().replaceAll("<br>", ""), fonts.get(1).getText());
                listKinhDo.add(newKinhDo);
            }
        }
    }
    static void writeToJSON() {
        JSONArray jsonArray = new JSONArray();
        for (trieuDai t : listTrieuDai) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ten", t.getTen());
            if(t.getKinhDo() == null) {
                jsonObject.put("kinh_do", "Không rõ");

            }else {
                jsonObject.put("kinh_do", t.getKinhDo());
            }
            jsonObject.put("nam_bat_dau", t.getNamBatDau());
            jsonObject.put("nam_ket_thuc", t.getNamKetThuc());

            jsonObject.put("thoi_ky", t.getThoiKy());
            jsonObject.put("vua", t.getKings().toString());
            jsonArray.put(jsonObject);
        }

        try {
            // Write JSONArray to JSON file
            FileWriter file = new FileWriter("trieuDai.json");
            file.write(jsonArray.toString(2));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void addKinhDoToTrieuDai() {
        System.out.println("Add");
        for(trieuDai t: listTrieuDai) {
            if(t.getNamBatDau().equals("Không rõ")) {
                t.setKinhDo("Không rõ");
                continue;
            }
            if(t.getNamBatDau().contains("TCN") || t.getNamKetThuc().contains("TCN") ) {
                for(KinhDo k: listKinhDo) {
                    if(!k.getNamBatDau().contains("TCN")) {
                        break;
                    }
                    int ts = Integer.parseInt(t.getNamBatDau().replaceAll("TCN", ""));
                    int ks = Integer.parseInt(k.getNamKetThuc().replaceAll("TCN", ""));
                    int te = Integer.parseInt(t.getNamKetThuc().replaceAll("TCN", ""));
                    int ke = Integer.parseInt(k.getNamBatDau().replaceAll("TCN", ""));
                    boolean correct = !(ts < ke || te > ks);
                    if(correct) {
                        t.setKinhDo(k.getTen());
                        break;
                    }
                    if(k.getNamKetThuc().contains("TCN") && !t.getNamKetThuc().contains("TCN")) {
                        t.setKinhDo(k.getTen());
                        break;
                    }
                }
            } else {
                for(KinhDo k: listKinhDo) {
                    if(k.getNamBatDau().contains("TCN")) continue;
                    if(!(Integer.parseInt(k.getNamBatDau()) > Integer.parseInt(t.getNamKetThuc()))
                            && !(Integer.parseInt(k.getNamKetThuc()) < Integer.parseInt(t.getNamBatDau()))) {
                        t.setKinhDo(k.getTen());
                        break;
                    }
                    else if(Integer.parseInt(k.getNamBatDau()) > Integer.parseInt(t.getNamKetThuc())) {
                        break;
                    }
                }
            }
        }
    }
    static WebElement crawlHTML(String url, Integer waitSecond, String waitElement) {
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\webDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-extensions");
        options.addArguments("--charset=UTF-8");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        Duration duration = Duration.ofSeconds(waitSecond);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(waitElement)));
        WebElement div = driver.findElement(By.cssSelector(waitElement));
        return div;
    }
}