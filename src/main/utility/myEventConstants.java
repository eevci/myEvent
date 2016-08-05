package main.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by enver on 8/1/16.
 */
public class myEventConstants {

    private static Map<String, String> constantMap;

    public enum UserRole {
        SUPERADMIN,
        ADMIN,
        MEMBER,
        ANONYMOUS
    }

    public myEventConstants() throws IOException {

        String result = "";
        InputStream inputStream = null;
        try {
                Properties prop = new Properties();
                String propFileName = "myEvent.properties";

                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");

                }

            for (final String name: prop.stringPropertyNames())
                constantMap.put(name, prop.getProperty(name));



        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }

    }


    public String getConstValue(String key) {
        return constantMap.get(key);
    }


}
