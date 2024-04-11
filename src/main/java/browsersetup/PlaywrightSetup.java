package browsersetup;

import browsercontextsetup.BrowserContextCreator;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ViewportSize;
import io.cucumber.java.*;
import lombok.extern.log4j.Log4j2;
import utils.PropertiesManager;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static browsersetup.PlaywrightBase.page;

@Log4j2
public class PlaywrightSetup
{

    private static Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    @BeforeAll
    public static void beforeAll() throws IOException
    {
        PropertiesManager.readProperties();
        System.setProperty("log4j2.configurationFile", System.getProperty("user.dir") + "src/main/resources/log4j2.xml");
    }

    @Before(order = 0)
    public void setupBrowser(Scenario scenario)
    {
        String browserValue = PropertiesManager.getBrowser();
        scenario.log("Browser selected: " + browserValue);
        playwright = Playwright.create();
        var contextCreator = new BrowserContextCreator();
        context = contextCreator.getBrowserContext(playwright, browserValue);
        page = context.newPage();
        log.info("{} launched.", browserValue);
    }

    @After(order = 1)
    public void embedScreenshot(Scenario scenario)
    {
        if (scenario.isFailed())
        {
            var simpleFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            var date = new Date();
            var screenshotName = simpleFormat.format(date) + ".png";
            byte[] bytes = page.screenshot();
            scenario.attach(bytes, "image/png", screenshotName);
        }
    }

    @After(order = 0)
    public void closeBrowser()
    {
        if (context != null && page != null)
        {
            context.close();
            log.info("Browser closed.");
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
