package browsersetup;

import browsercontextsetup.BrowserContextCreator;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.*;
import lombok.extern.log4j.Log4j2;
import utils.PropertiesManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static browsersetup.PlaywrightBase.*;


@Log4j2
public class PlaywrightSetup
{

    private static Playwright playwright;
    private BrowserContext context;

    @BeforeAll
    public static void beforeAll() throws IOException
    {
        PropertiesManager.readProperties();
        System.setProperty("log4j2.configurationFile", System.getProperty("user.dir") + "src/main/resources/log4j2.xml");
        playwright = Playwright.create();
    }

    @Before(order = 0)
    public void setupBrowser(Scenario scenario) throws IOException {
        String browserValue = PropertiesManager.getBrowser();
        scenario.log("Browser selected: " + browserValue);
        var contextCreator = new BrowserContextCreator();
        context = contextCreator.getBrowserContext(playwright, browserValue);
        var page = context.newPage();
        PlaywrightBase.setPage(page);
        log.info("{} launched.", browserValue);
        PlaywrightBase.setScenario(scenario);
    }

    @After(order = 1)
    public void embedScreenshot(Scenario scenario)
    {
        if (scenario.isFailed())
        {
            var simpleFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            var date = new Date();
            var screenshotName = simpleFormat.format(date) + ".png";
            byte[] bytes = Page().screenshot();
            scenario.attach(bytes, "image/png", screenshotName);
        }
    }

    @After(order = 0)
    public void closeBrowser()
    {
        if (context != null && Page() != null)
        {
            context.close();
            log.info("Browser closed.");
            removeScenario();
            log.info("Cleared scenario thread.");
            removePage();
            log.info("Cleared page thread.");
        }
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
}
