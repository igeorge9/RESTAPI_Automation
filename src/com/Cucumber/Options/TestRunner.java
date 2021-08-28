package com.Cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/com/features/placeValidations.feature",
plugin ={"json:target/jsonReports/cucumber-report.json","html:target/cucumber-html-report"},
glue={"cucumber/steps"},tags= ("@DeletePlace"))

public class TestRunner {

}
