package com.sang.stasmtesting.bkel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateProfileTest {
    public static SelenideElement loginBtn;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1600x900";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://e-learning.hcmut.edu.vn/");
        loginBtn = $("[class='d-flex align-items-stretch usermenu-container']");
        usernameField = $("[id='username']");
        passwordField = $("[id='password']");
        submitBtn = $("[class='btn-submit']");
        logInBkel();
    }

    @AfterEach
    public void closeWeb(){
        webdriver().driver().close();
    }

    private static void logInBkel() {
        loginBtn.click();
        SelenideElement loginAsStudent = $("[class='btn login-identityprovider-btn btn-block']");
        loginAsStudent.click();

        usernameField.sendKeys(Account.username);
        passwordField.sendKeys(Account.password);
        submitBtn.click();
    }





}
