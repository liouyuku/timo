package com.linln.api.business;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short

/**
 * 回调
 */
@RestController
public class Feedback {

    @ApiOperation(value = "设备收到push后的回调,用于判断此次消息是否发送成功", response = Response.class)
    @RequestMapping(value = "/feedback", method = RequestMethod.PUT)
    public @ResponseBody
    Response onMessage(
            @ApiParam(name = "msgId", value = "消息的id", required = true) @RequestParam("msgId") String msgId,
            @ApiParam(name = "deviceId", value = "设备id,不限于下位机 ，APP端也需要调用 。此ID为唯一有效标识", required = true) @RequestParam("deviceId") String deviceId) {
        return new Response().success(null);
    }
}
