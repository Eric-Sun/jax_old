package com.h13;


import com.j13.bar.server.utils.QiniuManager;
import org.junit.Test;

import java.io.IOException;

public class QiniuManagerTest {


    @Test
    public void test() throws IOException {
        QiniuManager manager = QiniuManager.getManager();
        manager.upload("/Users/sunbo/Desktop/a.png","a.png");
    }



}
