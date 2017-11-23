package com.chrisrecinos.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author - Christopher Recinos
 */
public class RunSearchWithNoParametersSteps extends StepDefinition {

    public static WebDriver driver;

    @Given("^User is on the AverageCollector landing page$")
    public void user_is_on_the_averagecollector_landing_page() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://localhost:8080/averagecollector");
    }

    @When("^User navigates to the AverageCollector home page$")
    public void user_navigates_to_the_averagecollector_home_page() throws Throwable {
        driver.findElement(By.id("homepage-nav")).click();
    }

    @And("^User clicks on the 'Run Search' button$")
    public void user_clicks_on_the_run_search_button() throws Throwable {
        driver.findElement(By.id("run-search")).click();
    }

    @Then("^Total results displayed should equal \"(.*)\"$")
    public void list_of_all_cards_is_displayed(String num) throws Throwable {
        String test_num = driver.findElement(By.id("total_res")).getText();
        if(!test_num.equals(num))
            throw new AssertionError("Results don't match");
    }
}
