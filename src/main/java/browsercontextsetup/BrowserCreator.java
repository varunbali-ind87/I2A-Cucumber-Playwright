package browsercontextsetup;

import browsersetup.PlaywrightBase;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.List;

public class BrowserCreator
{
    /**
     * Returns a Playwright browser: chrome, firefox based on the specified browser value.
     *
     * @param  playwright    the Playwright instance to create the browser from
     * @param  browserValue  the value specifying the browser type (e.g., "chrome" or "firefox")
     * @return               the Playwright Browser instance based on the specified browser value
     */
    public Browser getBrowser(Playwright playwright, String browserValue)
    {
        var launchOptions = new BrowserType.LaunchOptions().setHeadless(false).setDownloadsPath(PlaywrightBase.downloadPath);
        return switch (browserValue.toLowerCase())
        {
            case "chrome" -> playwright.chromium().launch(launchOptions.setArgs(List.of("--start-maximized")));
            case "firefox" -> playwright.firefox().launch(launchOptions);
            default -> throw new UnsupportedOperationException("Invalid browser specified.");
        };
    }
}
