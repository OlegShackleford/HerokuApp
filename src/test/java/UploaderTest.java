import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;

public class UploaderTest {


    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkUpload() {
        File pathOfFile = new File("src/test/resources/testFile.txt");
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.findElement(By.cssSelector("#file-upload")).sendKeys(pathOfFile.getAbsolutePath());
        driver.findElement(By.cssSelector("#file-submit")).click();
        String text = driver.findElement(By.cssSelector("#uploaded-files")).getText();
        Assert.assertEquals(text, "testFile.txt", "Incorrect text");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
