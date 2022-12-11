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

public class SearchCourseTest {
    public static SelenideElement loginBtn;
    public static SelenideElement myCoursesTab;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;
    public static SelenideElement searchCourseField;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1600x900";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://e-learning.hcmut.edu.vn/");
        myCoursesTab = $("[data-key='mycourses']");
        loginBtn = $("[class='d-flex align-items-stretch usermenu-container']");
        usernameField = $("[id='username']");
        passwordField = $("[id='password']");
        submitBtn = $("[class='btn-submit']");
        searchCourseField = $("[id='searchinput']");
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

    private void fillSearchInput(String keyword){
        myCoursesTab.click();
        searchCourseField.sendKeys(keyword);
        searchCourseField.pressEnter();
        sleep(2000);
    }

    @Test
    public void searchCourseWithUnderscore_success() throws InterruptedException {
        fillSearchInput("_");
        SelenideElement noCourseText = $("[class='text-muted mt-3']");
        noCourseText.shouldNotBe(Condition.visible);
    }

    @Test
    public void searchCourseWithSpecialCharacter_noCourseFound() throws InterruptedException {
        fillSearchInput("@#");
        SelenideElement noCourseText = $("[class='text-muted mt-3']");
        noCourseText.shouldBe(Condition.visible);
        assertEquals("không có khóa học", noCourseText.getText());
    }

    @Test
    public void searchCourseWithSingleCharacter_success() throws InterruptedException {
        fillSearchInput("a");
        SelenideElement noCourseText = $("[class='text-muted mt-3']");
        noCourseText.shouldNotBe(Condition.visible);
    }

    @Test
    public void searchCourseNormalCase_success() {
        fillSearchInput("software testing");
        SelenideElement categoryCourseTitle = $("[class='category-course']");
        assertEquals("HỌC KỲ I NĂM HỌC 2022-2023 (SEMESTER 1 - ACADEMIC YEAR 2022-2023)", categoryCourseTitle.getText());
        SelenideElement firstCourse = $("[class='aalink coursename']");
        assertEquals("Course name\n" +
                "SOFTWARE TESTING (CO3015)_BÙI HOÀI THẮNG (CLC_HK221)", firstCourse.getText());
    }

    @Test
    public void searchCourseWithLongStringLessThan256_noCourseFound() throws InterruptedException {
        fillSearchInput("abcdefhhijklmnopqrstuvwxyzabcdefhhijklmnopqrstuvwxyzabcdefhhijklmnopqrstuvwxyz");
        SelenideElement noCourseText = $("[class='text-muted mt-3']");
        noCourseText.shouldBe(Condition.visible);
        assertEquals("không có khóa học", noCourseText.getText());
    }

    @Test
    public void searchCourseWithLongStringMoreThan256_noCourseFound() throws InterruptedException {
        fillSearchInput("qDmzZhUSatVfxywizeiAncQrjuPosaXHRYTlcToMwtSSTSjVyvAHcNVzbutbutxqTgSSxmPVGfEBappxDozSimzukUWAyKEdFAxjuJNdwnAQXiNjbgpMudNThVnzDPEAJgLjXooklqikSQBQhncGnTGuMsTdrmYoAnyrpIbsyLAaXEGqtfenZcWtYbGXQoqvlCKYjaLxbmWUmVBJlxGGPWZVgsHLeTjxjkPPyUZdADJeayJiuLDlbuAGumLYKKYxNjvMFcBnMokZMDLKnujyMFxynOhBqFpNONzxMqRqaDCu");
        SelenideElement noCourseText = $("[class='text-muted mt-3']");
        noCourseText.shouldBe(Condition.visible);
        assertEquals("không có khóa học", noCourseText.getText());
    }

    @Test
    public void searchCourseWithEmptyString_printAllCourses() throws InterruptedException {
        fillSearchInput("");
        SelenideElement noCourseText = $("[class='text-muted mt-3']");
        noCourseText.shouldNotBe(Condition.visible);
    }
}
