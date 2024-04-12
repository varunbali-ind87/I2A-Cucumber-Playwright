package browsersetup;

import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;

import java.nio.file.Path;

public class PlaywrightBase
{
    public static final Path downloadPath = Path.of(System.getProperty("java.io.tmpdir"));
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();

    public static Scenario Scenario()
    {
        return scenarioThreadLocal.get();
    }

    public static void setScenario(Scenario scenario)
    {
        scenarioThreadLocal.set(scenario);
    }

    public static void removeScenario()
    {
        scenarioThreadLocal.remove();
    }

    public static Page Page()
    {
        return pageThreadLocal.get();
    }

    public static void setPage(Page page)
    {
        pageThreadLocal.set(page);
    }

    public static void removePage()
    {
        pageThreadLocal.remove();
    }

    private PlaywrightBase()
    {
    }
}
