import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NotificationMessage {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkNotification(){
        /*
        1.Open page https://the-internet.herokuapp.com/notification_message_rendered
        2.Click on page
        3.Check notification message
        4.Refresh page and check notification message
         */
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        String succesfulMessage = "Action successful";
        String unsuccesfulMessage = "Action unsuccesful, please try again";
        driver.findElement(By.cssSelector("[href=\"/notification_message\"]")).click();
        List<String> notifications = new ArrayList<>();
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < 5; i++) {
            String notificationText = driver.findElement(By.id("flash")).getText()
                    .replace("×", "").trim();
            if (!notifications.contains(notificationText)) {
                notifications.add(notificationText);
            }
            driver.navigate().refresh();
            driver.findElement(By.cssSelector("[href=\"/notification_message\"]")).click();
        }
        if (notifications.get(1).contains(succesfulMessage)){
            softAssert.assertEquals(notifications.get(0),succesfulMessage);
            softAssert.assertEquals(notifications.get(1),unsuccesfulMessage);
        } else {
            softAssert.assertEquals(notifications.get(1),unsuccesfulMessage);
            softAssert.assertEquals(notifications.get(0),succesfulMessage);
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
