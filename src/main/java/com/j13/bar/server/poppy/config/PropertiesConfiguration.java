package com.j13.bar.server.poppy.config;


import com.j13.bar.server.poppy.exceptions.RequestFatalException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesConfiguration {
    private static PropertiesConfiguration config = null;
    private static Map<String, String> map = new HashMap();

    private PropertiesConfiguration() {
    }

    public static PropertiesConfiguration getInstance() {
        if(config == null) {
            config = new PropertiesConfiguration();
        }

        return config;
    }

    public void addResource(String configPath) throws RequestFatalException {
        InputStream is = PropertiesConfiguration.class.getResourceAsStream(configPath);
        Properties properties = new Properties();

        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RequestFatalException("configPath=" + configPath, e);
        }

        Set keys = properties.stringPropertyNames();
        Iterator iter = keys.iterator();

        while(iter.hasNext()) {
            String key = (String)iter.next();
            map.put(key, properties.getProperty(key));
        }

    }

    public String getStringValue(String key) {
        return (String)map.get(key);
    }

    public int getIntValue(String key) {
        return (new Integer((String)map.get(key) + "")).intValue();
    }
}
