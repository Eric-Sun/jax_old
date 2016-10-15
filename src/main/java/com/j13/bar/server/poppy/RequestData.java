package com.j13.bar.server.poppy;

import com.google.common.collect.Maps;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public class RequestData {

    private FileItem fileItem;

    private Map<String,String> data = Maps.newLinkedHashMap();

    public FileItem getFileItem() {
        return fileItem;
    }

    public void setFileItem(FileItem fileItem) {
        this.fileItem = fileItem;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
