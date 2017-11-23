package com.chrisrecinos.cucumber.steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * @author - Christopher Recinos
 */
public class RunSearchWithSetAndNumAndInsertAndPlayerSteps extends StepDefinition {

    @And("^User selects \"(.*)\" as their specified set$")
    public void user_selects_specified_set(String set) throws Throwable {
        Select val = new Select(driver.findElement(By.id("set")));
        val.selectByVisibleText(set);
    }

    @And("^User enters \"(.*)\" as their specified card number$")
    public void user_enters_specified_card_number(String cardNum) throws Throwable {
        driver.findElement(By.id("card_num")).sendKeys(cardNum);
    }

    @And("^User enters \"(.*)\" as their specified insert$")
    public void user_enters_specified_insert(String insert) throws Throwable {
        driver.findElement(By.id("insert_type")).sendKeys(insert);
    }

    @And("^User enters \"(.*)\" as their specified player$")
    public void user_enters_specified_player(String player) throws Throwable {
        driver.findElement(By.id("player")).sendKeys(player);
    }
}
