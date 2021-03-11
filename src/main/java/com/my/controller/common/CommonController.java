package com.my.controller.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "公共接口")
@RestController
public class CommonController {

    @ApiOperation(value = "检查启动是否正常")
    @GetMapping("/")
    public String index() {
        return "resource-downloader server is started ... ";
    }

    @ApiOperation(value = "心跳检查")
    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "200";
    }

}
