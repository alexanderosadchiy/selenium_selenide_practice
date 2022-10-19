package org.osadchiy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.osadchiy.WebDriverHolder;

public class SecureAreaPage extends BaseLoginPage{

    @FindBy(css = "a>i")
    private WebElement logoutButton;

     public SecureAreaPage(){
        super();
    }

    public LoginPage logout(){
        logoutButton.click();
        return new LoginPage();
    }



}
