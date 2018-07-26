package com.example.zjl.myrecyclerview.bean;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class ChildItem {
    private int resId;        //图片（头像）的Id
    private String name;      //子列表项的名字（QQ昵称）
    private String detail;    //子列表项的详细信息（QQ签名）

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
