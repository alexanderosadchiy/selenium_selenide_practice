package org.osadchiy.tests.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ActionsTests extends BaseTestsClass {

    @Test
    public void hoverTest() throws InterruptedException {
        goToUrl("http://the-internet.herokuapp.com/hovers");

        WebDriver driver = WebDriverHolder.getInstance().getDriver();

        List<WebElement> elements = driver.findElements(By.cssSelector("div.figure"));

        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            new Actions(driver).moveToElement(element).build().perform();
            Assert.assertEquals(element.findElement(By.xpath(".//h5")).getText(), "name: user" + (i + 1));
            //Thread.sleep(2000);
            element.findElement(By.xpath(".//a")).click();
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.endsWith("user/" + (i + 1)));
            driver.navigate().back();
        }
    }

    @Test
    public void DnDTest() throws InterruptedException {
        goToUrl("http://the-internet.herokuapp.com/drag_and_drop");

        WebDriver driver = WebDriverHolder.getInstance().getDriver();

        WebElement elementA = driver.findElement(By.id("column-a"));
        WebElement elementB = driver.findElement(By.id("column-b"));

        new Actions(driver).
                moveToElement(elementA)
                .clickAndHold()
                .moveToElement(elementB)
                .release().
                build().
                perform();
        Thread.sleep(1000);

        Assert.assertEquals(elementA.getText(), "B");

    }
}
