package com.j13.bar.server.core;

import java.util.HashMap;
import java.util.Map;

public class RequestData extends HashMap<String, Object> {

    private int uid;
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getString(String key) {
        return new String(get(key) + "");
    }

    public Integer getInteger(String key) {
        return new Integer(get(key) + "");
    }

    public Long getLong(String key) {
        return new Long(get(key) + "");
    }
}
