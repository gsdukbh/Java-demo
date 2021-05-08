package com.example.springboottestdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringbootTestDemoApplicationSeleniumTest {

    private static ChromeDriver chrome;

    @BeforeEach
    public void openBrowser() {
        chrome = new ChromeDriver();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterEach
    public void closeBrowser() {
        chrome.quit();
    }


    @Test
    @WithUserDetails()
    void init() {
        String url = "http://localhost:8080/";
        chrome.get(url);

        // 登陆
        chrome.findElementByName("username").sendKeys("user");
        chrome.findElementByName("password").sendKeys("passwd");
        chrome.findElementByXPath("/html/body/div/form/button").submit();
    }

    @Test
    void get() {
    }

    @Test
    void post() {
    }
}