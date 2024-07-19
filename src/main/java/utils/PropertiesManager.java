package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

@Log4j2
public class PropertiesManager
{
    private static final Path PROPERTIES_FILE = Path.of(String.valueOf(SystemUtils.getUserDir()), "src", "test", "setup.properties");
    private static final Properties properties = new Properties();
    private static final String BROWSER = "browser";

    private PropertiesManager()
    {
    }

    /**
     * Reads all the values from the setup.properties file.
     *
     * @throws IOException the io exception
     */
    public static synchronized void readProperties() throws IOException
    {
        try (var reader = new FileReader(PROPERTIES_FILE.toFile()))
        {
            properties.load(reader);
            log.info("{} read successfully.", PROPERTIES_FILE.toString());
        }
    }

    public static synchronized String getBrowser()
    {
        String browser = System.getProperty(BROWSER);
        if (StringUtils.isNotBlank(browser))
            return browser;
        else
            return properties.getProperty(BROWSER);
    }
}
