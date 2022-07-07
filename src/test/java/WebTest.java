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

    @Test
    public void test0_9() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/abc.html";
        String expectedResult = "0-9";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement submenu0_9 = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='0.html']")
        );
        String actualResult = submenu0_9.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testNamesSiteCreators() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String[] expectedResult = {"Oliver Schade", "Gregor Scheithauer", "Stefan Scheler"};

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement submenuTeam = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='team.html']")
        );
        submenuTeam.click();

        String[] actualResult = new String[3];

        WebElement firstCreatorName = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/h3[text()='Oliver Schade']")
        );
        actualResult[0] = firstCreatorName.getText();
        WebElement secondCreatorName = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/h3[text()='Gregor Scheithauer']")
        );
        actualResult[1] = secondCreatorName.getText();
        WebElement thirdCreatorName = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/h3[text()='Stefan Scheler']")
        );
        actualResult[2] = thirdCreatorName.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testLyricsSong99BottlesOfBeer() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "Lyrics of the song 99 Bottles of Beer";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement submenuSongLyrics = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='lyrics.html']")
        );
        submenuSongLyrics.click();

        WebElement lyricsSong99BottlesOfBeer = driver.findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/h2"));
        String actualResult = lyricsSong99BottlesOfBeer.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testPrivacyEmailOliverSchade() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String[] expectedResult = {"Oliver Schade", "os@ls-la.net"};

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement submenuPrivacy = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='impressum.html']")
        );
        submenuPrivacy.click();

        String[] actualResult = new String[2];

        WebElement siteCreatorOliverSchade = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/h3[text()='Oliver Schade']")
        );
        actualResult[0] = siteCreatorOliverSchade.getText();
        WebElement emailOliverSchade = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/p[text()='os@ls-la.net']")
        );
        actualResult[1] = emailOliverSchade.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testNewComments() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "New Comments";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement menuTopLists = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/toplist.html']")
        );
        menuTopLists.click();

        WebElement submenuNewComments = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li[last()]")
        );
        String actualResult = submenuNewComments.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testFooterMenu() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String[] expectedResult = {"Start", "Browse Languages", "Search Languages", "Top Lists", "Guestbook",
                "Submit new Language"};

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        String[] actualResult = new String[6];

        WebElement footerMenuStart = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='footer']/p/a[@href='/']")
        );
        actualResult[0] = footerMenuStart.getText();
        WebElement footerMenuBrowseLanguages= driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='footer']/p/a[@href='/abc.html']")
        );
        actualResult[1] = footerMenuBrowseLanguages.getText();
        WebElement footerSearchLanguages = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='footer']/p/a[@href='/search.html']")
        );
        actualResult[2] = footerSearchLanguages.getText();
        WebElement footerMenuTopLists = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='footer']/p/a[@href='/toplist.html']")
        );
        actualResult[3] = footerMenuTopLists.getText();
        WebElement footerMenuGuestbook = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='footer']/p/a[@href='/guestbookv2.html']")
        );
        actualResult[4] = footerMenuGuestbook.getText();
        WebElement footerMenuSubmitNewLanguage = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='footer']/p/a[@href='/submitnewlanguage.html']")
        );
        actualResult[5] = footerMenuSubmitNewLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testSubmitLanguage() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String expectedResult = "Error: Precondition failed - Incomplete Input.";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement buttonSubmitLanguage = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/form[@id='addlanguage']/p/input[@type='submit']")
        );
        buttonSubmitLanguage.click();

        WebElement emptyFieldsError = driver.findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/p[@style]"));
        String actualResult = emptyFieldsError.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testSubmitLanguage2() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String expectedResult1 = "Error";
        String expectedResult2 = "Precondition";
        String expectedResult3 = "Incomplete";
        String expectedResult4 = "Input";
        String expectedResult5 = "failed";
        String expectedResult6 = ":";
        String expectedResult7 = "-";
        String expectedResult8 = ".";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checkingSpellingErrorText = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/p[@style]")
        );
        String actualResult1 = checkingSpellingErrorText.getText().substring(0, 5);

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(7, 19), expectedResult2);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(29, 39), expectedResult3);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(40, 45), expectedResult4);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(20, 26), expectedResult5);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(5, 6), expectedResult6);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(27, 28), expectedResult7);
        Assert.assertEquals(checkingSpellingErrorText.getText().substring(45), expectedResult8);

        driver.quit();
    }
}
