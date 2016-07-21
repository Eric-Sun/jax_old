package com.j13.bar.server.core;

import java.util.HashMap;

public class HDData extends HashMap<String, Object> {

    private HDData() {
    }

    public static HDData getData() {
        return new HDData();
    }

    public HDData add(String key, Object value) {
        put(key, value);
        return this;
    }
}
