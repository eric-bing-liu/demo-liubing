package com.liubing.swaggerui.demoswaggerui.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tags;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@Api(tags="用户管理")
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * 更新用户通过ID
     * 注解@RequestParam接收的参数是来自HTTP请求体或请求url的QueryString中。
     * RequestParam可以接受简单类型的属性，也可以接受对象类型。
     * --@RequestParam用来处理 Content-Type 为 application/x-www-form-urlencoded 编码的内容，Content-Type默认为该属性。
     * --@RequestParam也可用于其它类型的请求，例如：POST、DELETE等请求。
     *
     *
     * 注解@RequestBody接收的参数是来自requestBody中，即请求体。一般用于处理非 Content-Type: application/x-www-form-urlencoded编码格式的数据，比如：application/json、application/xml等类型的数据。
     * @param params  {"a":"aaaaaaaaaaaaaaaaaaaa", "b":"bbbbbbbbbb"}   {"a":"aaaaaaaaaaaaaaaaaaaa"}
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "书写备注信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "姓名", required = true, dataType = "String"),})
    @PostMapping("/updateById")
    public String updateById(@RequestBody JSONObject params) {
        System.out.println(params.toString());
        return "updateById user : " + params.toString();
    }


    @ApiOperation(value = "更新用户信息2", notes = "书写备注信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "姓名", required = true, dataType = "String"),})
    @PostMapping("/updateById2")
    public String updateById2(String a, String b) {
        return "updateById user : " + a.toString();
    }

    @GetMapping("/selectById/{id}")
    public String selectById(@PathVariable String id) {
        return "select user : " + id;
    }

    @DeleteMapping("/users/{id}")
    public String deleteById(@PathVariable String id) {
        return "delete user : " + id;
    }

    @ApiIgnore
    @DeleteMapping("/apiIgnore/{id}")
    public String apiIgnore(@PathVariable String id) {
        return "apiIgnore user : " + id;
    }

}
