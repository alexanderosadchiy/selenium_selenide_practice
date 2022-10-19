package org.osadchiy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WebDriverHolder {
    private static WebDriverHolder INSTANCE = null;

    private WebDriver driver;

    private WebDriverHolder() {
        driver = WebDriverFactory.initDriver(WebDriverType.CHROME);
    }

    public static WebDriverHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebDriverHolder();
        }

        return INSTANCE;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
