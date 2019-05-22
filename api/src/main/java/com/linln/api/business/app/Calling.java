package com.linln.api.business.app;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/***
 * APP呼叫相关方法
 */
@RestController
public class Calling {

    @ApiOperation(value = "app端挂断", response = Response.class)
    @RequestMapping(value = "/hanUp", method = RequestMethod.POST)
    public @ResponseBody
    Response hanUp(
            @ApiParam(name = "uId", value = "用户id不能为空", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "eId", value = "设备id", required = true) @RequestParam("deviceId") String deviceId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "響鈴超時通知，即app收到下位機的呼叫后，在指定時間内未點擊接通調用此接口", response = Response.class)
    @RequestMapping(value = "/callTimeout", method = RequestMethod.PUT)
    public @ResponseBody
    Response callBackHandUp(
            @ApiParam(name = "deviceId", value = "设备id", required = true) @RequestParam("eid") String deviceId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "APP收到呼叫，響鈴", response = Response.class)
    @RequestMapping(value = "/bell", method = RequestMethod.PUT)
    public @ResponseBody
    Response bell(
            @ApiParam(name = "callId", value = "呼叫ID，從push消息中獲取", required = true) @RequestParam("callId") String callId,
            @ApiParam(name = "deviceId", value = "設備ID，從push消息中獲取", required = true) @RequestParam("callId") String deviceId) {
        return new Response().success(null);
    }


    @ApiOperation(value = "APP接通呼叫", response = Response.class)
    @RequestMapping(value = "/connect", method = RequestMethod.PUT)
    public @ResponseBody
    Response connect(
            @ApiParam(name = "callId", value = "呼叫ID，從push消息中獲取", required = true) @RequestParam("callId") String callId,
            @ApiParam(name = "deviceId", value = "設備ID，從push消息中獲取", required = true) @RequestParam("callId") String deviceId) {
        return new Response().success(null);
    }

}
