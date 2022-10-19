package org.osadchiy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FirstSeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void firstTest() throws InterruptedException {
        driver.navigate().to("https://www.google.com/");
        driver.findElement(By.xpath("(//button)[4]")).click();
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
        element.sendKeys("Provectus", Keys.ENTER);

        Thread.sleep(5000);

        List<WebElement> elements = driver.findElements(By.xpath("//a/h3"));

        for (WebElement element1 : elements) {
            System.out.println(element1.getText());
            Assert.assertTrue(element1.getText().contains("Provectus"));
        }
    }

    @AfterMethod
    public void afterTest() {
        if (driver != null) {
            driver.close();
        }

    }
}

