package browsercontextsetup;

import browsersetup.PlaywrightBase;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.log4j.Log4j2;

import java.awt.*;

@Log4j2
public class BrowserContextCreator
{
    public BrowserContext getBrowserContext(Playwright playwright, String browserValue)
    {
        Browser browser = null;
        var options = new BrowserType.LaunchOptions().setHeadless(false).setDownloadsPath(PlaywrightBase.downloadPath);
        if (browserValue.equalsIgnoreCase("chrome"))
            browser = playwright.chromium().launch(options);
        else if (browserValue.equalsIgnoreCase("firefox"))
            browser = playwright.firefox().launch(options);
        else
            throw new UnsupportedOperationException("Invalid browser specified!");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        log.info("Width = {}, Height = {}", width, height);

        var contextOptions = new Browser.NewContextOptions().setViewportSize(width, height).setAcceptDownloads(true);
        return browser.newContext(contextOptions);
    }
}
