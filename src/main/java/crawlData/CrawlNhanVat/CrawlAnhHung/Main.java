package crawlData.CrawlNhanVat.CrawlAnhHung;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main {
    static List<anhHungVuTrang> dsAnhHung = new ArrayList<>();
    public static void main(String[] args) {

        crawlNhanVat();
        print();
        savetoJson();
    }
    static void print(){
        for (anhHungVuTrang anhHung : dsAnhHung)
        {
            System.out.println(anhHung.getTen());
            System.out.println(anhHung.getNamSinhNamMat());
            System.out.println(anhHung.getDanToc());
            System.out.println(anhHung.getQueQuan());
            System.out.println(anhHung.getNamPhong());
            System.out.println(anhHung.getTieuSu());

        }
        System.out.println(dsAnhHung.size());


    }

    static void crawlNhanVat(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Nguye\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("file.encoding", "UTF-8");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-extensions");
        options.addArguments("--charset=UTF-8");
        WebDriver driver = new ChromeDriver(options);
        String url = "https://shorturl.at/jxDT7";
        driver.get(url);
        List<WebElement> tables = driver.findElements(By.cssSelector("table.wikitable.sortable"));
        for(WebElement table: tables) {
            WebElement clickBtn = table.findElement(By.className("headerSort"));
            clickBtn.click();
        }
        Duration duration =Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
        for(WebElement t: tables) {
            List<WebElement> trs = t.findElements(By.cssSelector("tr"));
            trs.remove(0);
            for(WebElement tr: trs) {
                List<WebElement> cells = tr.findElements(By.tagName("td"));
                String ten = cells.get(0).getText();
                String namSinhNamMat = cells.get(1).getText();
                String danToc = cells.get(2).getText();
                String queQuan = cells.get(3).getText();
                String namPhong =cells.get(4).getText();
                String tieuSu = cells.get(5).getText();
                anhHungVuTrang anhHung = new anhHungVuTrang(ten, namSinhNamMat, danToc, queQuan, namPhong, tieuSu);
                dsAnhHung.add(anhHung);
            }
        }

    }
    static void savetoJson(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("vuTrang.json"), dsAnhHung);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
