package org.osadchiy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlertTest {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void jsAlertTest() {
        clickForJsAlert();
        String alertText = manipulateWithAlertAndCloseIt(true, null);
        String resultText = getResultText();
        Assert.assertEquals(alertText, "I am a JS Alert", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test
    public void jsAlertTestByJS() {
        clickForJsAlertJS();
        String alertText = manipulateWithAlertAndCloseIt(true, null);
        String resultText = getResultTextByJS();
        Assert.assertEquals(alertText, "I am a JS Alert", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test
    public void jsAlertTestByJSExt() {
        clickForJsAlertJSExt();
        String alertText = manipulateWithAlertAndCloseIt(true, null);
        String resultText = getResultText();
        Assert.assertEquals(alertText, "I am a JS Alert", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test(dataProvider = "dataProvider")
    public void jsConfirmTest(boolean accept, String resultTextExpected) {
        clickForJsConfirm();
        String alertText = manipulateWithAlertAndCloseIt(accept, null);
        String resultText = getResultText();
        Assert.assertEquals(alertText, "I am a JS Confirm", "Your text is wrong!!!");
        Assert.assertEquals(resultText, "You clicked: " + resultTextExpected);
    }

    @Test(dataProvider = "dataProviderAlertPrompt")
    public void jsPromptTest(boolean accept, String textToEnter) {
        clickForJsPrompt();
        String alertText = manipulateWithAlertAndCloseIt(accept, textToEnter);
        String resultText = getResultText();
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

    private WebElement getClickForAlertButton(String textOnButton) {
        return driver.findElement(By.xpath("//button[text()='%s']".formatted(textOnButton)));
    }

    private String getResultText() {
        return driver.findElement(By.id("result")).getText();
    }

    private String getResultTextByJS() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement result = driver.findElement(By.id("result"));
        return javascriptExecutor.executeScript( " return arguments[0].textContent;" , result).toString();
    }

    /**
     * Manipulation with alert window. Note! Alert window should exist.
     *
     * @param accept
     * @param text
     * @return
     */

    private String manipulateWithAlertAndCloseIt(boolean accept, String text) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (text != null) {
            alert.sendKeys(text);
        }

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return alertText;
    }

    public void clickForJsAlert() {
        getClickForAlertButton("Click for JS Alert").click();
    }

    public void clickForJsConfirm() {
        getClickForAlertButton("Click for JS Confirm").click();
    }

    public void clickForJsPrompt() {
        getClickForAlertButton("Click for JS Prompt").click();
    }

    public void clickForJsAlertJS() {
        clickButtonByJS("Click for JS Alert");
    }

    public void clickForJsConfirmJS() {
        clickButtonByJS("Click for JS Confirm");
    }

    public void clickForJsPromptJS() { clickButtonByJS("Click for JS Prompt"); }

    public void clickForJsAlertJSExt() {
        clickButtonByJSExt("Click for JS Alert");
    }

    public void clickForJsConfirmJSExt() {
        clickButtonByJSExt("Click for JS Confirm");
    }

    public void clickForJsPromptJSExt() { clickButtonByJSExt("Click for JS Prompt"); }

    private void clickButtonByJS(String textOnButton) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        switch (textOnButton) {
            case "Click for JS Alert":
                javascriptExecutor.executeScript("return jsAlert();");
                break;
            case "Click for JS Confirm":
                javascriptExecutor.executeScript("return jsConfirm();");
                break;
            case "Click for JS Prompt":
                javascriptExecutor.executeScript("return jsPrompt();");
                break;
        }
    }

    private void clickButtonByJSExt(String textOnButton) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement button = getClickForAlertButton(textOnButton);
        javascriptExecutor.executeScript("arguments[0].click()", button);

        }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
