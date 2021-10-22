package com.opengeekv2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class Config implements ConfigSource {

    static final String FILE_PATH = System.getProperty("user.dir").split("target")[0] + "config/local.properties";
    static final String CONFIG_SOURCE_NAME = "external.config";
    
    private Properties loadProperties() {
        Path configFile = Paths.get(FILE_PATH);
        Properties properties = new Properties();
        try {
            InputStream configFileInputStream = Files.newInputStream(configFile);
            properties.load(configFileInputStream);
        }
        catch (IOException exception) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("No external config file found. Using default values.");
        }
        return properties;
    }
    
    public String getName() {
        return CONFIG_SOURCE_NAME;
    }

    public String getValue(String key) {
        Properties properties = loadProperties();
        return properties.getProperty(key);
    }

    @Override
    public Set<String> getPropertyNames() {
        Properties properties = loadProperties();
        String[] result = new String[properties.size()];
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(properties.keySet().toArray(result)));
        return Set.copyOf(arrayList);
    }
    
}
