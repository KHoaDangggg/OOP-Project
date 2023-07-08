package crawlData.crawltrieudai;

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
import java.util.Iterator;
import java.util.List;

public class crawl {
    static ArrayList<trieuDai> listTrieuDai = new ArrayList<>();
    static ArrayList<kinhDo> listKinhDo = new ArrayList<>();
    static ArrayList<quocHieu> listQuocHieu = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException {
        crawlTrieuDai();
        crawlKinhDo();
        crawlQuocHieu();
        addPropertiesToTrieuDai();
        writeToJSON();
    }
    static void crawlTrieuDai() {
        WebElement div = crawl.crawlHTML("https://baoduongmaynenkhi.vn/tom-tat-lich-su-viet-nam/", 1, "section.post_content.clearfix");
        List<WebElement> titles = div.findElements(By.cssSelector("p, h3"));
        Iterator<WebElement> iterator1 = titles.iterator();
        while (iterator1.hasNext()) {
            WebElement element = iterator1.next();
            boolean btag = element.getAttribute("innerHTML").contains("<b>");
            if(!btag) {
                iterator1.remove();
                continue;
            }
            if (!element.getText().contains("(") && !element.getText().contains("từ")) {
                iterator1.remove();
            }
        }
        Iterator<WebElement> iterator = titles.iterator();
        while (iterator.hasNext()) {
            WebElement element = iterator.next();
            WebElement nextSibling = element.findElement(By.xpath("following-sibling::*[1]"));
            try {
                WebElement bTag = nextSibling.findElement(By.tagName("b"));
                if (bTag.getText().contains("(")) {
                    iterator.remove();
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
        titles.remove(0);
        int count = 2;
        for(int i=0; i<titles.size(); i++) {
            //Set ten,nam
            WebElement title = titles.get(i);
            String text = title.getText()
                    .replaceAll("đến | – |\\u2013", "-")
                    .replaceAll("khoảng", "")
                    .replaceAll("từ", "(")
                    .replaceAll("nay", "2023");
            int start = text.lastIndexOf('(');
            int mid = text.lastIndexOf("-");
            int end = text.indexOf(")") ;
            if(!text.contains(")")) {
                end = text.length();
            }
            String namBatDau = text.substring(start+1, mid).replaceAll(" ","");
            String namKetThuc = text.substring(mid+1, end).replaceAll(" ","");
            if(text.substring(0, start).contains("(")) {
                start--;
            }
            String ten = text.substring(0, start).trim();
            //Set mota
            WebElement nextElement = title.findElement(By.xpath("following-sibling::p[1]"));
            StringBuilder result = new StringBuilder();
            ArrayList<String> kings = new ArrayList<>();
            while(true) {
                if(titles.contains(nextElement) || nextElement == null) {
                    break;
                }
                // Set king
                if(nextElement.getTagName().equals("ul")) {
                    List<WebElement> lis = nextElement.findElements(By.cssSelector("li"));
                    for(WebElement li: lis) {
                        kings.add(li.getText());
                    }
                } else {
                    // Set mota
                    List<WebElement> boldElements = nextElement.findElements(By.cssSelector("b"));
                    if (boldElements.size() > 0 ) {
                        if(count == 0) {
                            break;
                        } else {
                            count--;
                        }
                    }
                    result.append(nextElement.getText()).append('\n');
                }
                try {
                    nextElement = nextElement.findElement(By.xpath("following-sibling::p[1] | following-sibling::h3[1] | following-sibling::ul[1]"));
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            if(result.charAt(result.length()-1) == ':') {
                result.setCharAt(result.length()-1, '.');
            }
            String mota = result.toString().replaceAll("bao gồm:| gồm:| là:", ".").replaceAll("\\u2013| \\u201c| \\u201d", " ").trim();
            trieuDai newTrieuDai = new trieuDai(ten, namBatDau, namKetThuc, mota, kings);
            if(i == 0) {
                newTrieuDai = new trieuDai(ten, namBatDau, namKetThuc, "Gồm hai nhà nước nhỏ bao gồm: \n" + mota, kings);
            }
            listTrieuDai.add(newTrieuDai);
        }
        writeToJSON();
    }

    static void crawlKinhDo() {
        WebElement div = crawlHTML("https://quynhluu2.edu.vn/Giao-vien/DANH-SACH-CAC-KINH-DO-THU-DO-CUA-VIET-NAM-765.html", 5, "tbody");
        List<WebElement> rows = div.findElements(By.tagName("tr"));
        rows.remove(0);
        for (WebElement row : rows) {
            List<WebElement> fonts = row.findElements(By.cssSelector("font"));
            String[] trieuDais = fonts.get(1).getAttribute("innerHTML").split("<br>");
            String[] time = fonts.get(2).getAttribute("innerHTML").split("<br>");
            if (trieuDais.length >= 2 && time.length >= 2) {
                for (int j = 0; j < trieuDais.length; j++) {
                    kinhDo newKinhDo = new kinhDo(fonts.get(0).getText(), time[j], trieuDais[j]);
                    listKinhDo.add(newKinhDo);
                }
            } else {
                kinhDo newKinhDo = new kinhDo(fonts.get(0).getText(), fonts.get(2).getText().replaceAll("<br>", ""), fonts.get(1).getText());
                listKinhDo.add(newKinhDo);
            }
        }
    }

    static void crawlQuocHieu() {
        WebElement div = crawlHTML("https://vi.wikipedia.org/wiki/T%C3%AAn_g%E1%BB%8Di_Vi%E1%BB%87t_Nam", 1,
                "table.collapsible.autocollapse.mw-collapsible.mw-made-collapsible tbody");
        List<WebElement> rows = div.findElements(By.tagName("tr"));
        rows.remove(0);
        quocHieu newQuocHieu;
        for(int i=0; i<23; i++) {
            WebElement row = rows.get(i);
            List<WebElement> tds =row.findElements(By.cssSelector("td"));
            String ten = tds.get(1).getText().replaceAll(" ", "");
            String cleanTen = null;
            for (int j = 0; j < ten.length(); j++) {
                if (Character.isUpperCase(ten.charAt(j)) && j>0) {
                    cleanTen = ten.substring(0,j) + " " + ten.substring(j);
                }
            }
            if(tds.get(0).getText().contains("từ")) {
                newQuocHieu = new quocHieu(cleanTen.replaceAll("\n",""), tds.get(0).getText().replaceAll("từ", ""), "2023");
            }
            else {
                String[] nam = tds.get(0).getText().replaceAll(" ", "").split("\u2013");
                newQuocHieu = new quocHieu(cleanTen.replaceAll("\n",""), nam[0], nam[1]);
            }
            if(newQuocHieu.getNamKetThuc().contains("CN") && !newQuocHieu.getNamKetThuc().contains("TCN")) {
                newQuocHieu.setNamKetThuc(newQuocHieu.getNamKetThuc().replaceAll("CN",""));
            }
            listQuocHieu.add(newQuocHieu);
        }

    }

    static void addPropertiesToTrieuDai(){
        for(trieuDai t: listTrieuDai) {
            if(t.getNamBatDau().contains("?")) {
                t.setKinhDo("Không rõ");
                t.setQuocHieu("Không rõ");
                continue;
            }
            for(kinhDo k: listKinhDo) {
                if(isMatch(t.getNamBatDau(), t.getNamKetThuc(), k.getNamBatDau(), k.getNamKetThuc())) {
                    t.setKinhDo(k.getTen());
                    break;
                }
            }
            for(quocHieu q: listQuocHieu) {
                if(isMatch(t.getNamBatDau(), t.getNamKetThuc(), q.getNamBatDau(), q.getNamKetThuc())) {
                    t.setQuocHieu(q.getTen());
                }
            }
        }
    }

    static  boolean isMatch(String ...years) {
        int[] nam =  new int[4];
        for(int i=0; i<4; i++) {
            String year = years[i];
            if(year.contains("TCN")) {
                nam[i] = - Integer.parseInt(year.replaceAll("TCN","").trim());
            } else {
                nam[i] = Integer.parseInt(year.trim());
            }
        }
        if(nam[1] < 0 && nam[0] > 0) {
            nam[0] = -nam[0];
        }
        if(nam[3] < 0 && nam[2] > 0) {
            nam[2] = -nam[2];
        }
        return nam[0] <= nam[3] && nam[1] >= nam[2];
    }

    static void writeToJSON() {
        JSONArray jsonArray = new JSONArray();
        for (trieuDai t : listTrieuDai) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ten", t.getTen().replaceAll("[\t\n]", ""));
            if(t.getKinhDo() == null) {
                jsonObject.put("kinhDo", "Không rõ");

            }else {
                jsonObject.put("kinhDo", t.getKinhDo());
            }
            jsonObject.put("namBatDau", t.getNamBatDau());
            jsonObject.put("namKetThuc", t.getNamKetThuc());
            jsonObject.put("moTa", t.getMoTa());
            jsonObject.put("kings", t.getKings());
            jsonObject.put("quocHieu", t.getQuocHieu());
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("src/JSON_Data/trieuDai.json");
            file.write(jsonArray.toString(1));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
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