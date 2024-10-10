import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class junitAssignment {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
@Test
    public void formAutomation() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Utils.scroll(driver,100);

        driver.findElement(By.id("edit-name")).sendKeys("Hania Kabir");
        driver.findElement(By.id("edit-number")).sendKeys("01684266101");

        WebElement txtCalendarElement = driver.findElement(By.id("edit-date"));
        txtCalendarElement.sendKeys("1010");
        txtCalendarElement.sendKeys(Keys.ARROW_RIGHT);
        txtCalendarElement.sendKeys("2024");


//        txtCalendarElem.sendKeys(Keys.ENTER);
//        Thread.sleep(2000);
        driver.findElement(By.id("edit-email")).sendKeys("hania@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am a singer.");

        String relativePath="\\src\\test\\resources\\banner.jpg";
        String absolutePath=System.getProperty("user.dir")+relativePath;
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(absolutePath);
        Utils.scroll(driver,500);
        Thread.sleep(3000);
        Utils.scroll(driver,500);
        driver.findElement(By.id("edit-age")).click();
        Thread.sleep(3000);
        Utils.scroll(driver,300);
        driver.findElement(By.id("edit-submit")).click();
        String actualResult = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(actualResult.contains("Thank you for your submission!"));


    }

    @AfterAll
    public void finishTest(){
        driver.quit();
    }
}
