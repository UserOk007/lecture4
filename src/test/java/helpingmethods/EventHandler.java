package helpingmethods;

import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;


import static helpingmethods.FrequentOperations.driver;

/**
 * Lecture 4
 */
public class EventHandler implements WebDriverEventListener{

    @Override

    public void afterFindBy(By by, WebElement element, WebDriver selenium) {
        if (element != null) {
            System.out.println("Found element: " + element.getTagName());
        }
    }

    public void afterClickOn(WebElement element, WebDriver selenium) {
        if (element != null) {
            System.out.println("Cli" +
                    "cked on element: " + element.getClass());
        }
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }

    public void afterNavigateBack(WebDriver selenium) {
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
    public void afterNavigateForward(WebDriver selenium) {
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
        System.out.println("Refrage page");

    }

    public void afterNavigateRefresh(WebDriver webDriver) {
        System.out.println("Navigate forvard");
    }

    public void afterNavigateTo(String url, WebDriver selenium) {
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
    public void afterScript(String script, WebDriver selenium) {}

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void beforeClickOn(WebElement element, WebDriver selenium) {
        if (element != null) {
            System.out.println("Click on element: " + element.getTagName());
        }
    }

    public void beforeFindBy(By by, WebElement element, WebDriver selenium) {

        if (element != null) {
            System.out.println("Searching element: " + by.toString());//element.getTagName());
        }
    }

    public void beforeNavigateBack(WebDriver selenium) {}
    public void beforeNavigateForward(WebDriver selenium) {}

    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String url, WebDriver selenium) {}
    public void beforeScript(String script, WebDriver selenium) {}
    public void onException(Throwable error, WebDriver selenium) {}

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

        if (webElement != null) {
            System.out.println("Got text: " + webElement.getText());
        }

    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }
}

