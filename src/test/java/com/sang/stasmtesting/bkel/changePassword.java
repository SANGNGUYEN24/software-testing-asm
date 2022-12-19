package com.example.proj3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    public static SelenideElement loginBtn;
    public static SelenideElement myCoursesTab;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;
    public static SelenideElement searchCourseField;
    MainPage mainPage = new MainPage();
    public static SelenideElement prefBtn;
    public static SelenideElement userBtn;
    public static SelenideElement changeBtn;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://127.0.0.1:8888/");
        myCoursesTab = $("[data-key='mycourses']");
        loginBtn = $("[class='login pl-2']");
        usernameField = $("[id='username']");
        passwordField = $("[id='password']");
        submitBtn = $("[id='loginbtn']");

        userBtn = $("[id='user-menu-toggle']");
        prefBtn = $x("//*[@id=\"carousel-item-main\"]/a[6]");
        changeBtn = $x("//*[@id=\"region-main\"]/div/div/div/div[1]/div/div/div/div[2]/a");


        logInBkel();
        changePassword();

    }

    @AfterEach
    public void closeWeb(){
        webdriver().driver().close();
    }

    private static void logInBkel() {
        loginBtn.click();

        usernameField.sendKeys(Account.username);
        passwordField.sendKeys(Account.password);
        submitBtn.click();
    }

    private static void changePassword(){
        userBtn.click();
        prefBtn.click();
        changeBtn.click();
    }

    @Test
    public void CP_001() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_001.oldPwd);
        newPwd.sendKeys(pwd.CP_001.newPwd);
        confPwd.sendKeys(pwd.CP_001.confPwd);

        submitBtn.click();

        $("[id='notice']").shouldBe(visible);
    }

    @Test
    public void CP_002() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_002.oldPwd);
        newPwd.sendKeys(pwd.CP_002.newPwd);
        confPwd.sendKeys(pwd.CP_002.confPwd);

        submitBtn.click();

        $("[id='id_error_newpassword1']").shouldBe(visible);
    }

    @Test
    public void CP_003_NP_01() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_003_NP_01.oldPwd);
        newPwd.sendKeys(pwd.CP_003_NP_01.newPwd);
        confPwd.sendKeys(pwd.CP_003_NP_01.confPwd);

        submitBtn.click();

        $("[id='id_error_newpassword1']").shouldBe(visible);
    }

    @Test
    public void CP_003_NP_02() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_003_NP_02.oldPwd);
        newPwd.sendKeys(pwd.CP_003_NP_02.newPwd);
        confPwd.sendKeys(pwd.CP_003_NP_02.confPwd);

        submitBtn.click();

        $("[id='id_error_newpassword1']").shouldBe(visible);
    }

    @Test
    public void CP_003_NP_05() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_003_NP_05.oldPwd);
        newPwd.sendKeys(pwd.CP_003_NP_05.newPwd);
        confPwd.sendKeys(pwd.CP_003_NP_05.confPwd);

        submitBtn.click();

        $("[id='id_error_newpassword1']").shouldBe(visible);
    }

    @Test
    public void CP_004() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_004.oldPwd);
        newPwd.sendKeys(pwd.CP_004.newPwd);
        confPwd.sendKeys(pwd.CP_004.confPwd);

        submitBtn.click();

        $("[id='id_error_newpassword1']").shouldBe(visible);
    }
    @Test
    public void CP_005() {
        SelenideElement oldPwd = $("[id='id_password']");
        SelenideElement newPwd = $("[id='id_newpassword1']");
        SelenideElement confPwd = $("[id='id_newpassword2']");
        SelenideElement submitBtn = $("[id='id_submitbutton']");

        oldPwd.sendKeys(pwd.CP_005.oldPwd);
        newPwd.sendKeys(pwd.CP_005.newPwd);
        confPwd.sendKeys(pwd.CP_005.confPwd);

        submitBtn.click();

        $("[id='id_error_password']").shouldBe(visible);
    }

}