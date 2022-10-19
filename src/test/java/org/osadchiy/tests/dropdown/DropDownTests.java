package org.osadchiy.tests.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTests extends BaseTestsClass {
    @Test
    public void dropdownTest(){
        goToUrl("http://the-internet.herokuapp.com/dropdown");
        Select dd = new Select(WebDriverHolder.getInstance().getDriver()
                .findElement(By.id("dropdown")));

        dd.selectByVisibleText("Option 2");
        String value = dd.getAllSelectedOptions().get(0).getAttribute("value");
        Assert.assertEquals(value, "2");

        dd.selectByValue("1");
        value = dd.getAllSelectedOptions().get(0).getText();
        Assert.assertEquals(value, "Option 1");

    }
}
