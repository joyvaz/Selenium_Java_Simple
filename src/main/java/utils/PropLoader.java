package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class PropLoader
{
    private static final String DEFAULT_CONFIG_FILE = "config.properties";

    /**
    * Retrieves a value from the default file.
    * @param key This is the key that we want to retrieve
    */
    public static String get(String key)
    {
        return get(DEFAULT_CONFIG_FILE, key);
    }

    /**
    * Retrieves a value from a SPECIFIC file using ThreadLocal cache.
    * @param fileName  This is the name of the properties file
    * @param key This is the key that we want to retrieve
    * @return The value of the key on the file properties
    */
    public static String get(String fileName, String key)
    {
        String value;

        value = System.getProperty(key);
        if (value == null)
            value = System.getenv(key);

        if (value == null)
        {
            Properties props = getPropertiesFile(fileName);
            value = props.getProperty(key);
        }

        if (value == null)
            throw new RuntimeException(String.format(
                    "Key '%s' was not found in System Properties, Environment Variables, or file '%s'.",
                    key, fileName));

        return value;
    }

    /**
    * Loads the file into the CURRENT THREAD's cache.
    * @param fileName This is the name of the properties file
    * @return the values loaded
    */
    private static Properties getPropertiesFile(String fileName)
    {
        Map<String, Properties> currentThreadCache = CommonVariables.propMap.get();
        if (currentThreadCache.containsKey(fileName))
            return currentThreadCache.get(fileName);

        Properties props = new Properties();
        try (InputStream input = PropLoader.class.getClassLoader().getResourceAsStream(fileName))
        {
            if (input == null)
                throw new RuntimeException("Could not find resource file: " + fileName);
            props.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            currentThreadCache.put(fileName, props);

        }
        catch (IOException ex)
        {
            throw new RuntimeException("Failed to load properties file: " + fileName, ex);
        }

        return props;
    }

    /**
    * Use this in an @AfterMethod or @AfterClass to free memory.
    */
    public static void unload()
    {
        CommonVariables.propMap.remove();
    }
}
