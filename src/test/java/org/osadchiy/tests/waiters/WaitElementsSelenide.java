package org.osadchiy.tests.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.driver.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitElements extends BaseTestsClass {
    @Test
    public void removeElementTest(){
        goToUrl("http://the-internet.herokuapp.com/dynamic_controls");
        WebDriver driver = WebDriverHolder.getInstance().getDriver();
        Assert.assertTrue(driver.findElement(By.id("checkbox")).isDisplayed());
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#checkbox-example>#loading"))));
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#checkbox-example>#loading"))));

        Assert.assertEquals(driver.findElements(By.id("checkbox")).size(),0);

        driver.findElement(By.xpath("//button[text()='Add']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#checkbox-example>#loading"))));
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#checkbox-example>#loading"))));
        Assert.assertTrue(driver.findElement(By.id("checkbox")).isDisplayed());



    }
}
