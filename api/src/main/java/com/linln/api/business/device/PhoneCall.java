package com.linln.api.business.device;

import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.PhoneCallRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhoneCall {

    @ApiOperation(value = "呼叫失败转化为电话呼叫", response = Response.class)
    @RequestMapping(value = "/phoneCall", method = RequestMethod.POST)
    public @ResponseBody
    Response log(@RequestBody PhoneCallRequest phoneCallBean) {
        return new Response().success(null);
    }
}
