package com.sang.stasmtesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.lang.model.element.Element;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpTest {
    PizzaPage pizzaPage = new PizzaPage();
    private SelenideElement userNameInput;
    private SelenideElement firstNameInput;
    private SelenideElement lastNameInput;
    private SelenideElement emailInput;
    private SelenideElement phoneInput;
    private SelenideElement passInput;
    private SelenideElement cpassInput;
    private SelenideElement submitBtn;
    private SelenideElement signUpFalseWarning;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost/OnlinePizzaDelivery");
        userNameInput = $("input[name='username']");
        firstNameInput = $("input[name='firstName']");
        lastNameInput = $("input[name='lastName']");
        emailInput = $("input[name='email']");
        phoneInput = $("input[name='phone']");
        passInput = $("input[name='password']");
        cpassInput = $("input[name='cpassword']");
        submitBtn = $("button[id='signUpBtn']");
        signUpFalseWarning = $("strong[id='signUpFalseWarning']");
    }

    @Test
    public void signUp() {
        pizzaPage.signUpButton.click();

        userNameInput.sendKeys("sangnd");
        firstNameInput.sendKeys("Sang");
        lastNameInput.sendKeys("Nguyen Dinh");
        emailInput.sendKeys("nguyendinhsang102222@gmail.com");
        phoneInput.sendKeys("0352610986");
        passInput.sendKeys("lkjasdflasfd");
        cpassInput.sendKeys("lkjasdflasfd");

        submitBtn.click();
        assertEquals("Warning! Username Already Exists", signUpFalseWarning.getText());
    }
}
