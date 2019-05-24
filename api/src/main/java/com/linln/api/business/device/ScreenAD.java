package com.linln.api.business.device;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class ScreenAD {

    @ApiOperation(value = "设备主屏信息", response = Response.class)
    @RequestMapping(value = "/device/ad", method = RequestMethod.GET)
    public @ResponseBody
    Response getScreenImg(@ApiParam(name="mac",value="设备的mac") @RequestParam("mac") String mac) {
        return new Response().success(null);
    }
}
