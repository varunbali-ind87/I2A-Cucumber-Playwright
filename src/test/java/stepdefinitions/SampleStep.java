package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import pageobjects.HomePage;
import pageobjects.SearchPage;

@Log4j2
public class SampleStep
{
    private final HomePage homePage = new HomePage();
    private final SearchPage searchPage = new SearchPage();

    @Given("the user is on {string}")
    public void theUserIsOn(String url)
    {
        homePage.goToHomePage(url);
    }

    @When("the user enters {string} in the searchbox & submits")
    public void theUserEntersInTheSearchboxSubmits(String keyword)
    {
        homePage.search(keyword);
    }

    @Then("he should see some results")
    public void heShouldSeeSomeResults()
    {
        searchPage.waitForSearchResults();
    }

    @When("the user clicks About Us")
    public void theUserClicksAboutUs()
    {
        homePage.clickFooter("ABOUT US");
    }

    @Then("he should be taken to the about us page")
    public void heShouldBeTakenToTheAboutUsPage()
    {
        homePage.waitForAboutUsPage();
    }

    @When("the user clicks Sign In link")
    public void theUserClicksSignInLink()
    {
        homePage.signIn();
    }

    @Then("he should be taken to the sign in page")
    public void heShouldBeTakenToTheSignInPage()
    {
        homePage.verifySignInTitle();
    }

    @When("the user visits {string} topic")
    public void theUserVisitsTopic(String topic)
    {
        homePage.clickTopic(topic);
    }
}
