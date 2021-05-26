package com.liubing.demoserviceprovider.controller;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class IndexController {


    @RequestMapping(value = {"/"})
    public String index() {
        return "index_" + new Date().getTime();
    }


    @RequestMapping(value = {"/addCookies"})
    public String addCookies(HttpServletResponse response) {


        ServletUtil.addCookie(response, "a", "aaaaa", 500);
        ServletUtil.addCookie(response, "b", "bbbbb");
        return "addCookies_" + new Date().getTime();
    }


    /**
     * getUserInfo
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/getUserInfo"})
    public Object getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getHeaderNames:" + response.getHeaderNames());
        System.out.println("getCookieString:" + getCookieString(request));
        return request.getParameterMap();
    }


    String getCookieString(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cc = cookies[i];
                stringBuilder.append(String.format("getName：%s，getValue：%s，getMaxAge：%s，getPath：%s。\r\n", cc.getName(), cc.getValue(), cc.getMaxAge(), cc.getPath()));
            }
        }
        return stringBuilder.toString();
    }
}
