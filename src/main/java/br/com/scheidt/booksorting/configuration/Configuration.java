package br.com.scheidt.booksorting.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Configuration class used to load the sorting properties.
 * 
 * @author Marcelo Scheidt
 */
public class Configuration {

    private static final String CONFIGURATION_PATH = "configuration.xml";

    private static Properties properties;

    /**
     * Load and return the sorting properties. <br>
     * 
     * @return the properties
     */
    public static Properties getProperties() {
        if(properties == null) {
            load();
        }
        
        return properties;
    }

    /**
     * Load the configuration.xml file with the sorting properties.
     */
    private static void load() {
        try {
            InputStream input = new FileInputStream(CONFIGURATION_PATH);
            properties = new LinkedProperties();
            properties.loadFromXML(input);
            
        } catch (InvalidPropertiesFormatException e) {
            Logger.getGlobal().severe("Invalid property format in file: " + e.getMessage());
            
        } catch (IOException e) {
            Logger.getGlobal().severe("Couldn't load file: " + e.getMessage());
        }
    }
}
