package com.sang.stasmtesting.bkel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.webdriver.Title;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendQuizTest {
    public static SelenideElement loginBtn;
    public static SelenideElement myCoursesTab;
    public static SelenideElement myCourses;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;
    public static SelenideElement searchCourseField;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1600x900";
        // Configuration.headless = true;
        // SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://e-learning.hcmut.edu.vn/");
        myCoursesTab = $("[data-key='mycourses']");
        loginBtn = $("[class='d-flex align-items-stretch usermenu-container']");
        usernameField = $("[id='username']");
        passwordField = $("[id='password']");
        submitBtn = $("[class='btn-submit']");
        myCourses = $("[data-course-id='67607']");
        searchCourseField = $("[id='searchinput']");
        logInBkel();
    }

    // @AfterEach
    // public void closeWeb() {
    // webdriver().driver().close();
    // }

    private static void logInBkel() {
        loginBtn.click();
        SelenideElement loginAsStudent = $("[class='btn login-identityprovider-btn btn-block']");
        loginAsStudent.click();

        usernameField.sendKeys("");
        passwordField.sendKeys("");
        submitBtn.click();
    }

    private void fillSearchInput(String keyword) {
        myCoursesTab.click();
        searchCourseField.sendKeys(keyword);
        searchCourseField.pressEnter();
        sleep(2000);
    }

    private void enterMyClosedCourseTab() {
        SelenideElement courses = myCoursesTab;
        courses.click();
        assertEquals(webdriver().object().getTitle(), "Khoá học của tôi");
        open("https://e-learning.hcmut.edu.vn/course/view.php?id=67607");
    }

    @Test
    public void enterQuiz() throws InterruptedException {
        enterMyClosedCourseTab();
        assertEquals(webdriver().object().getTitle(), "Khóa học: SOFTWARE TESTING (CO3015)_BÙI HOÀI THẮNG (CLC_HK221)");
        open("https://e-learning.hcmut.edu.vn/mod/quiz/view.php?id=12815");

        SelenideElement text = $("[class='description-inner']");
        assertEquals(text.getText(), "Opened: Chủ nhật, 4 Tháng chín 2022, 12:00 PM\n" +
                "Closed: Thứ sáu, 16 Tháng chín 2022, 9:00 PM");
    }

}
