package com.j13.bar.server.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceTest {

    @Test
    public void register(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "a");
        map.put("password", "b");
        String s = http.post("http://localhost:8080/user/register", map);
        System.out.println(s);
    }

    @Test
    public void login(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "a");
        map.put("password", "b");
        String s = http.post("http://localhost:8080/user/login", map);
        System.out.println(s);
    }
}
