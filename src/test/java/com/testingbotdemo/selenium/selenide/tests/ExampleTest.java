package com.testingbotdemo.selenium.selenide.drivers;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTest extends TestingBotDriver {

    @Test
    public void test() throws Exception {
        open("https://testingbot.com");
        $("body").shouldHave(text("TestingBot"));
    }
}
