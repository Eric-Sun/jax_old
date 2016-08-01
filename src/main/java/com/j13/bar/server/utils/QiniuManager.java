package com.j13.bar.server.utils;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

public class QiniuManager {

    private static QiniuManager qiniuManager;

    private static String AccessKey = "1m-fkTm3JZHPQZpXuhBhzC61taVP5POHzA04kpqJ";
    private static String SecretKey = "tf4_7xEiQhCrz8WArvqTsc3z0zAk4gMXIzD-gNmv";
    private static String BUCKETNAME = "er1cthumb";
    private static String FILEPATH = "/";

    private UploadManager uploadManager = new UploadManager();

    private static Auth auth = null;

    private QiniuManager() {

    }

    public static QiniuManager getManager() {
        synchronized (QiniuManager.class) {
            if (qiniuManager == null) {
                init();
                qiniuManager = new QiniuManager();
                return qiniuManager;
            } else {
                return qiniuManager;
            }
        }
    }


    private static void init() {
        auth = Auth.create(AccessKey, SecretKey);
        Config.zone = Zone.zone1();

    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(BUCKETNAME);
    }

    public void upload(String targetFilePath, String fileName) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(targetFilePath, fileName, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }


    //构造私有空间需要生成的下载的链接
    static String URL = "http://er1cthumb/a.png";

    public void download() {
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl(URL, 3600);
        System.out.println(downloadRUL);
    }

    public static void main(String args[]) {
        QiniuManager.getManager().download();
    }

}
