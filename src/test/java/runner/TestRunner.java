package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import javax.naming.ConfigurationException;
import java.io.IOException;

@CucumberOptions(features = { "src/test/resources/features" }, glue = { "browsersetup", "stepdefinitions" },
		monochrome = true, plugin = { "pretty", "json:target/cucumber.json", "junit:target/cucumber.xml"},
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
	}

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
}
