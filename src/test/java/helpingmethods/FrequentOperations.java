package helpingmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

/**
 * Lecture 4
 */
public class FrequentOperations {

    public static WebDriver driver = getConfiguredDriver();//
    public WebDriverWait wait = new WebDriverWait(driver,9);

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources//chromedriver.exe");
       return new ChromeDriver();
}


   public void waitUntilElementAppearsByID(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public void waitUntilElementAppearsByClassNAme(String className) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
    }

    public void waitUntilElementAppearsByTagNAme(String tagName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(tagName)));
    }

    public static EventFiringWebDriver getConfiguredDriver() {
        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);

        return driver;
    }
}
