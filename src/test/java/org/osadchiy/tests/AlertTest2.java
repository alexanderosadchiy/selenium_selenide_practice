package org.osadchiy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.WebDriverFactory;
import org.osadchiy.WebDriverHolder;
import org.osadchiy.WebDriverType;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlertTest2 extends BaseTestsClass {
    // private WebDriver driver;

    AlertTestsLib alertTestLib = new AlertTestsLib();

    @BeforeMethod
    public void beforeMethod() {
        alertTestLib.goToUrl("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void jsAlertTest() {
        alertTestLib.clickForJsAlert();
        String alertText = alertTestLib.manipulateWithAlertAndCloseIt(true, null);
        String resultText = alertTestLib.getResultText();
        Assert.assertEquals(alertText, "I am a JS Alert", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test
    public void jsAlertTestByJS() {
        alertTestLib.clickForJsAlert();
        String alertText = alertTestLib.manipulateWithAlertAndCloseIt(true, null);
        String resultText = alertTestLib.getResultTextByJS();
        Assert.assertEquals(alertText, "I am a JS Alert", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test
    public void jsAlertTestByJSExt() {
        alertTestLib.clickForJsAlertJSExt();
        String alertText = alertTestLib.manipulateWithAlertAndCloseIt(true, null);
        String resultText = alertTestLib.getResultText();
        Assert.assertEquals(alertText, "I am a JS Alert", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test(dataProvider = "dataProvider")
    public void jsConfirmTest(boolean accept, String resultTextExpected) {
        alertTestLib.clickForJsConfirm();
        String alertText = alertTestLib.manipulateWithAlertAndCloseIt(accept, null);
        String resultText = alertTestLib.getResultText();
        Assert.assertEquals(alertText, "I am a JS Confirm", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You clicked: " + resultTextExpected);
    }

    @Test(dataProvider = "dataProviderAlertPrompt")
    public void jsPromptTest(boolean accept, String textToEnter) {
        alertTestLib.clickForJsPrompt();
        String alertText = alertTestLib.manipulateWithAlertAndCloseIt(accept, textToEnter);
        String resultText = alertTestLib.getResultText();
        Assert.assertEquals(alertText, "I am a JS prompt", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You entered: " + textToEnter);
    }

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {true, "Ok"},
                {false, "Cancel"}
        };
    }

    @DataProvider
    public Object[][] dataProviderAlertPrompt() {
        return new Object[][]{
                {true, "Type new text"},
                {false, null}
        };
    }

}
