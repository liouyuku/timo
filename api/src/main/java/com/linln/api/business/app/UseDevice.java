package com.linln.api.business.app;


import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.OpenDoorRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用戶操作設備
 */
@RestController
public class UseDevice extends BaseApi {

    @ApiOperation(value = "开门", response = Response.class)
    @RequestMapping(value = "/openDoor", method = RequestMethod.POST)
    public @ResponseBody
    Response openDoor(@Validated @RequestBody OpenDoorRequest request, BindingResult result) {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }

    @ApiOperation(value = "进入监控", response = Response.class)
    @RequestMapping(value = "/entryMonitor", method = RequestMethod.GET)
    public @ResponseBody
    Response entryMonitor(
            @ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "deviceId", value = "设备id不能为空", required = true) @RequestParam("eId") String deviceId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "退出监控", response = Response.class)
    @RequestMapping(value = "/outMonitor", method = RequestMethod.PUT)
    public @ResponseBody
    Response outMonitor(
            @ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "deviceId", value = "设备id不能为空", required = true) @RequestParam("deviceId") String deviceId) {

        return new Response().success();
    }
}
