package utils;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertiesManager
{
    private static final String propertiesFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "setup.properties";
    private static final Properties properties = new Properties();

    private PropertiesManager()
    {
    }

    public static synchronized void readProperties() throws IOException
    {
        try (var reader = new FileReader(propertiesFile))
        {
            properties.load(reader);
            log.info("{} read successfully.", propertiesFile);
        }
    }

    public static synchronized String getBrowser()
    {
        return properties.getProperty("browser");
    }
}
