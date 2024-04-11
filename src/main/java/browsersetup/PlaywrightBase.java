package browsersetup;

import com.microsoft.playwright.Page;

import java.nio.file.Path;

public class PlaywrightBase
{
    public static final Path downloadPath = Path.of(System.getProperty("java.io.tmpdir"));
    public static Page page;

    private PlaywrightBase()
    {
    }
}
