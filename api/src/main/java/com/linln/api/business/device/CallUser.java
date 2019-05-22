package com.linln.api.business.device;

import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.CallUserRequest;
import com.linln.api.business.frombean.request.DeviceHanUpRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class CallUser {

    /**
     * 呼叫用户
     */
    @ApiOperation(value = "呼叫用户", response = Response.class)
    @RequestMapping(value = "/callUser", method = RequestMethod.POST)
    public @ResponseBody
    Response callUser(@RequestBody CallUserRequest request) {
        return new Response().success(null);
    }


    @ApiOperation(value = "下位机挂断", response = Response.class)
    @RequestMapping(value = "/deviceHanUp", method = RequestMethod.POST)
    public @ResponseBody
    Response deviceHanUp(@RequestBody DeviceHanUpRequest request) {
        return new Response().success(null);
    }
}
