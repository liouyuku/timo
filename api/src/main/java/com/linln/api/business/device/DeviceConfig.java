package com.linln.api.business.device;


import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.DeviceConfigRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 设备配置信息
 */
@RestController
public class DeviceConfig extends BaseApi {

    /**
     * 查询下位机配置
     * @param deviceId
     * @return
     */
    @ApiOperation(value = "查询下位机的配置信息", response = Response.class)
    @RequestMapping(value = "/device/configuration/get", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@ApiParam(name = "deviceId", value = "设备id", required = true) @RequestParam("deviceId") String deviceId)
    {
        return new Response().success(null);
    }

    /**
     * 添加下位机配置
     * @param request
     * @return
     */
    @ApiOperation(value = "添加下位机的配置信息", response = Response.class)
    @RequestMapping(value = "/device/configuration/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@Validated @RequestBody DeviceConfigRequest request, BindingResult result)
    {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }

    /**
     * 修改下位机配置
     * @param request
     * @return
     */
    @ApiOperation(value = "修改下位机的配置信息", response = Response.class)
    @RequestMapping(value = "/device/configuration/update", method = RequestMethod.PUT)
    public @ResponseBody
    Response put(@Validated @RequestBody DeviceConfigRequest request, BindingResult result)
    {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }


}
