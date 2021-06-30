package com.liubing.actuator.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义端点
 * 请求地址：http://localhost:8291/actuator/customEndPoint
 */
@Endpoint(id = "customEndPoint")
@Component
public class CustomEndPoint {

    /**
     * 读取以后，接口返回的信息。
     * @return
     */
    @ReadOperation
    public Map<String, Object> getInfo() {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("自定义信息1", "custom endpoint1 ");
        dataMap.put("自定义信息2", "custom endpoint2 ");
        dataMap.put("自定义信息3", "custom endpoint3 ");
        return dataMap;
    }
}
