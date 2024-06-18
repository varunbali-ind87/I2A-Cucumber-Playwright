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
    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "setup.properties";
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
        try (var reader = new FileReader(PROPERTIES_FILE))
        {
            properties.load(reader);
            log.info("{} read successfully.", PROPERTIES_FILE);
        }
    }

    public static synchronized String getBrowser()
    {
        return properties.getProperty(BROWSER);
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
        fileHandler.setFile(new File(PROPERTIES_FILE));

        // Load the properties file
        fileHandler.load();

        if (StringUtils.isNotBlank(System.getProperty(BROWSER)))
        {
            String property = System.getProperty(BROWSER);
            log.info("Setting browser to {}", property);
            config.setProperty(BROWSER, property);
        }

        // Save the properties file
        fileHandler.save();
        readProperties();
    }
}
