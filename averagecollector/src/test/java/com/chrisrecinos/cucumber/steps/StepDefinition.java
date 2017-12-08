package com.chrisrecinos.cucumber.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author - Christopher Recinos
 */

@WebAppConfiguration
@SpringBootTest
public class StepDefinition {

    public static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }
}
