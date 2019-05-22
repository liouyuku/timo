package com.linln.api.business.device;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.AddDeviceRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * 设备信息
 */
@RestController
@Validated
public class DeviceInfo extends BaseApi {

    @ApiOperation(value = "增加下位机", response = Response.class)
    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody AddDeviceRequest bean, HttpServletRequest req) {
        return new Response().success(null);

    }

    /**
     * 获取绑定信息
     */
    @ApiOperation(value = "获取绑定信息", response = Response.class)
    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@ApiParam(name = "mac", value = "设备的mac地址", required = true) @NotBlank(message = "设备mac地址不能为空") @RequestParam("mac") String mac) {
        return new Response().success(null);
    }
}
