package com.chrisrecinos.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author - Christopher Recinos
 */

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features",
                 monochrome = true, glue= "com.chrisrecinos.cucumber.steps")
public class CucumberTest {
}
