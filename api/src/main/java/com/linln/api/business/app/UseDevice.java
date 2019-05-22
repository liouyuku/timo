package com.linln.api.business.app;


import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.OpenDoorRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用戶操作設備
 */
@RestController
public class UseDevice {

    @ApiOperation(value = "开门", response = Response.class)
    @RequestMapping(value = "/openDoor", method = RequestMethod.POST)
    public @ResponseBody
    Response openDoor(@Validated @RequestBody OpenDoorRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                return new Response().failure(error.getDefaultMessage());
            }
        }
        return new Response().success(null);
    }

    @ApiOperation(value = "进入监控", response = Response.class)
    @RequestMapping(value = "/entryMonitor", method = RequestMethod.GET)
    public @ResponseBody
    Response entryMonitor(
            @ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") int uId,
            @ApiParam(name = "eId", value = "设备id不能为空", required = true) @RequestParam("eId") String eId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "退出监控", response = Response.class)
    @RequestMapping(value = "/outMonitor", method = RequestMethod.PUT)
    public @ResponseBody
    Response outMonitor(
            @ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") int uId,
            @ApiParam(name = "eId", value = "设备id不能为空", required = true) @RequestParam("eId") String eId) {

        return new Response().success();
    }
}
