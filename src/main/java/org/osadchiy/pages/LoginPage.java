package org.osadchiy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.osadchiy.WebDriverHolder;

public class LoginPage extends BaseLoginPage{

    @FindBy(id = "username")
    private WebElement userNameField;

    @FindBy(name = "password")
    private WebElement userPassField;

    @FindBy(css = "button.radius")
    private WebElement loginButton;

    public LoginPage(){
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    public LoginPage enterUserName(String userName){
        return enterDataToField(userNameField, userName);
    }

    public LoginPage enterUserPass(String password){
        return enterDataToField(userPassField, password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public SecureAreaPage loginSuccess(String name, String password){
        enterUserName(name).enterUserPass(password).clickLoginButton();
        return new SecureAreaPage();

    }

    public LoginPage loginUnsuccess(String name, String password){
        enterUserName(name).enterUserPass(password).clickLoginButton();
        return new LoginPage();
    }

    private LoginPage enterDataToField (WebElement fieldElement, String value){
        fieldElement.clear();
        fieldElement.sendKeys(value);
        return this;
    }

}
