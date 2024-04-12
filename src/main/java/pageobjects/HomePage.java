package pageobjects;

import lombok.extern.log4j.Log4j2;

import static browsersetup.PlaywrightBase.Page;
import static browsersetup.PlaywrightBase.Scenario;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@Log4j2
public class HomePage
{

    private static final String ONETRUST_REJECT_ALL_HANDLER = "#onetrust-reject-all-handler";

    public void search(final String keyword)
    {
        Page().getByAltText("Search").click();
        Scenario().log("Clicked search.");
        Page().locator("xpath=//*[@id='__next']/div//../input").fill(keyword);
        Scenario().log("Entered " + keyword);
        Page().keyboard().press("Enter");
        Scenario().log("Pressed enter key");
    }

    public void goToHomePage(String url)
    {
        Page().navigate(url);
        assertThat(Page()).hasTitle("Insights to Action: Home");
        Scenario().log("User is now on home page.");
        declineOnetrust();
    }

    public void declineOnetrust()
    {
        assertThat(Page().locator(ONETRUST_REJECT_ALL_HANDLER)).isVisible();
        Page().locator(ONETRUST_REJECT_ALL_HANDLER).click();
        assertThat(Page().locator(ONETRUST_REJECT_ALL_HANDLER)).isHidden();
        Scenario().log("Declined onetrust.");
    }

    public void clickFooter(String footer)
    {
        Page().getByTitle(footer).click();
        Scenario().log(footer + " clicked.");
    }

    public void waitForAboutUsPage()
    {
        assertThat(Page().getByText("Overview")).isVisible();
        Scenario().log("Overview is visible.");
        assertThat(Page().getByText("Our Team")).isVisible();
        Scenario().log("Our Team is visible.");
        assertThat(Page().getByText("Our Services")).isVisible();
        Scenario().log("Our Services is visible.");
        assertThat(Page().getByText("Key Features")).isVisible();
        Scenario().log("Key Features is visible.");
    }
}
