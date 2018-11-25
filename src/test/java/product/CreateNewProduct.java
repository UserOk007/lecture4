package product;


import helpingmethods.FrequentOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 Lecture 4 - Homework
 */
public class CreateNewProduct extends FrequentOperations{

    public static final String ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    public static final String MAIN_URL = "http://prestashop-automation.qatestlab.com.ua";
    public static final String PRODUCT_TAB = "//*[@id='subtab-AdminProducts']/a";
    public static final String CATALOG_SUBTAB = "subtab-AdminCatalog";
    public static final String QUANTITY_SUBTAB = "tab_step3";
    public static final String INPUT_PRODUCT_QUANTITY = "input[id='form_step3_qty_0']";
    public static final String PRICE_SUBTAB = "tab_step2";
    public static final String INPUT_PRODUCT_PRICE = "input[id='form_step2_price']";
    public static final String SWITCHER = "switch-input";
    public static final String SAVE_PRODUCT_BUTTON = "submit";
    public static final String NOTIFICATION_AREA = "growls";
    public static final String CROSS_SIGN_OF_NOTIFICATION = "growl-close";
    public static final String SHOWN_PRICE = "//div[@class='current-price']";
    public static final String SHOWN_QUANTITY = "//div[@class='product-quantities']//span";
    public static final String ALL_PRODUCTS_LINK = "all-product-link";
    public static final String PRODUCT_CREATION_PAGE = "page-header-desc-configuration-add";
    public static final String INPUT_PRODUCT_NAME = "input[id='form_step1_name_1']";
    public static final String QUANTITY_SUBTAB_TITLE = "//div[@id='quantities']//h2";
    public final String nameOfProduct = randomName();
    public final String quantityOfProduct = randomQuantity();
    public String priceOfProduct = randomPrice();



    @DataProvider (name = "Authentication")
    public Object[][] takeLogin(){
        return new String[][] {
                {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}
        };
    }

    @Test(dataProvider = "Authentication")
    public void userLogin(String email, String passwd){
        driver.manage().window().maximize();
        driver.get(ADMIN_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        waitUntilElementAppearsByID("email");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(passwd);
        driver.findElement(By.name("submitLogin")).click();
        String dashboardTitle = driver.getTitle();
        System.out.println(dashboardTitle);
        Assert.assertEquals(dashboardTitle, "prestashop-automation > Панель администратора (PrestaShop™)");
    }

    @Test (dependsOnMethods = "userLogin")
    public void openProductTab() {
        waitUntilElementAppearsByID(CATALOG_SUBTAB);
        WebElement catalogTab = driver.findElement(By.id(CATALOG_SUBTAB));
        WebElement productTab = driver.findElement(By.xpath(PRODUCT_TAB));

        Actions actions = new Actions(driver);
        actions.moveToElement(catalogTab).pause(500).moveToElement(productTab).click(productTab).build().perform();
    }


    @Test (dependsOnMethods = "openProductTab")
    public void createNewProduct() {
        waitUntilElementAppearsByID("page-header-desc-configuration-add");
        driver.findElement(By.id(PRODUCT_CREATION_PAGE)).click();
        waitUntilElementAppearsByID("form_step1_name");
        WebElement mainSettingsTab = driver.findElement(By.id("tab_step1"));
        String mainSettingsTextTab = mainSettingsTab.getText();
        Assert.assertEquals(mainSettingsTextTab, "Основные настройки");
    }

    @Test (dependsOnMethods = "createNewProduct")
    public void addProductNameAndQuantity() {
        driver.findElement(By.cssSelector(INPUT_PRODUCT_NAME)).sendKeys(nameOfProduct);
        driver.findElement(By.id(QUANTITY_SUBTAB)).click();

        WebElement inputProductQuantity = driver.findElement(By.cssSelector(INPUT_PRODUCT_QUANTITY));
        waitUntilElementAppearsByID("form_step3_qty_0");
        inputProductQuantity.clear();
        inputProductQuantity.sendKeys(quantityOfProduct);

        WebElement quntitySubTabTitle = driver.findElement(By.xpath(QUANTITY_SUBTAB_TITLE));
        String subtabTitle = quntitySubTabTitle.getText();
        Assert.assertEquals(subtabTitle, "Количества");
    }

    @Test (dependsOnMethods = "addProductNameAndQuantity")
    public void addProductPrice() {
        WebElement priceTab = driver.findElement(By.id(PRICE_SUBTAB));
        WebElement inputProductPrice = driver.findElement(By.cssSelector(INPUT_PRODUCT_PRICE));
        priceTab.click();
        waitUntilElementAppearsByID("form_step2_price");
        inputProductPrice.clear();
        inputProductPrice.sendKeys(priceOfProduct);
    }

    @Test (dependsOnMethods = "addProductNameAndQuantity")
    public void makeActiveAndSaveProduct() {
        driver.findElement(By.className(SWITCHER)).click();
        Assert.assertTrue(true, NOTIFICATION_AREA);
        checkNotification();

        waitUntilElementAppearsByID("submit");
        driver.findElement(By.id(SAVE_PRODUCT_BUTTON)).click();
        Assert.assertTrue(true, NOTIFICATION_AREA);
        checkNotification();

    }

    @Test (dependsOnMethods = "makeActiveAndSaveProduct")
    public void goToTheNewProductPage() {
        driver.get(MAIN_URL);
        waitUntilElementAppearsByClassNAme(ALL_PRODUCTS_LINK);
        driver.findElement(By.className(ALL_PRODUCTS_LINK)).click();
        waitUntilElementAppearsByClassNAme("product-title");
        Assert.assertTrue(true, nameOfProduct);
        System.out.println("New product is " + randomName());
        waitUntilElementAppearsByID("products");
        driver.findElement(By.linkText(nameOfProduct)).click();
    }

    @Test (dependsOnMethods = "goToTheNewProductPage")
    public void checkProductNameIsCorrect() {

        waitUntilElementAppearsByTagNAme("h1");
        String nameContainer = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(nameContainer, nameOfProduct);
    }

    @Test (dependsOnMethods = "checkProductNameIsCorrect")
    public void checkProductQuantityIsCorrect() {

        String quantityContainer = driver.findElement(By.xpath(SHOWN_QUANTITY)).getText();
        String actualQuantity = quantityOfProduct + " Товары";
        Assert.assertEquals(quantityContainer, actualQuantity);
    }
    @Test (dependsOnMethods = "checkProductQuantityIsCorrect")
    public void checkProductPriceIsCorrect() {
        String priceContainer = driver.findElement(By.xpath(SHOWN_PRICE)).getText();
        priceOfProduct = priceOfProduct.replace(".", ",");
        String actualPrice = priceOfProduct +  " ₴";
        Assert.assertEquals(priceContainer, actualPrice);
    }

    @AfterTest
    private void tearDown() {
        driver.quit();
    }

    String randomQuantity() {
        Random rand = new Random();
        int randomNum = rand.nextInt((100 - 1) + 1) + 1;
        return Integer.toString(randomNum);
    }


    String randomPrice() {

        double x = (Math.random()*((100.0-0.1)+1))+0.1;
        double newDouble = new BigDecimal(x).setScale(2, RoundingMode.UP).doubleValue();
        return Double.toString(newDouble);
    }

    String randomName() {
        String characters = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
        String randomString = "";
        int length = 5;

        Random rand = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }

        return randomString;
    }


    private void checkNotification() {
        waitUntilElementAppearsByID(NOTIFICATION_AREA);
        driver.findElement(By.className(CROSS_SIGN_OF_NOTIFICATION)).click();
    }
}
