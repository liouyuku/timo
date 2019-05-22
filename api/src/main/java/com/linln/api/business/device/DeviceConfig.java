package com.linln.api.business.device;


import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.CallUserRequest;
import com.linln.api.business.frombean.request.DeviceConfigRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 设备配置信息
 */
@RestController
public class DeviceConfig extends BaseApi {

    @ApiOperation(value = "查询下位机的配置信息", response = Response.class)
    @RequestMapping(value = "/deviceConfig", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@ApiParam(name = "deviceId", value = "设备id", required = true) @RequestParam("deviceId") String deviceId)
    {
        return new Response().success(null);
    }

    @ApiOperation(value = "添加下位机的配置信息", response = Response.class)
    @RequestMapping(value = "/deviceConfig", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody DeviceConfigRequest request)
    {
        return new Response().success(null);
    }

    @ApiOperation(value = "修改下位机的配置信息", response = Response.class)
    @RequestMapping(value = "/deviceConfig", method = RequestMethod.PUT)
    public @ResponseBody
    Response put(@RequestBody DeviceConfigRequest request)
    {
        return new Response().success(null);
    }


}
