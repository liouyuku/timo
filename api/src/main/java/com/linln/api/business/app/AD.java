package com.linln.api.business.app;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short


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
            @ApiParam(name = "returnFlag", value = "返回数据类型") @RequestParam("returnFlag") String returnFlag) {
        return new Response().success(null);
    }

    @ApiOperation(value = "app引导界面", response = Response.class)
    @RequestMapping(value = "/app/prologue", method = RequestMethod.GET)
    public @ResponseBody
    Response prologue() {
        return new Response().success(null);
    }


    @ApiOperation(value = "加載APP時候的廣告業", response = Response.class)
    @RequestMapping(value = "/app/homePage", method = RequestMethod.GET)
    public @ResponseBody
    Response getBackgroundPicture() {
        return new Response().success(null);
    }
}
