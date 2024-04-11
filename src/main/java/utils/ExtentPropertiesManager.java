package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Log4j2
public class ExtentPropertiesManager
{
	public static final String EXTENT_DESTINATION_FILEPATH;
	private static final String EXTENT_SOURCE_FILEPATH;
	static
	{
		EXTENT_SOURCE_FILEPATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources"
				+ File.separator + "extent.properties";
		EXTENT_DESTINATION_FILEPATH = System.getProperty("user.dir") + File.separator + "target" + File.separator + "test-classes"
				+ File.separator;
	}

	private ExtentPropertiesManager()
	{
	}

	/**
	 * Updates extent.properties with the OS name alongwith the version on which the automation suite is executing. Finally it copies the file from
	 * the projects source directory & overwrites the file residing in the target folder so that the Extent Cucumber plugin can read the updated file.
	 *
	 * @throws IOException            the io exception
	 * @throws ConfigurationException the configuration exception
	 */
	public static synchronized void updateExtentProperties() throws IOException, ConfigurationException
	{
		var config = new PropertiesConfiguration();
		var layout = config.getLayout();
		File sourceFile = new File(EXTENT_SOURCE_FILEPATH);
		var reader = new FileReader(sourceFile);
		layout.load(config, reader);
		config.setProperty("systeminfo.os", SystemUtils.OS_NAME);
		config.setProperty("systeminfo.version", SystemUtils.OS_VERSION);
		var writer = new FileWriter(sourceFile);
		layout.save(config, writer);
		File destinationFile = new File(EXTENT_DESTINATION_FILEPATH);
		FileUtils.copyFileToDirectory(sourceFile, destinationFile);
		log.info("Extent.properties updated successfully.");
	}
}
