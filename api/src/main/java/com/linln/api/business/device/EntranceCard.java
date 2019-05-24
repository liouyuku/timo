package com.linln.api.business.device;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short

@RestController
public class EntranceCard {

    @ApiOperation(value = "验证卡号", response = Response.class)
    @RequestMapping(value = "/device/card/verification", method = RequestMethod.GET)
    public @ResponseBody
    Response verification(@ApiParam(name = "deviceId", value = "设备id", required = true) @RequestParam("deviceId") String deviceId,
                 @ApiParam(name = "uId", value = "用户ID", required = true) @RequestParam("uId") String uId)
    {
        return new Response().success(null);
    }
}
