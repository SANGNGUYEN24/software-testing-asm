package com.sang.stasmtesting.bkel;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// page_url = http://127.0.0.1:8080/
public class SubmitTest {
    public static SelenideElement loginBtn;
    public static SelenideElement myCoursesTab;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;

    // Course elements
    public static SelenideElement menuBtn = $("i[class*='fa-bars']");
    public static SelenideElement siteHomeBtn = $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > nav > ul > li:nth-of-type(2) > a > div > div > span:nth-of-type(2)");
    public static SelenideElement course = $("a[class='aalink']");
    public static SelenideElement submitFile = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div > div > ul > li:nth-of-type(2) > div:nth-of-type(3) > ul > li > div > div > div:nth-of-type(2) > div:nth-of-type(1) > a > span");;
    public static SelenideElement addSubmissionBtn = $("button[id^='single']");

    public static SelenideElement filePickerBtn = $("a[title='Add...']");
    public static SelenideElement chooseFileBtn = $("input[id$='676']");
    public static SelenideElement uploadThisFileBtn = $("button[class='fp-upload-btn btn-primary btn']");

    public static SelenideElement saveChangesBtn = $("input[id$='submitbutton']");

    public static SelenideElement uploadBtn = $("html > body > div:nth-of-type(3) > div:nth-of-type(3) > div > div > section > div:nth-of-type(1) > div:nth-of-type(3) > form > div:nth-of-type(2) > div:nth-of-type(2) > fieldset > div:nth-of-type(1) > div:nth-of-type(4) > div:nth-of-type(1) > div:nth-of-type(2) > div > div > i");


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
        
        // Submit file navigate
        menuBtn.click();
        siteHomeBtn.click();
        course.click();
        submitFile.click();
        
        // Pre choose file
        addSubmissionBtn.click();
        filePickerBtn.click();
        chooseFileBtn.click();
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
    public void SF_001_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_001/a.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_001_3() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_001/ab.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_001_5() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_001/file#.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_001_6() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_001/this.file.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_002_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.jpg");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_002_2() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.png");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_002_3() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.jpeg");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_002_4() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.gif");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_002_5() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.webp");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_003_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.doc");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_003_2() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_003_3() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.xls");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_003_4() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.ppt");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_004_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.mp3");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_005_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.mov");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_005_2() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_002_003_004_005/file_name.mp4");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_006_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_006/SF_006_1.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_006_2() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_006/SF_006_2.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_007_1() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_1.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_2.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }

    @Test
    public void SF_007_2() throws InterruptedException {
        chooseFileBtn.sendKeys("src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_1.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_2.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_3.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_4.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_5.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_6.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_7.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_8.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_9.pdf \n " +
                "src/test/java/com/sang/stasmtesting/bkel/test_files/SF_007/SF_007_10.pdf");
        uploadThisFileBtn.click();
        saveChangesBtn.click();
    }
}