import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class Register {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://shop.pragmatic.bg");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registerTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@id='top-links'])//li[2]")));
        WebElement accountButton = driver.findElement(By.xpath("(//div[@id='top-links'])//li[2]"));
        accountButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id=\"top-links\"])//li//li[1]")));
        WebElement registerButton = driver.findElement(By.xpath("(//*[@id=\"top-links\"])//li//li[1]"));
        registerButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("Karolina");
        WebElement lastName = driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Koleva");
        WebElement telephone = driver.findElement(By.id("input-telephone"));
        telephone.sendKeys("0888888888");
        String emailPrefix = RandomStringUtils.randomAlphabetic(7);
        String emailSuffix = RandomStringUtils.randomAlphabetic(5);
        String emailAddress = emailPrefix + "@" + emailSuffix + ".com";
        WebElement email = driver.findElement(By.id("input-email"));
        email.sendKeys(emailAddress);
        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("karolina12345");
        WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
        confirmPassword.sendKeys("karolina12345");
        WebElement agreeButton = driver.findElement(By.name("agree"));
        agreeButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary")));
        WebElement continueButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        continueButton.click();

        wait.until(ExpectedConditions.titleContains("Your Account Has Been Created!"));
        assertTrue(driver.getTitle().equals("Your Account Has Been Created!"));
        WebElement check = driver.findElement(By.id("content"));
    }
}

