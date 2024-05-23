package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.extern.log4j.Log4j2;

import static browsersetup.PlaywrightBase.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Log4j2
public class SearchPage
{
    public void waitForSearchResults()
    {
        assertThat(Page()).hasTitle("Insights to Action: Search");
        log.info("Page title: " + Page().title());
        assertThat(Page().locator("xpath=//div[starts-with(@class, 'search_leftSectionContainer')]")).isVisible();
        var locators = Page().locator("xpath=//div[starts-with(@class, 'SearchRecord_insightcontainer')]//../h1/a").elementHandles();
        log.info("Search results: " + locators.size());
        locators.forEach(locator -> log.info("{}", locator.textContent()));
    }
}
