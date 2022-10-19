package org.osadchiy.tests.basicauth;

import org.openqa.selenium.By;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

public class BasicAuthTests extends BaseTestsClass {

    @BeforeClass
    public void beforeClass(){
        //goToUrl("http://the-internet.herokuapp.com/basic_auth");
    }

    @Test
    public void basicAuthTest(){
        goToUrl("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(WebDriverHolder.
                getInstance().
                getDriver().
                findElement(By.cssSelector(".example>h3"))
                .getText(), "Basic Auth");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
