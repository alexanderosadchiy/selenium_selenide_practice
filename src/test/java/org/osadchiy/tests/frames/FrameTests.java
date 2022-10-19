package org.osadchiy.tests.frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameTests extends BaseTestsClass {

    @Test
    public void testFrame() {
        goToUrl("http://the-internet.herokuapp.com/nested_frames");

        WebDriver driver = WebDriverHolder.getInstance().getDriver();

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String bodyText = driver.findElement(By.cssSelector("body")).getText();
        Assert.assertTrue(bodyText.contains("LEFT"));

        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame-bottom");
        bodyText = driver.findElement(By.cssSelector("body")).getText();
        Assert.assertTrue(bodyText.contains("BOTTOM"));
    }

}
