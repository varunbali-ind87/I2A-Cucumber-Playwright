package pageobjects;

import browsersetup.PlaywrightBase;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import lombok.extern.log4j.Log4j2;

import static browsersetup.PlaywrightBase.page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Log4j2
public class HomePage
{

    private static final Locator ONETRUST_REJECT = page.locator("#onetrust-reject-all-handler");

    public void search(final String keyword)
    {
        page.getByAltText("Search").click();
        log.info("Clicked search.");
        page.locator("xpath=//*[@id='__next']/div//../input").fill(keyword);
        log.info("Entered {}", keyword);
        page.keyboard().press("Enter");
        log.info("Pressed enter key");
    }

    public void goToHomePage(String url)
    {
        page.navigate(url);
        assertThat(page).hasTitle("Insights to Action: Home");
        log.info("User is now on home page.");
        declineOnetrust();
    }

    public void declineOnetrust()
    {
        assertThat(ONETRUST_REJECT).isVisible();
        ONETRUST_REJECT.click();
        assertThat(ONETRUST_REJECT).isHidden();
        log.info("Declined onetrust.");
    }
}
