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
}

