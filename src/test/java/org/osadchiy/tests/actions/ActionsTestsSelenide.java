package org.osadchiy.tests.actions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.WebDriverConditions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.osadchiy.driver.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ActionsTests {

    @Test
    public void hoverTest() throws InterruptedException {
        open("http://the-internet.herokuapp.com/hovers");

        ElementsCollection elements = $$("div.figure");

        for (int i = 0; i < elements.size(); i++) {
            SelenideElement element = elements.get(i);
            $(element).hover();
            Assert.assertEquals($x(".//h5").text(), "name: user" + (i + 1));
            //Thread.sleep(2000);
            $x(".//a").click();
            Thread.sleep(2000);
            webdriver().shouldHave(url("http://the-internet.herokuapp.com/user/" + (i + 1)));
            back();
        }
    }

    @Test
    public void DnDTest() throws InterruptedException {
        open("http://the-internet.herokuapp.com/drag_and_drop");

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
