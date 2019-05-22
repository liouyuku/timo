package com.linln.api.business.app;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
 * APP廣告
 */

@RestController
public class AD {

    @ApiOperation(value = "查询app广告", response = Response.class)
    @RequestMapping(value = "/app/AD", method = RequestMethod.GET)
    public @ResponseBody
    Response appAdvertisement(
            @ApiParam(name = "type", value = "类型", required = true) @RequestParam("type") String type,
            @ApiParam(name = "retrunFalg", value = "返回数据类型") @RequestParam("retrunFalg") String retrunFalg) {
        return new Response().success(null);
    }


    @ApiOperation(value = "移动端获取图片", response = Response.class)
    @RequestMapping(value = "/backgroundPicture", method = RequestMethod.GET)
    public @ResponseBody
    Response getBackgroundPicture() {
        return new Response().success(null);
    }
}
