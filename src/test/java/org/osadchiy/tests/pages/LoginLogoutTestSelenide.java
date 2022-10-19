package org.osadchiy.tests.pages;

import org.osadchiy.BaseTestsClass;
import org.osadchiy.pages.LoginPage;
import org.osadchiy.pages.SecureAreaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginLogoutTest extends BaseTestsClass {

    private final String USER_NAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";

    @Test
    public void loginTest(){
        goToUrl("http://the-internet.herokuapp.com/login");
        LoginPage loginPage = new LoginPage();
        SecureAreaPage secureAreaPage = loginPage.loginSuccess(USER_NAME, PASSWORD);
        Assert.assertTrue(secureAreaPage.isSuccessBannerVisible());
        loginPage = secureAreaPage.logout();
        Assert.assertTrue(loginPage.isSuccessBannerVisible());
    }

    @Test
    public void unsuccessLoginTest(){
        goToUrl("http://the-internet.herokuapp.com/login");
        LoginPage loginPage = new LoginPage();
        loginPage = loginPage.loginUnsuccess(USER_NAME + "1", PASSWORD);
        Assert.assertTrue(loginPage.isErrorBannerVisible());
        Assert.assertTrue(loginPage.getErrorBannerText().contains("Your username is invalid!"));
    }
}
