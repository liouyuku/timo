package com.linln.api.business;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系統相應接口
 */
public class System {

    @ApiOperation(value = "上傳圖片", response = Response.class)
    @RequestMapping(value = "/imgUp", method = RequestMethod.POST)
    public @ResponseBody
    Response imgUp() {
        return null;
    }

    @ApiOperation(value = "上傳日志", response = Response.class)
    @RequestMapping(value = "/logUp", method = RequestMethod.POST)
    public @ResponseBody
    Response logUp() {
        return null;
    }

    @ApiOperation(value = "上傳文件", response = Response.class)
    @RequestMapping(value = "/fileUp", method = RequestMethod.POST)
    public @ResponseBody
    Response fileUp() {
        return null;
    }

    @ApiOperation(value = "获取服务器时间", response = Response.class)
    @RequestMapping(value = "/currentTime", method = RequestMethod.GET)
    public @ResponseBody
    Response currentTime() {
        return null;
    }

}
