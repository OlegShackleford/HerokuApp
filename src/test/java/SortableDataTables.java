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
import java.util.ArrayList;
import java.util.List;

public class SortableDataTables {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDataTables(){
        /*
        1.Open page http://the-internet.herokuapp.com/tables
        2.Check the cell "Last Name" for compliance.
        3.Check the cell "First name" for compliance.
        4.Check the cell "Email" for compliance.
        5.Check the cell "Due" for compliance.
        6.Check the cell "Website" for compliance.
         */
        driver.get("http://the-internet.herokuapp.com/tables");
        List<WebElement> userInfo = new ArrayList<>();
        userInfo.add(driver.findElement(By.xpath("//table//tr[1]//td[1]")));
        userInfo.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[2]")));
        userInfo.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[3]")));
        userInfo.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[4]")));
        userInfo.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[5]")));

        List<String> expectedUserInfo = new ArrayList<>();
        expectedUserInfo.add("Smith");
        expectedUserInfo.add("John");
        expectedUserInfo.add("jsmith@gmail.com");
        expectedUserInfo.add("$50.00");
        expectedUserInfo.add("http://www.jsmith.com");

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < userInfo.size(); i++) {
            softAssert.assertEquals(userInfo.get(i).getText(),
                    expectedUserInfo.get(i),
                    "Expected data: " + expectedUserInfo.get(i));
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
