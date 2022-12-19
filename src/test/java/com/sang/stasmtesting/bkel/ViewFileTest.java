package com.sang.stasmtesting.bkel;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:8080/
public class ViewFileTest {
    public static SelenideElement loginBtn;
    public static SelenideElement myCoursesTab;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;

    // Course elements
    public static SelenideElement menuBtn = $("i[class*='fa-bars']");
    public static SelenideElement siteHomeBtn = $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > nav > ul > li:nth-of-type(2) > a > div > div > span:nth-of-type(2)");
    public static SelenideElement course = $("a[class='aalink']");

    // Files
    public static SelenideElement VF_001_1_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(1) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_001_2_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(2) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_001_3_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(3) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_001_4_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(4) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_001_5_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(5) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_002_1_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(6) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_002_2_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(7) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_002_3_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(8) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_002_4_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(9) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_003_1_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(10) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_004_1_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(11) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");

    public static SelenideElement VF_004_2_file = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(3) > div:nth-of-type(3) > ul > li:nth-of-type(12) > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");
    

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://127.0.0.1:8080/");
        myCoursesTab = $("[data-key='mycourses']");
        loginBtn = $("span[class='login'] a");
        usernameField = $("input[id='username']");
        passwordField = $("input[id='password']");
        submitBtn = $("button[id='loginbtn']");

        logInMoodle();

        // Course navigate
        menuBtn.click();
        siteHomeBtn.click();
        course.click();
    }

    @AfterEach
    public void closeWeb(){
        webdriver().driver().close();
    }

    private static void logInMoodle() {
        loginBtn.click();
        usernameField.sendKeys("student");
        passwordField.sendKeys("Student_1");
        submitBtn.click();
    }

    @Test
    public void VF_001_1() throws InterruptedException {
        VF_001_1_file.click();
    }

    @Test
    public void VF_001_2() throws InterruptedException {
        VF_001_2_file.click();
    }

    @Test
    public void VF_001_3() throws InterruptedException {
        VF_001_3_file.click();
    }

    @Test
    public void VF_001_4() throws InterruptedException {
        VF_001_4_file.click();
    }

    @Test
    public void VF_001_5() throws InterruptedException {
        VF_001_5_file.click();
    }

    @Test
    public void VF_002_1() throws InterruptedException {
        VF_002_1_file.click();
    }

    @Test
    public void VF_002_2() throws InterruptedException {
        VF_002_2_file.click();
    }

    @Test
    public void VF_002_3() throws InterruptedException {
        VF_002_3_file.click();
    }

    @Test
    public void VF_003_1() throws InterruptedException {
        VF_003_1_file.click();
    }

    @Test
    public void VF_004_1() throws InterruptedException {
        VF_004_1_file.click();
    }

    @Test
    public void VF_004_2() throws InterruptedException {
        VF_004_2_file.click();
    }
}