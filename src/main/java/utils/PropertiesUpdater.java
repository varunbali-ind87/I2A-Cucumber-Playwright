package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

@Log4j2
public class PropertiesUpdater
{
    private static final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "setup.properties";

    public static void updateAllProperties() throws ConfigurationException, ConfigurationException, IOException
    {
        // Create a new PropertiesConfiguration instance
        var config = new PropertiesConfiguration();

        // Create a FileHandler using the properties file
        var fileHandler = new FileHandler(config);
        fileHandler.setFile(new File(PROPERTIES_FILE_PATH));

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
        PropertiesManager.readProperties();
    }
}
