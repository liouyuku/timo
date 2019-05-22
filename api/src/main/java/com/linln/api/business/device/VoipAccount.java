package com.linln.api.business.device;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.response.VoipAccountResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 网络电话账号
 */
@RestController
public class VoipAccount extends BaseApi {


    @ApiOperation(value = "获取落地电话的账号", response = VoipAccountResponse.class)
    @RequestMapping(value = "/voip", method = RequestMethod.GET)
    public @ResponseBody
    Response get() {
        return new Response().success(null);
    }

    @ApiOperation(value = "释放落地电话的账号", response = Response.class)
    @RequestMapping(value = "/voip", method = RequestMethod.PUT)
    public @ResponseBody
    Response release(@ApiParam(name = "user_name", value = "user_name", required = true) @RequestParam("user_name") String user_name) {
        return new Response().success(null);
    }
}
