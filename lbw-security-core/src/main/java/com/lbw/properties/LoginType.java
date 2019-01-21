package com.lbw.properties;

/**
 * Author by lbw , Date on 2018/10/12.
 * 前端登陆方式，支持模板语言的跳转方式和接口返回Json
 */
public enum LoginType {

    /**
     * 重定向方式，正确认证返回页面，失败认证跳转Spring boot默认错误页面
     */
    REDIRECT,


    /**
     * Json方式，均返回Json数据
     */
    JSON
}
