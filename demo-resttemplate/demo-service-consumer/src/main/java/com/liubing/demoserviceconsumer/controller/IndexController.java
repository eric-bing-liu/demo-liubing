package com.liubing.demoserviceconsumer.controller;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@RestController
public class IndexController {


    @RequestMapping(value = {"/"})
    public String index(HttpServletRequest request) {
        return "index_" + new Date().getTime();
    }


    @RequestMapping(value = {"/addCookies"})
    public String addCookies(HttpServletRequest request, HttpServletResponse response) {

        ServletUtil.addCookie(response, "a", "aaaaa", 500);
        ServletUtil.addCookie(response, "b", "bbbbb");
        return "addCookies_" + new Date().getTime();
    }


    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过RestTemplate方式 请求restful接口 并携带cookie
     * 方法1：HttpHeaders + List<String> cookies   坏消息：cookie只能设置key value，provider端只是临时可以获取到，么有path等信息
     * 也就是说，response以后，这个传入的cookie么得了
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = {"/getUserInfo"})
    public Object getUserInfo(HttpServletRequest httpServletRequest) {

        HttpHeaders headers = new HttpHeaders();

        List<String> cookies = new ArrayList<String>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add("XXL_JOB_LOGIN_IDENTITY=6333303830376536353837616465323835626137616465396638383162336437;Path=/; HttpOnly");       //在 header 中存入cookies
        cookies.add("c=ccccc");

        headers.put(HttpHeaders.COOKIE, cookies);        //将cookie存入头部
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();      //请求体给予内容
        map.add("appName", "xxl-job-executor-cdmtc-record");        //应用名称
        map.add("title", "测试执行器");      //执行器名称
        map.add("order", "1");          //排序方式
        map.add("addressType", "1");        //注册方式 ：  0为
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Object> response = restTemplate.postForEntity("http://localhost:8000/getUserInfo", request, Object.class);
        System.out.println(response.getBody());
        return response;
    }


    @RequestMapping(value = {"/getUserInfo2"})
    public Object getUserInfo2(HttpServletRequest httpServletRequest) throws Exception {

        String baseUrl = "http://localhost:8000";
        WebClient webClient = WebClient.create(baseUrl);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "u123");
        map.add("password", "p123");

        Mono<ClientResponse> mono = webClient.post().uri("/getUserInfo").cookie("a1", "a1-value").cookie("a2", "a2-value").syncBody(map).exchange();
        ClientResponse response = mono.block();
        if (response.statusCode() == HttpStatus.OK) {
//            Mono<Result> resultMono = response.bodyToMono(Result.class);
            Mono<String> resultMono = response.bodyToMono(String.class);
            resultMono.subscribe(result -> {

                log.info("result:{}", result);

//                if (result.isSuccess()) {
//
//
//                    ResponseCookie sidCookie = response.cookies().getFirst("sid");
//                    Flux<User> userFlux = webClient.get().uri("users").cookie(sidCookie.getName(), sidCookie.getValue()).retrieve().bodyToFlux(User.class);
//                    userFlux.subscribe(System.out::println);
//
//
//                }
            });
        }

        return response.statusCode();

    }
}
