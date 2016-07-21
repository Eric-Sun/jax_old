package com.j13.bar.server.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午8:24
 * To change this template use File | Settings | File Templates.
 */
public class MessageServiceTest {

    @Test
    public void send(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", "5");
        map.put("content", "aaaaa");
        String s = http.post("http://localhost:8080/message/send", map);
        System.out.println(s);
    }


    @Test
    public void fetchAll(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        String s = http.post("http://localhost:8080/message/fetchAll", map);
        System.out.println(s);
    }
}
