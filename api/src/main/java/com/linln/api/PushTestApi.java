package com.linln.api;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.PushRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用于push消息的调试
 */
@RestController
public class PushTestApi extends BaseApi {

    @ApiOperation(value = "推送到手机", response = Response.class)
    @RequestMapping(value = "/push/mobilePush", method = RequestMethod.POST)
    public @ResponseBody
    Response mobilePush(@Validated @RequestBody PushRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    @ApiOperation(value = "推送到下位机", response = Response.class)
    @RequestMapping(value = "/push/devicePush", method = RequestMethod.POST)
    public @ResponseBody
    Response devicePush(@Validated @RequestBody PushRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

}
