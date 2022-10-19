package org.osadchiy.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.osadchiy.WebDriverHolder;

public class AlertTestsLib {

    public void goToUrl (String url){
        WebDriverHolder.getInstance().getDriver().get(url);
    }

    private WebElement getClickForAlertButton(String textOnButton) {
        return WebDriverHolder.getInstance().getDriver().findElement(By.xpath("//button[text()='%s']".formatted(textOnButton)));
    }

    public String getResultText() {
        return WebDriverHolder.getInstance()
                .getDriver()
                .findElement(By.cssSelector("#result"))
                .findElement(By.xpath("./.."))
                .getText();
    }

    public String getResultTextByJS() {
        JavascriptExecutor javascriptExecutor = WebDriverHolder.getInstance().getJavascriptExecutor();
        WebElement result = WebDriverHolder.getInstance().getDriver().findElement(By.id("result"));
        return javascriptExecutor.executeScript( " return arguments[0].textContent;" , result).toString();
    }

    /**
     * Manipulation with alert window. Note! Alert window should exist.
     *
     * @param accept
     * @param text
     * @return
     */

    public String manipulateWithAlertAndCloseIt(boolean accept, String text) {
        Alert alert = WebDriverHolder.getInstance().getDriver().switchTo().alert();
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
        JavascriptExecutor javascriptExecutor = WebDriverHolder.getInstance().getJavascriptExecutor();
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
        JavascriptExecutor javascriptExecutor = WebDriverHolder.getInstance().getJavascriptExecutor();
        WebElement button = getClickForAlertButton(textOnButton);
        javascriptExecutor.executeScript("arguments[0].click()", button);

    }

}
