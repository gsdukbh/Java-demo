package com.example.springboottestdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringbootTestDemoApplicationSeleniumTest {

    private static ChromeDriver chrome;

    @BeforeEach
    public  void openBrowser() {
        chrome = new ChromeDriver();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public  void closeBrowser() {
        chrome.quit();
    }


    @Test
    void init() {
        String url= "http://localhost:8080/";
        chrome.get(url);
        assertEquals("hello world",chrome.findElementByLinkText("hello world").getText());
    }

    @Test
    void get() {
    }

    @Test
    void post() {
    }
}