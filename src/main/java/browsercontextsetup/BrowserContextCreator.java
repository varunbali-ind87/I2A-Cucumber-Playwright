package browsercontextsetup;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class BrowserContextCreator
{
    private static final Path videoDirectory = Paths.get(String.valueOf(SystemUtils.getUserDir()), "videos");

    /**
     * Retrieves a new browser context by ensuring a clean video directory exists.
     *
     * @param  browser the browser object to create the context from
     * @return         the new browser context
     * @throws IOException if an I/O error occurs
     */
    public BrowserContext getBrowserContext(final Browser browser) throws IOException
    {
        // Create video directory if it does not exist
        if (!Files.exists(videoDirectory))
            Files.createDirectories(videoDirectory);

        // Delete if its not a directory & create a directory instead
        else if (Files.exists(videoDirectory) && !Files.isDirectory(videoDirectory))
        {
            Files.delete(videoDirectory);
            Files.createDirectories(videoDirectory);
        }

        // Clean everything if the directory exists & is not empty
        else
            PathUtils.cleanDirectory(videoDirectory);

        // Return NewContextOptions by enabling downloads, video recording and setting the viewport size to null
        var newContextOptions = new Browser.NewContextOptions()
                .setAcceptDownloads(true)
                .setRecordVideoDir(videoDirectory)
                .setViewportSize(null);

        // Return new browser context
        return browser.newContext(newContextOptions);
    }
}
