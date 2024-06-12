package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.TopicPage;

public class TopicsStep
{
    private final TopicPage topicPage = new TopicPage();
    private String contentType;

    @And("filters {string} contents")
    public void filtersContents(String contentType)
    {
        topicPage.filterContentsBasedOnType(contentType);
        this.contentType = contentType;
    }

    @Then("the filtered results should be displayed")
    public void theFilteredResultsShouldBeDisplayed()
    {
        topicPage.verifyContentTypeIsFiltered(contentType);
    }
}
