package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.FileUtils;

public class PropertiesReader {
    private static final Properties properties = new Properties();
    private static final String PROP_ROOT = "src/main/resources/";
    private static final AtomicBoolean isLoaded = new AtomicBoolean(false);

    // Private constructor to prevent instantiation
    private PropertiesReader() {}

    // Static block to load properties when the class is first accessed
    static {
        loadProperties();
    }

    // Synchronized method to load properties only once
    public static synchronized void loadProperties() {
        if (isLoaded.get()) return; // Prevent redundant loading

        try {
            Collection<File> propertiesFiles = FileUtils.listFiles(new File(PROP_ROOT), new String[]{"properties"}, true);
            for (File file : propertiesFiles) {
                System.out.println("🔹 Loading properties from: " + file.getAbsolutePath());

                // Use try-with-resources to ensure the file is properly closed
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                }
            }

            // Merge with system properties (Only if the system property is not already set)
            properties.forEach((key, value) -> {
                if (System.getProperty((String) key) == null) {
                    System.setProperty((String) key, (String) value);
                }
            });

            isLoaded.set(true);  // Mark as loaded
        } catch (IOException e) {
            System.err.println("❌ Error loading properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get a property value with a default option
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Get a property value (null if not found)
    public static String getProperty(String key) {
        return getProperty(key, null);
    }
}
