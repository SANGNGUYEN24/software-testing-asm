package com.sang.stasmtesting.bkel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateProfileTest {
    public static SelenideElement loginBtn;
    public static SelenideElement usernameField;
    public static SelenideElement passwordField;
    public static SelenideElement submitBtn;

    public static SelenideElement profileBtn;
    public static SelenideElement profileBar;
    public static SelenideElement editProfileTitle;
    public static SelenideElement addFileBtn;
    public static SelenideElement chooseFileBtn;
    public static SelenideElement uploadChosenFileBtn;
    public static SelenideElement updateProfileBtn;
    public static SelenideElement statusBar;

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
        profileBtn = $("[id='user-menu-toggle']");
        profileBar = $("[href='https://e-learning.hcmut.edu.vn/user/profile.php']");
        editProfileTitle = $("[href='https://e-learning.hcmut.edu.vn/user/edit.php?id=85904&returnto=profile']");
        addFileBtn = $("[class='icon fa fa-file-o fa-fw ']");
        statusBar = $("[class='alert alert-success alert-block fade in  alert-dismissible']");
    }

    @AfterEach
    public void closeWeb() {
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

    private void gotoEditPage() {
        profileBtn.click();
        profileBar.click();
        editProfileTitle.click();
    }

    private void uploadProfilePicture() {
        SelenideElement chooseFileBtn = $("[id='yui_3_17_2_1_1671455394248_1874']");
        chooseFileBtn.sendKeys("/assets/avatar.jpg");

        SelenideElement uploadChosenFileBtn = $("[class='fp-upload-btn btn-primary btn']");
        uploadChosenFileBtn.click();
    }

    private void clickUpdateProfileBtn() {
        SelenideElement updateProfileBtn = $("[id='id_submitbutton']");
        updateProfileBtn.click();

        assertEquals("Đã lưu các thay đổi\n" +
                "×\n" +
                "Dismiss this notification", statusBar.getText());
    }

    // CP-009
    @Test
    public void updateAvatarWithoutDeleteOld_success() {
        gotoEditPage();
        addFileBtn.click();
        uploadProfilePicture();
        clickUpdateProfileBtn();
    }

    // CP-010
    @Test
    public void deleteTheOldPicture_success() {
        gotoEditPage();

        SelenideElement deleteCheckbox = $("[for='id_deletepicture']");
        deleteCheckbox.click();

        clickUpdateProfileBtn();
    }

    // CP-011
    @Test
    public void doNothing_noError() {
        gotoEditPage();
        clickUpdateProfileBtn();
    }

    // CP-012
    @Test
    public void updateProfileAndDescription_success() {
        gotoEditPage();
        uploadProfilePicture();
        addDescription();
        clickUpdateProfileBtn();
    }

    //CP-014
    @Test
    public void uploadTwoImagesandDescription() {
        gotoEditPage();

        SelenideElement chooseFileBtn = $("[id='yui_3_17_2_1_1671455394248_1874']");
        chooseFileBtn.sendKeys("/assets/avatar.jpg");

        SelenideElement uploadChosenFileBtn = $("[class='uploadChosenFileBtn']");
        uploadChosenFileBtn.click();

        SelenideElement chooseFileBtn1 = $("[id='yui_3_17_2_1_1671455394248_1874']");
        chooseFileBtn.sendKeys("/assets/avatar.jpg");

        SelenideElement uploadChosenFileBtn1 = $("[class='uploadChosenFileBtn']");
        uploadChosenFileBtn.click();

        SelenideElement description = $("[id='id_description_editoreditable']");
        description.sendKeys("Toi la sinh vien Bach Khoa");

        clickUpdateProfileBtn();
    }

    // User does nothing and click the update profile button.
    @Test
    public void doNothingandUpdateProfile() {
        gotoEditPage();

        clickUpdateProfileBtn();
    }
    //CP-015
    @Test
    public void onlyViewProfilePage() {
        gotoEditPage();
    private void addDescription() {
        SelenideElement description = $("[id='id_description_editoreditable']");
        description.sendKeys("Toi la sinh vien Bach Khoa");
    }

    //CP-013
    @Test
    public void reUploadProfilePictureAndAddDesc_success() {
        gotoEditPage();
        uploadProfilePicture();
        uploadProfilePicture();
        addDescription();
        clickUpdateProfileBtn();
    }

    // CP-013
    @Test
    public void reUploadProfilePictureAndAddDesc_dontClickUpdateBtn_noError() {
        gotoEditPage();
        uploadProfilePicture();
        uploadProfilePicture();
        addDescription();
    }

    // CP-014
    @Test
    public void dontSaveEditAndLogout_noError(){
        gotoEditPage();
        SelenideElement userBtn = $("[href='https://e-learning.hcmut.edu.vn/pluginfile.php/18247/user/icon/boost/f2?rev=1906157']");
        userBtn.click();
        SelenideElement signOutBtn = $("[id='yui_3_17_2_1_1671461953113_1306']");
        signOutBtn.click();
    }
}
