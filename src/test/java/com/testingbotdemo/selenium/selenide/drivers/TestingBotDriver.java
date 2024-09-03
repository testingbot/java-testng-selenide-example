package com.testingbotdemo.selenium.selenide.drivers;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.WebDriverRunner;

public class TestingBotDriver {
    private RemoteWebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        String username = System.getenv("TESTINGBOT_KEY") == null ? "TESTINGBOT_KEY" : System.getenv("TESTINGBOT_KEY");
        String accesskey = System.getenv("TESTINGBOT_SECRET") == null ? "TESTINGBOT_SECRET" : System.getenv("TESTINGBOT_SECRET");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "WIN10");

        Map<String, Object> testingBotOptions = new HashMap<>();
        testingBotOptions.put("name", "Cucumber Example");
        capabilities.setCapability("tb:options", testingBotOptions);

        try {
            String gridURL = "https://" + username + ":" + accesskey + "@hub.testingbot.com/wd/hub";
            driver = new RemoteWebDriver(new URL(gridURL), capabilities);
            WebDriverRunner.setWebDriver(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid URL provided for TestingBot hub", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}