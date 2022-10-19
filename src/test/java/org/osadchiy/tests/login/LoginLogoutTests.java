package org.osadchiy.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;

public class LoginLogoutTests extends BaseTestsClass {

    private final String USER_NAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";

    @BeforeClass
    public void beforeClass(){
        goToUrl("http://the-internet.herokuapp.com/login");
    }

    @Test
    public void loginTest(){
        enterUserName(USER_NAME);
        enterUserPass(PASSWORD);
        clickLogin();
        Assert.assertTrue(isLogoutVisible());
        Assert.assertTrue(isSuccessMessageExist());
        logout();
        Assert.assertTrue(isSuccessMessageExist());

    }

    @Test
    public void logingFailTest(){
        enterUserName(USER_NAME + "1");
        enterUserPass(PASSWORD);
        clickLogin();
        Assert.assertTrue(isFailMessageExist());
    }

    public boolean isSuccessMessageExist(){
        return findElementBy(By.cssSelector("#flash-messages .success")).isDisplayed();
    }

    public boolean isFailMessageExist(){
        return findElementBy(By.cssSelector("#flash-messages .error")).isDisplayed();
    }

    public void enterUserName (String userName){
        enterValueToInput(By.cssSelector("#username"),userName);
    }

    public void enterUserPass (String userPass){
        enterValueToInput(By.cssSelector("#password"),userPass);
    }

    public void clickLogin(){
        findElementBy(By.cssSelector("button.radius")).click();
    }

    public void logout(){
        findElementBy(By.cssSelector("a.button")).click();
    }

    public boolean isLogoutVisible(){
        return findElementBy(By.cssSelector("a.button")).isDisplayed();
    }

    private WebElement findElementBy(By by){
        return WebDriverHolder
                .getInstance()
                .getDriver().
                findElement(by);
    }

    private void enterValueToInput (By by, String text){
        WebElement element = findElementBy(by);
        element.clear();
        element.sendKeys(text);

    }
}
