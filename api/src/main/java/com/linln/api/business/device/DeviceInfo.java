package com.linln.api.business.device;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.AddDeviceRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short

import javax.validation.constraints.NotBlank;

/**
 * 设备信息
 */
@RestController
@Validated
public class DeviceInfo extends BaseApi {

    @ApiOperation(value = "增加下位机", response = Response.class)
    @RequestMapping(value = "/device/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@Validated @RequestBody AddDeviceRequest request, BindingResult result) {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }

    /**
     * 获取绑定信息
     */
    @ApiOperation(value = "获取绑定信息", response = Response.class)
    @RequestMapping(value = "/device/get", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@ApiParam(name = "deviceId", value = "设备Id", required = true) @NotBlank(message = "设备ID不能为空") @RequestParam("deviceId") String deviceId) {
        return new Response().success(null);
    }


}
