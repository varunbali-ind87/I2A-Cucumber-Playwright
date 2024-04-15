package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import utils.ExtentPropertiesManager;

import java.io.IOException;

@CucumberOptions(features = {"src/test/resources/features"}, glue = {"browsersetup", "stepdefinitions"},
        monochrome = true, plugin = {
		"pretty",
		"json:target/cucumber.json",
		"junit:target/cucumber.xml",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/failedscenarios.txt"},
        tags = "@Test")

@Log4j2
public class TestRunner extends AbstractTestNGCucumberTests
{

    @AfterSuite
    public void afterSuite()
    {
        log.info("Wrapping up the suite..");
    }

    @BeforeSuite
    public void beforeSuite() throws ConfigurationException, IOException
    {
        /*
         * Valid values of jobtype = eclipse, jenkins. If below code is anything but
         * jenkins then the below code will not be executed. This is helpful if you want
         * to use your IDE If value = jenkins then it will be executed for Jenkins
         * environment
         */
        System.setProperty("log4j2.configurationFile", System.getProperty("user.dir") + "src/main/resources/log4j2.xml");
        log.info("Starting the suite...");
        ExtentPropertiesManager.updateExtentProperties();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}
