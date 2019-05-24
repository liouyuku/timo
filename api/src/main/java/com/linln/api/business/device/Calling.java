package com.linln.api.business.device;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.CallUserRequest;
import com.linln.api.business.frombean.request.DeviceHanUpRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short


@RestController(value = "device_calling")
public class Calling extends BaseApi {

    /**
     * 呼叫用户
     */
    @ApiOperation(value = "呼叫用户", response = Response.class)
    @RequestMapping(value = "device/callUser", method = RequestMethod.POST)
    public @ResponseBody
    Response callUser(@Validated @RequestBody CallUserRequest request,BindingResult result) {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }


    @ApiOperation(value = "下位机挂断", response = Response.class)
    @RequestMapping(value = "/device/hanUp", method = RequestMethod.POST)
    public @ResponseBody
    Response deviceHanUp(@Validated @RequestBody DeviceHanUpRequest request, BindingResult result) {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }
}
