package pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static browsersetup.PlaywrightBase.Page;
import static browsersetup.PlaywrightBase.Scenario;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TopicPage
{

    public static final String CONTENT_TYPE_MENU = "Content Type";

    public void filterContentsBasedOnType(String contentType)
    {
        Page().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Filter")).click();
        Scenario().log("Clicked filter button");
        assertThat(Page().locator("xpath=//div[starts-with(@class, 'Filter_filterFormDisplayed')]")).isVisible();
        Page().getByText(CONTENT_TYPE_MENU).click();
        Scenario().log("Clicked content type menu");
        Page().getByLabel(contentType).check();
        Scenario().log("Checked " + contentType + " checkbox");
        Page().getByText(CONTENT_TYPE_MENU).click();
        Scenario().log("Collapsed content type menu");
        Page().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Filters")).click();
        Scenario().log("Clicked apply filters button");
        assertThat(Page().locator("xpath=//*[text() = 'Loading ... ']")).isVisible();
        assertThat(Page().locator("xpath=//*[text() = 'Loading ... ']")).isHidden();
    }

    public void verifyContentTypeIsFiltered(String contentType)
    {
        Page().locator("xpath=//div[starts-with(@class, 'CardTypeC_readingCountRow')]/span/span").all()
                .forEach(locator -> assertThat(locator).containsText(contentType.toUpperCase()));
        Scenario().log("Verified that " + contentType + " is filtered");
    }
}
