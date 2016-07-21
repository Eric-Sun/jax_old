package com.j13.bar.server.core;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class HDResponseWrapper {

    public static String success(long uid, HDData data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", StatusCodeConstants.SUCCESS);
        map.put("uid", uid);
        map.put("data", data);
        return JSON.toJSONString(map);
    }

    public static String failure(int statusCode, long uid, HDData data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", statusCode);
        map.put("uid", uid);
        map.put("data", data);
        return JSON.toJSONString(map);
    }

    public static String failure(long uid, HDData data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", StatusCodeConstants.FATLE_ERROR);
        map.put("uid", uid);
        map.put("data", data);
        return JSON.toJSONString(map);
    }

    static class StatusCodeConstants {
        public static int SUCCESS = 0;
        public static int FATLE_ERROR = -1;
    }


}
