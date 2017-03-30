package com.j13.bar.server.poppy;

import com.google.common.collect.Maps;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public class RequestData {

    private Map<String,Object> data = Maps.newLinkedHashMap();

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
