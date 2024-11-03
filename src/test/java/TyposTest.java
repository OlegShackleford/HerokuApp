import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;

public class TyposTest {


    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTypos(){
        /*
        1.Open page https://the-internet.herokuapp.com/typos
        2.Check the spelling of the paragraph - "Sometimes you'll see a typo, other times you won't."
         */
        driver.get("https://the-internet.herokuapp.com/typos");
        String correctText = "Sometimes you'll see a typo, other times you won't.";
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < 15; i++) {
            driver.navigate().refresh();
            List<WebElement> message = driver.findElements(By.tagName("p"));
            String updateText = message.get(1).getText();
            softAssert.assertEquals(updateText,
                    correctText,
                    "Correct message must be - Sometimes you'll see a typo, other times you won't.");
            softAssert.assertAll();
            }
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown(){
        driver.quit();
        }
    }

