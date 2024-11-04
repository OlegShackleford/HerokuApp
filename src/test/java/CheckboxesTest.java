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

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAndUncheckCheckBoxes(){
        /*
        1.Open page "https://the-internet.herokuapp.com/checkboxes"
        2.Check that the first checkbox is unchecked
        3.Make the first checkbox check
        4.Check that the first checkbox is checked
        5.Check that the second checkbox is checked
        6.Make the second checkbox unchecked
         */
        driver.get("https://the-internet.herokuapp.com/inputs");
        List<WebElement> checkBoxOption = driver.findElements(By.cssSelector("[type = checkbox]"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(checkBoxOption.get(0).isSelected(),"First checkBox must be unselected");
        checkBoxOption.get(0).click();
        softAssert.assertTrue(checkBoxOption.get(0).isSelected(),"First checkbox must be checked");
        softAssert.assertTrue(checkBoxOption.get(1).isSelected(),"Second checkbox must be selected");
        checkBoxOption.get(1).click();
        softAssert.assertFalse(checkBoxOption.get(1).isSelected(),"Second checkbox must be unchecked");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
