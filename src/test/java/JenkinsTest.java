import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JenkinsTest {

    private static final String BASE_URL = "http://99-bottles-of-beer.net/lyrics.html";
    private static final String BROWSE_LANGUAGE = "//ul[@id='menu']/li/a[@href='/abc.html']";

    private void getBottles(StringBuilder songLyrics, int number, String btl) {
        songLyrics.append(number).append(btl);
    }

    private void getNoMore(StringBuilder songLyrics, String noMore, String btl) {
        songLyrics.append(noMore).append(btl);
    }

    private String createSongLyrics() {
        final String BOTTLES_WALL_COMA_SPACE = " bottles of beer on the wall, ";
        final String BOTTLES_DOT_LINE = " bottles of beer.\n";
        final String BOTTLES_WALL_DOT = " bottles of beer on the wall.";
        final String TAKE_COMA_SPACE = "Take one down and pass it around, ";
        final String NO_MORE = "No more";
        final String GO = "Go to the store and buy some more, ";

        StringBuilder songLyrics = new StringBuilder();

        getBottles(songLyrics, 99, BOTTLES_WALL_COMA_SPACE);
        getBottles(songLyrics, 99, BOTTLES_DOT_LINE);

        for (int i = 98; i > -1; i--) {
            songLyrics.append(TAKE_COMA_SPACE);

            if (i == 1) {
                getBottles(songLyrics, i, BOTTLES_WALL_DOT.replace("s", ""));
                getBottles(songLyrics, i, BOTTLES_WALL_COMA_SPACE.replace("s", ""));
                getBottles(songLyrics, i, BOTTLES_DOT_LINE.replace("s", ""));
            } else if (i == 0) {
                getNoMore(songLyrics, NO_MORE.toLowerCase(), BOTTLES_WALL_DOT);
                getNoMore(songLyrics, NO_MORE, BOTTLES_WALL_COMA_SPACE);
                getNoMore(songLyrics, NO_MORE.toLowerCase(), BOTTLES_DOT_LINE);
            } else {
                getBottles(songLyrics, i, BOTTLES_WALL_DOT);
                getBottles(songLyrics, i, BOTTLES_WALL_COMA_SPACE);
                getBottles(songLyrics, i, BOTTLES_DOT_LINE);
            }
        }

        songLyrics.append(GO);
        getBottles(songLyrics, 99, BOTTLES_WALL_DOT);

        return songLyrics.toString();
    }

    @Test
    public void testSongLyricsText() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        final String expectedResult = createSongLyrics();
        By pTags = By.xpath("//div[@id='main']/p");
        By menuSongLyrics = By.linkText("Song Lyrics");

        driver.get(BASE_URL);
        driver.findElement(menuSongLyrics).click();

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> pAllElements = driver.findElements(pTags);

        Assert.assertFalse(pAllElements.isEmpty());

        for (WebElement pEachElement : pAllElements) {
            actualResult.append(pEachElement.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult);

        driver.quit();
    }

    @Test
    public void testBROWSE_LANGUAGES_J() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath(BROWSE_LANGUAGE)).click();
        driver.findElement(By.xpath("//a[@href='j.html']")).click();
        String actualResult = driver.findElement(By.xpath("//div[@id='main']/p[text()]")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testBROWSE_LANGUAGES_M() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        String expectedResult = "MySQL";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath(BROWSE_LANGUAGE)).click();
        driver.findElement(By.xpath("//a[@href='m.html']")).click();
        String actualResult = driver.findElement(By.xpath("//tr[last()]/td/a")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testBROWSE_LANGUAGES_TablesHeaders() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath(BROWSE_LANGUAGE)).click();
        String[] expectedResult = {"Language", "Author", "Date", "Comments", "Rate"};

        String[] actualResult = new String[5];
        for (int i = 0; i < actualResult.length; i++) {
            int index = i + 1;
            actualResult[i] = driver
                    .findElement(By.xpath("//tbody/tr/th[" + index + "]"))
                    .getText();
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testMathematicaLanguage() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        String expectedResultLanguage = "Mathematica";
        String expectedResultCreator = "Brenton Bostick";
        String expectedResultData = "03/16/06";
        String expectedResultComments = "1";

        StringBuilder expectedResult = new StringBuilder();
        expectedResult
                .append(expectedResultLanguage)
                .append(" ")
                .append(expectedResultCreator)
                .append(" ")
                .append(expectedResultData)
                .append(" ")
                .append(expectedResultComments);

        driver.get(BASE_URL);
        driver.findElement(By.xpath(BROWSE_LANGUAGE)).click();
        driver.findElement(By.linkText("M")).click();

        List<WebElement> trs = driver.findElements(By.xpath("//table[@id='category']/tbody/tr"));

        List<String> actualResult = new ArrayList<>();

        for (WebElement tr : trs) {
            if (tr.getText().contains(expectedResultLanguage)) {
                actualResult.add(tr.getText());
            }
        }

        Assert.assertEquals(actualResult.size(), 1);
        Assert.assertFalse(actualResult.get(0).isEmpty());
        Assert.assertEquals(actualResult.get(0), expectedResult.toString());

        driver.quit();
    }

    @Test
    public void testTenLanguageStartNumbers() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        int expectedResult = 10;

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath(BROWSE_LANGUAGE)).click();
        driver.findElement(By.xpath("//a[@href='0.html']")).click();
        int actualResult = driver.findElements(By.xpath("//tbody/tr/td/a")).size();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testSignGuestbook() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        String expectedResult = "Error: Error: Invalid security code.";

        String random = "" + ((int) (Math.random() * 900) + 100);

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//a[@href='/guestbookv2.html']")).click();
        driver.findElement(By.xpath("//a[@href='./signv2.html']")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Nataliia");
        driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Louisiana");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("psvnatali@gmail.com");
        driver.findElement(By.xpath("//input[@name='homepage']")).sendKeys("lagoldgymnastics.com");
        driver.findElement(By.xpath("//input[@name='captcha']")).sendKeys(random);
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("test message");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String actualResult = driver.findElement(By.xpath("//div[@id='main']/p[contains(text(),' Error: Invalid security code.')]"))
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testBrowseLanguagesAlternativeVersions() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        String expectedResult = "Log in";

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//ul/li/a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//ul/li/a[@ href='j.html']")).click();
        driver.findElement(By.xpath("//table/tbody/tr/td/a[@href='language-java-3.html']")).click();
        driver.findElement(By.xpath("//table/tbody/tr/td/a[@href='language-java-4.html']")).click();
        driver.findElement(By.xpath("//div[@id='voting']/p/a[@title='reddit']")).click();

        String actualResult =
                driver.findElement(By.xpath("//div[@class='Step__content']/h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testTopListShakespeare() {
        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        Boolean expectedResult = false;

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();

        String[] result = new String[20];
        for (int i = 1; i < result.length; i++) {
            if (driver.findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertTrue(expectedResult);

        driver.quit();
    }

    @Test
    public void testTopEsotericShakespeare() {
        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        Boolean expectedResult = false;

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();
        driver.findElement(By.xpath("//ul/li/a[@href='./toplist_esoteric.html']")).click();

        String[] result = new String[10];
        for (int i = 1; i < result.length; i++) {
            if (driver.findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertTrue(expectedResult);

        driver.quit();
    }

    @Test
    public void testTopHitsShakespeare() {
        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        Boolean expectedResult = false;

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();
        driver.findElement(By.xpath("//ul/li/a[@href='./tophits.html']")).click();

        String[] result = new String[8];
        for (int i = 1; i < result.length; i++) {
            if (driver.findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertTrue(expectedResult);

        driver.quit();
    }

    @Test
    public void testTopRealShakespeare() {
        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        Boolean expectedResult = false;

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();
        driver.findElement(By.xpath("//a[@href='./toplist_real.html']")).click();

        String[] result = new String[25];
        for (int i = 1; i < result.length; i++) {
            if (driver.findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertFalse(expectedResult);

        driver.quit();
    }

    @Test
    public void testVersionsOfJava() {

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        int expectedResult = 6;

        driver.get(BASE_URL);
        driver.findElement(By.xpath(BROWSE_LANGUAGE)).click();
        driver.findElement(By.xpath("//a[@href='j.html']")).click();
        driver.findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        int actualResult = driver
                .findElements(By.xpath("//table[@id='category']/tbody/tr/td/a")).size() + 1;

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testCommentsQuantity(){

        String chromeDriver = "webdriver.chrome.driver";
        String drivePath = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";

        System.setProperty(chromeDriver, drivePath);
        WebDriver driver = new ChromeDriver();

        String expectedResult = "object-oriented version";

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//li/a[@href='j.html']")).click();
        driver.findElement(By.xpath("//td/a[@href='language-java-3.html']")).click();

        int firstLanguage = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[2]/td[4]")).getText());
        int secondLanguage = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[3]/td[4]")).getText());
        int thirdLanguage = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[4]/td[4]")).getText());
        int fourthLanguage = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[5]/td[4]")).getText());
        int fifthLanguage = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[6]/td[4]")).getText());

        driver.findElement(By.xpath("//td/a[@href='language-java-4.html']")).click();

        int sixthLanguage = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[2]/td[4]")).getText());

        int[] maxQuantity = new int[] {firstLanguage,secondLanguage,thirdLanguage, fourthLanguage,fifthLanguage,sixthLanguage};

        int maxComments = 0;
        for (int i = 0; i < maxQuantity.length; i++) {
            if(maxComments <= maxQuantity[i]){
                maxComments = maxQuantity[i];
            }
        }
        String actualResult = driver.findElement(By.xpath("//table[@id='category']/tbody/tr/td[1]")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
}

