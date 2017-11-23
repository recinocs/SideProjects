package com.chrisrecinos.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * @author - Christopher Recinos
 */
public class RunSearchWithNoParametersSteps extends StepDefinition {

    @Given("^User is on the AverageCollector landing page$")
    public void user_is_on_the_averagecollector_landing_page() throws Throwable {
        driver.manage().window().fullscreen();
        driver.get("http://localhost:8080/averagecollector");
    }

    @When("^User navigates to the AverageCollector home page$")
    public void user_navigates_to_the_averagecollector_home_page() throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id("homepage-nav")).click();
    }

    @And("^User clicks on the 'Run Search' button$")
    public void user_clicks_on_the_run_search_button() throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id("run-search")).click();
    }

    @Then("^Total results displayed should equal \"(.*)\"$")
    public void list_of_all_cards_is_displayed(String num) throws Throwable {
        String test_num = driver.findElement(By.id("total_res")).getText();
        if(!test_num.equals(num))
            throw new AssertionError("Results don't match");
        Thread.sleep(6000); //Sleep in order to scroll down and confirm result size. Increase or decrease as needed
    }
}
