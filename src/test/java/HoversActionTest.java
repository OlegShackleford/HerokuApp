import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HoversActionTest {


    WebDriver driver;

    @Test
    public void checkFirstAction() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(driver);
        SoftAssert softAssert = new SoftAssert();
        /*
        1 - нахожу первый элемент по локатору
        2 - навожу мышку
        3 - получаю описание по локатору name = User
        4 - сравниваю имена
        5 - нахожу ссылку View Profile и кликаю по ней
        6 - получаю сообщение и сравниваю
        7 - возвращаюсь, чтоб повторить все действия.
         */
        WebElement firstUser = driver.findElement(By.xpath("(//img[@alt = 'User Avatar'])[1]"));
        actions.moveToElement(firstUser).perform();
        String name = driver.findElement(By.xpath("//h5[text()='name: user1']")).getText();
        softAssert.assertEquals(name, "name: user1", "Expected message = " + name);
        driver.findElement(By.cssSelector("a[href='/users/1']")).click();
        String message = driver.findElement(By.xpath("//h1[text() = 'Not Found']")).getText();
        softAssert.assertEquals(message, "Not Found", "Expected message = Not Found");
        driver.navigate().back();

        WebElement secondUser = driver.findElement(By.xpath("(//img[@alt = 'User Avatar'])[2]"));
        actions.moveToElement(secondUser).perform();
        String secondName = driver.findElement(By.xpath("//h5[text()='name: user2']")).getText();
        softAssert.assertEquals(secondName, "name: user2", "Expected message = " + name);
        driver.findElement(By.cssSelector("a[href='/users/2']")).click();
        softAssert.assertEquals(message, "Not Found", "Expected message = Not Found");
        driver.navigate().back();

        WebElement thirdUser = driver.findElement(By.xpath("(//img[@alt = 'User Avatar'])[3]"));
        actions.moveToElement(thirdUser).perform();
        String thirdName = driver.findElement(By.xpath("//h5[text()='name: user3']")).getText();
        softAssert.assertEquals(thirdName, "name: user3", "Expected message = " + name);
        driver.findElement(By.cssSelector("a[href='/users/3']")).click();
        softAssert.assertEquals(message, "Not Found", "Expected message = Not Found");
        driver.navigate().back();

        driver.quit();
    }

//    public void checkSecondWayToAction(){
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("http://the-internet.herokuapp.com/hovers");
//        Actions actions = new Actions(driver);
//        SoftAssert softAssert = new SoftAssert();
//        List<String> dataNames = new ArrayList<>();
//        dataNames.add("name: user1");
//        dataNames.add("name: user2");
//        dataNames.add("name: user3");
//
//        List<WebElement> elements = driver.findElements(By.cssSelector("[alt = 'User Avatar']"));
//        for (int i = 0; i < elements.size(); i++) {
//
//            actions.moveToElement(elements.get(i)).perform();
//            WebElement nameElement = driver.findElement(By.xpath(String.format("//h5[text()='%s']", dataNames.get(i))));
//            String actualName = nameElement.getText();
//
//            Assert.assertEquals(actualName,dataNames.get(i));
//            softAssert.assertEquals(actualName,dataNames.get(i));
//
//
//        }
//    }
}
