package CrawlData.CrawlTrieuDai;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class test {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Nguye\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\webDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-extensions");
        options.addArguments("--charset=UTF-8");
        WebDriver driver = new ChromeDriver(options);
        String url = "https://shorturl.at/jxDT7";
        driver.get(url);
        List<WebElement> tables = driver.findElements(By.cssSelector("table.wikitable.sortable"));
        for(WebElement table: tables) {
            WebElement clickBtn = driver.findElement(By.className("headerSort"));
            clickBtn.click();
        }
        Duration duration =Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
        for(WebElement t: tables) {
            System.out.println(t.getAttribute("innerHTML"));
        }

//        for (WebElement rowElement : rowElements)
//        {
//            List<WebElement> cellElements = rowElement.findElements(By.tagName("td"));
//            if (cellElements.size() == 0){
//                continue;
//            }
//            String ten = cellElements.get(0).getText();
//            String namSinhnamMat = cellElements.get(1).getText();
//            String danToc = cellElements.get(2).getText();
//            String queQuan = cellElements.get(3).getText();
//            String namPhong = cellElements.get(4).getText();
//            String tieuSu = cellElements.get(5).getText();
//            anhHungVuTrang anhhung = new anhHungVuTrang(ten, namSinhnamMat, danToc, queQuan, namPhong, tieuSu);
//            dsAnhHung.add(anhhung);
//        }
//
//        // Print the result:
//        for (anhHungVuTrang anhhung : dsAnhHung)
//        {
//            System.out.println(anhhung.getTen());
//            System.out.println(anhhung.getNamSinhNamMat());
//            System.out.println(anhhung.getDanToc());
//            System.out.println(anhhung.getQueQuan());
//            System.out.println(anhhung.getNamPhong());
//            System.out.println(anhhung.getTieuSu());
//
//        }

        driver.quit();

    }
}
