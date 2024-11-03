import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class InputTest {


    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInput(){
        /*
        1.Open page https://the-internet.herokuapp.com/inputs
        2.Check the possibility to enter number
        3.Check the possibility to increase the number
        4.Check the possibility to reduce the number
        5.Check enter the character
         */
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.findElement(By.tagName("input")).sendKeys("16");
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < 9; i++) {
            driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        }
        WebElement element = driver.findElement(By.tagName("input"));
        softAssert.assertEquals(element.getAttribute("value"),"25","Expected number 25");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(element.getAttribute("value"),"24","Expected number 24");
        driver.findElement(By.tagName("input")).sendKeys("west");
        softAssert.assertNotEquals(element.getAttribute("value"),
                "west",
                "The input text must not be visible");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
