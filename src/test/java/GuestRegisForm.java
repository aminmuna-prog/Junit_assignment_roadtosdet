import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRegisForm {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void guestFormAutomation() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("Saddam12aiub");
        driver.findElement(By.id("last_name")).sendKeys("Abedin");
        driver.findElement(By.id("user_email")).sendKeys("saddama10@aiub.com");
        driver.findElement(By.id("user_pass")).sendKeys("mariha@10aiubbd");
//        date picker code start
        String year = "2015";
        String day = "11";
        Utils.scroll(driver, 200);
        driver.findElement(By.xpath("//input[@class='ur-flatpickr-field regular-text without_icon flatpickr-input']")).click();

        //get year
        while (true){
            String currentYr = driver.findElement(By.className("cur-year")).getAttribute("value");
            if (currentYr.contains(year)){
                break;
            }
            driver.findElement(By.className("flatpickr-prev-month")).click();
        }
        //get month
        Select dropmonth = new Select(driver.findElement(By.className("flatpickr-monthDropdown-months")));
        dropmonth.selectByVisibleText("May");
        //get day
        List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='dayContainer']//span"));
        for(WebElement dt:alldates){
            if (dt.getText().contains(day)){
                dt.click();
                break;
            }
        }

//        date picker code end
        Thread.sleep(3000);
        driver.findElement(By.id("radio_1665627729_Female")).click();
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");
        Utils.scroll(driver, 200);
        List <WebElement> addressElem = driver.findElements(By.id("phone_1665627880"));
        addressElem.get(1).sendKeys("01682466909");
        Select dropCountry = new Select(driver.findElement(By.id("country_1665629257")));
        dropCountry.selectByVisibleText("Bangladesh");
        Utils.scroll(driver, 500);
        Thread.sleep(1000);
        Utils.scroll(driver, 500);
        driver.findElement(By.id("privacy_policy_1665633140")).click();
        List <WebElement> submitbtns = driver.findElements(By.cssSelector("[type=submit]"));
        submitbtns.get(2).click();
        String actuallResult = driver.findElement(By.xpath("//div[@id='ur-submit-message-node']//ul")).getText();
        Assertions.assertTrue(actuallResult.contains("User successfully registered"));


    }

    public void finishTest(){
        driver.quit();
    }



}

