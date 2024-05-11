package browsercontextsetup;

import browsersetup.PlaywrightBase;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class BrowserContextCreator
{
    public BrowserContext getBrowserContext(Playwright playwright, String browserValue) throws IOException {
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

        var videoDirectory = new File(System.getProperty("user.dir") + File.separator + "videos");
        if (!videoDirectory.exists())
            FileUtils.forceMkdir(videoDirectory);

        var contextOptions = new Browser.NewContextOptions().setViewportSize(width, height).setAcceptDownloads(true).setRecordVideoDir(Path.of(videoDirectory.getAbsolutePath()));
        return browser.newContext(contextOptions);
    }
}
