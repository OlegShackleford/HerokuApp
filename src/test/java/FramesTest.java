import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FramesTest {


    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkIframe() {
        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.findElement(By.xpath("//div[@aria-label = 'Close']")).click();
        driver.switchTo().frame("mce_0_ifr");
        String textInFrame = driver.findElement(By.xpath("//body[@id='tinymce']")).getText();
        assertEquals(textInFrame, "Your content goes here.", "Incorrect text of Iframe");
        driver.switchTo().defaultContent();
    }

    @Test
    public void checkNestedFrames() {
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");

        String leftFrameElement = driver.findElement(By.xpath("//body")).getText();
        softAssert.assertEquals(leftFrameElement, "Text in top frame: LEFT", "Incorrect text");

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");

        String middleFrameElement = driver.findElement(By.xpath("//body")).getText();
        softAssert.assertEquals(middleFrameElement, "Text in top frame: MIDDLE", "Incorrect text");

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");

        String rightFrameElement = driver.findElement(By.xpath("//body")).getText();
        softAssert.assertEquals(rightFrameElement, "Text in top frame: RIGHT", "Incorrect text");

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");

        String bottomFrame = driver.findElement(By.xpath("//body")).getText();
        softAssert.assertEquals(bottomFrame, "BOTTOM", "Incorrect text");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
