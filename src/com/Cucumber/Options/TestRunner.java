package com.Cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/com/features/placeValidations.feature",
plugin={"pretty", "html:cucumberReport/cucumber-html-report","json:cucumberReport/cucumber-report.json"},
glue={"cucumber/steps"},tags= ("@DeletePlace"))

public class TestRunner {

}
