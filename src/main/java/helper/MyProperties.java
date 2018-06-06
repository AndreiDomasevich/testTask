package helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {

    private static final Properties PROPERTIES;
    static {
        PROPERTIES = new Properties();
        try {

            InputStream projectStream = MyProperties.class.getResourceAsStream("/properties.properties");
            PROPERTIES.load(projectStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMyProperty(String key) {
        String myProperty = PROPERTIES.getProperty(key);
        return myProperty;
    }

}
