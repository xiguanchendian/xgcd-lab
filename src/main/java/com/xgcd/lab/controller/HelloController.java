package com.xgcd.lab.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Web启动测试1", "Web启动测试2"})
@RestController
@RequestMapping("/")
public class HelloController {

    @ApiOperation(value = "sayHello")
    @RequestMapping("/sayHello")
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }
}
