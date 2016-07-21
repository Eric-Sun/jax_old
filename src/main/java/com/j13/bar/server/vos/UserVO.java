package com.j13.bar.server.vos;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class UserVO {
    long uid;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
