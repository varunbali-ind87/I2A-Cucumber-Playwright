package browsersetup;

import browsercontextsetup.BrowserContextCreator;
import browsercontextsetup.BrowserCreator;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.*;
import lombok.extern.log4j.Log4j2;
import utils.DateUtils;
import utils.PropertiesManager;

import java.io.IOException;
import java.nio.file.Path;

import static browsersetup.PlaywrightBase.*;


@Log4j2
public class PlaywrightSetup
{

    private static Playwright playwright;
    private BrowserContext context;
    private Browser browser;

    @BeforeAll
    public static void beforeAll() throws IOException
    {
        PropertiesManager.readProperties();
        System.setProperty("log4j2.configurationFile", String.valueOf(Path.of(System.getProperty("user.dir"), "src", "main", "resources", "log4j2.xml")));
        playwright = Playwright.create();
    }

    @AfterAll
    public static void afterAll()
    {
        if (playwright != null)
        {
            playwright.close();
            log.info("Playwright closed.");
        }
    }

    @Before(order = 0)
    public void setupBrowser(Scenario scenario) throws IOException
    {
        String browserValue = PropertiesManager.getBrowser();
        scenario.log("Browser selected: " + browserValue);

        var browserCreator = new BrowserCreator();
        browser = browserCreator.getBrowser(playwright, browserValue);
        scenario.log("Browser object initialized.");

        var contextCreator = new BrowserContextCreator();
        context = contextCreator.getBrowserContext(browser);
        scenario.log("Browser context object initialized.");

        var page = context.newPage();
        PlaywrightBase.setPage(page);
        browserValue = browserValue.substring(0, 1).toUpperCase() + browserValue.substring(1).toLowerCase();
        scenario.log(String.format("%s launched.", browserValue));
        PlaywrightBase.setScenario(scenario);
    }

    @After(order = 1)
    public void embedScreenshot(Scenario scenario)
    {
        if (scenario.isFailed())
        {
            var dateUtils = new DateUtils();
            var screenshotName = dateUtils.getScreenShotFromCurrentTimestamp();
            byte[] bytes = Page().screenshot();
            scenario.attach(bytes, "image/png", screenshotName);
        }
    }

    @After(order = 0)
    public void closeBrowser()
    {
        if (context != null)
        {
            context.close();
            log.info("Context closed.");
        }
        if (browser.isConnected())
        {
            browser.close();
            log.info("Browser closed.");
        }
        if (!Page().isClosed())
        {
            removePage();
            log.info("Cleared page thread.");
        }
        if (Scenario() != null)
        {
            removeScenario();
            log.info("Cleared scenario thread.");
        }
    }
}
