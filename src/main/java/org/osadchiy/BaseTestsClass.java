package org.osadchiy;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class BaseTestsClass {

    @BeforeSuite
    public void beforeSuite() throws IOException {
        WebDriverHolder.getInstance();
        FileUtils.cleanDirectory(new File("downloads"));
    }

    public void goToUrl (String url){

        WebDriverHolder.getInstance().getDriver().get(url);
    }

    @AfterSuite
    public void afterSuite() {
        WebDriverHolder.getInstance().closeDriver();
    }
}
