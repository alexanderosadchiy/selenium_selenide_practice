package org.osadchiy;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

public class FirstSeleniumProgram {

    @SneakyThrows
    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver", new File("seleniumWebDriver/chromedriver.exe").getAbsolutePath());
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.google.com/");
        driver.findElement(By.xpath("(//button)[4]")).click();
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
        element.sendKeys("Provectus", Keys.ENTER);

        Thread.sleep(5000);

        List<WebElement> elements = driver.findElements(By.xpath("//a/h3"));

        for (WebElement element1: elements){
            String text = element1.getText();
            if (!text.contains("Provectus")){
                System.out.println("Something wrong!!!");
            }
        }
        driver.close();


    }

}
