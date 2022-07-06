import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTest {

    @Test
    public void testMenuStartTitle() throws InterruptedException {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "Welcome to 99 Bottles of Beer";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement menuBrowseLanguages = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")
        );
        menuBrowseLanguages.click();

        WebElement menuStart = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/']")
        );
        menuStart.click();

        WebElement h2 = driver.findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/h2"));
        String actualResult = h2.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void test99BottlesOfBeer() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "99 Bottles of Beer";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement h1 = driver.findElement(By.xpath("//body/div[@id='wrap']/div[@id='header']/h1"));
        String actualResult = h1.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testSubmitNewLanguage() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "SUBMIT NEW LANGUAGE";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement menuSUBMIT_NEW_LANGUAGE = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li[last()]")
        );
        String actualResult = menuSUBMIT_NEW_LANGUAGE.getText();

        Assert.assertEquals(actualResult, expectedResult.toUpperCase());

        driver.quit();
    }

    @Test
    public void testSubmitNewLanguageSubmenu() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "Submit New Language";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement menuSUBMIT_NEW_LANGUAGE = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/submitnewlanguage.html']")
        );
        menuSUBMIT_NEW_LANGUAGE.click();

        WebElement submenuSubmitNewLanguage = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='./submitnewlanguage.html']")
        );
        String actualResult = submenuSubmitNewLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
}
