package org.osadchiy.pages.selenide;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecureAreaPage extends BaseLoginPage {

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
