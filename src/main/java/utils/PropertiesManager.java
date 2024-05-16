package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.lang3.StringUtils;

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

    /**
     * Reads all the values from the setup.properties file.
     *
     * @throws IOException the io exception
     */
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

    /**
     * Updates all properties in the setup.properties file.
     *
     * @throws ConfigurationException the configuration exception
     * @throws IOException            Signals that an I/O exception of some sort has occurred
     */
    public static void updateAllProperties() throws ConfigurationException, IOException
    {
        // Create a new PropertiesConfiguration instance
        var config = new PropertiesConfiguration();

        // Create a FileHandler using the properties file
        var fileHandler = new FileHandler(config);
        fileHandler.setFile(new File(propertiesFile));

        // Load the properties file
        fileHandler.load();

        if (StringUtils.isNotBlank(System.getProperty("browser")))
        {
            String property = System.getProperty("browser");
            log.info("Setting browser to {}", property);
            config.setProperty("browser", property);
        }

        // Save the properties file
        fileHandler.save();
        readProperties();
    }
}
