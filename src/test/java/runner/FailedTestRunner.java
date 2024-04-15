package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "@target/failedscenarios.txt",
        glue = {"browsersetup", "stepdefinitions"},
        plugin = {
                "pretty",
                "json:target/failed-cucumber.json",
                "junit:target/failed-cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },
        tags = "@Test")
public class FailedTestRunner extends AbstractTestNGCucumberTests
{
}
