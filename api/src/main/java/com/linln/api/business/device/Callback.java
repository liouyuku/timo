package com.linln.api.business.device;

import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 下位机回调
 */
@RestController
public class Callback {

    @ApiOperation(value = "设备收到push后的回调,用于判断此次消息是否发送成功", response = Response.class)
    @RequestMapping(value = "/callback", method = RequestMethod.PUT)
    public @ResponseBody
    Response onMessage(
            @ApiParam(name = "msgId", value = "消息的id", required = true) @RequestParam("msgId") String msgId,
            @ApiParam(name = "eId", value = "设备id", required = true) @RequestParam("eId") String eId) {
        return new Response().success(null);
    }
}
