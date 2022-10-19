package org.osadchiy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.osadchiy.WebDriverHolder;

public class BaseLoginPage {

    @FindBy(css = "#flash.error")
    private WebElement errorBanner;

    @FindBy(css = "#flash.success")
    private WebElement successBanner;

    public BaseLoginPage(){
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    public boolean isErrorBannerVisible(){
        return errorBanner.isDisplayed();
    }

    public boolean isSuccessBannerVisible(){
        return successBanner.isDisplayed();
    }

    public String getErrorBannerText(){
        return errorBanner.getText();
    }

    public String getSuccessBannerText(){
        return successBanner.getText();
    }

}
