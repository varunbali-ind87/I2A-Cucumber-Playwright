package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.extern.log4j.Log4j2;

import static browsersetup.PlaywrightBase.page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Log4j2
public class SearchPage
{
    public void waitForSearchResults()
    {
        assertThat(page).hasTitle("Insights to Action: Search");
        log.info("Page title: " + page.title());
        assertThat(page.locator("xpath=//div[starts-with(@class, 'search_leftSectionContainer')]")).isVisible();
        var locators = page.locator("xpath=//div[starts-with(@class, 'SearchRecord_insightcontainer')]//../h1/a").all();
        log.info("Search results: " + locators.size());
        locators.forEach(locator ->
        {
            assertThat(locator).isVisible();
            log.info("{}", locator.textContent(new Locator.TextContentOptions().setTimeout(30000)));
        });
    }
}
