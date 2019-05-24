package com.linln.api.business.app;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.util.agora.AccessTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/***
 * APP呼叫相关方法
 */
@RestController(value = "app_calling")
public class Calling extends BaseApi {


    @ApiOperation(value = "获取声网的token，默认5分钟挂断", response = Response.class)
    @RequestMapping(value = "/agoraToken", method = RequestMethod.POST)
    public @ResponseBody
    Response agoraToken(
            @ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "deviceId", value = "房间通道名称，默认是设备id", required = true) @RequestParam("deviceId") String deviceId) {
        AccessTokenUtil au = new AccessTokenUtil();
        String result = au.genToken(uId, deviceId);
        return new Response().success(result);
    }

    @ApiOperation(value = "app端挂断", response = Response.class)
    @RequestMapping(value = "/app/hanUp", method = RequestMethod.POST)
    public @ResponseBody
    Response hanUp(
            @ApiParam(name = "uId", value = "用户id不能为空", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "eId", value = "设备id", required = true) @RequestParam("deviceId") String deviceId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "響鈴超時通知，即app收到下位機的呼叫后，在指定時間内未點擊接通調用此接口", response = Response.class)
    @RequestMapping(value = "/app/callTimeout", method = RequestMethod.PUT)
    public @ResponseBody
    Response callBackHandUp(
            @ApiParam(name = "deviceId", value = "设备id", required = true) @RequestParam("eid") String deviceId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "APP收到呼叫，響鈴", response = Response.class)
    @RequestMapping(value = "/app/bell", method = RequestMethod.PUT)
    public @ResponseBody
    Response bell(
            @ApiParam(name = "callId", value = "呼叫ID，從push消息中獲取", required = true) @RequestParam("callId") String callId,
            @ApiParam(name = "deviceId", value = "設備ID，從push消息中獲取", required = true) @RequestParam("callId") String deviceId) {
        return new Response().success(null);
    }


    @ApiOperation(value = "APP接通呼叫", response = Response.class)
    @RequestMapping(value = "/app/connect", method = RequestMethod.PUT)
    public @ResponseBody
    Response connect(
            @ApiParam(name = "callId", value = "呼叫ID，從push消息中獲取", required = true) @RequestParam("callId") String callId,
            @ApiParam(name = "deviceId", value = "設備ID，從push消息中獲取", required = true) @RequestParam("callId") String deviceId) {
        return new Response().success(null);
    }

}
