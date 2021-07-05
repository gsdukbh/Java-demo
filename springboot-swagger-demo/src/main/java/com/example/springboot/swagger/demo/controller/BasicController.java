package com.example.springboot.swagger.demo.controller;

import com.example.springboot.swagger.demo.common.Result;
import com.example.springboot.swagger.demo.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * @author leejiawei
 */
@RestController
@Api(tags = "BasicController",produces = "s")
public class BasicController {
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation("获取所有品牌列表")
    @GetMapping("/ge")
    public String index(){

        return  "hello ";
    }

    @ApiOperation(value = "获取用户列表", notes = "查询用户列表")
    @GetMapping("/user/list")
    @ApiResponses({
            @ApiResponse(code = 100, message = "异常数据")
    })
    public Result<Map<Integer, User>> getUserList() {
        return Result.successWithData(users);
    }
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<User> postUser(@ApiIgnore User user) {
        users.put(user.getId(), user);
        return Result.successWithData(user);
    }
}
