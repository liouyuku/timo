package com.linln.api.business.device;


import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/***
 * 设备通告c
 */
@RestController
public class Announcement {
    @ApiOperation(value = "设备获取通告", response = Response.class)
    @RequestMapping(value = "/device/announcement", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@ApiParam(name="deviceId",value="设备id",required=true)
                             @RequestParam("deviceId") String deviceId) {
        return new Response().success(null);
    }
}
