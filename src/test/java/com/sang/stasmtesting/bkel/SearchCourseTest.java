package com.sang.stasmtesting.bkel;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchCourseTest {
    private SelenideElement loginBtn;
    private SelenideElement myCoursesTab;

    @BeforeAll
    public static void setUpAll(){
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp(){
        open("https://e-learning.hcmut.edu.vn/");
        myCoursesTab = $("[data-key='mycourses']");
        loginBtn = $("[class='d-flex align-items-stretch usermenu-container']");
    }

    @Test
    public void testSearchCourse_success(){
        loginBtn.click();
        SelenideElement loginAsStudent = $("[class='btn login-identityprovider-btn btn-block']");
        loginAsStudent.click();
        SelenideElement usernameField = $("[id='username']");
        SelenideElement passwordField = $("[id='password']");
        SelenideElement submitBtn = $("[class='btn-submit']");
        usernameField.sendKeys(Account.username);
        passwordField.sendKeys(Account.password);
        submitBtn.click();

        myCoursesTab.click();
        SelenideElement searchCourseField = $("[id='searchinput']");
        searchCourseField.sendKeys("software testing");
        searchCourseField.pressEnter();
    }
}
