package com.linln.api.business.device;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.PhoneCallRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@RestController
public class VoIP extends BaseApi {

    /**
     * 呼叫失败转化为电话呼叫，存记录
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "呼叫失败转化为电话呼叫", response = Response.class)
    @RequestMapping(value = "/device/phoneCall", method = RequestMethod.POST)
    public @ResponseBody
    Response log(@Validated @RequestBody PhoneCallRequest request, BindingResult result) {
        Response res = verification(result);
        if(!res.getMeta().isSuccess())return res;
        return res;
    }

    /**
     * 获取落地电话的账号
     *
     * @return
     */
    @ApiOperation(value = "获取落地电话的账号", response = Response.class)
    @RequestMapping(value = "/account/get", method = RequestMethod.GET)
    public @ResponseBody
    Response get() {
        return new Response().success(null);
    }


    /**
     * 檢查號碼
     * @param number
     * @return
     */
    @ApiOperation(value = "检查是否是外地号码，如果是外地号码 ，下位机则调用呼叫电话的时候加0 .目前判断本地号码的依据就是是否是长株潭", response = Response.class)
    @RequestMapping(value = "/number/check", method = RequestMethod.GET)
    public @ResponseBody
    Response check(@ApiParam(name = "number", value = "需要檢查的號碼 ，可以是座機，手機", required = true) @RequestParam("number") String number) {
        return new Response().success(null);
    }

    /**
     * 释放落地电话的账号
     * @param user_name
     * @return
     */
    @ApiOperation(value = "释放落地电话的账号", response = Response.class)
    @RequestMapping(value = "/account/release", method = RequestMethod.PUT)
    public @ResponseBody
    Response release(@ApiParam(name = "user_name", value = "user_name", required = true) @RequestParam("user_name") String user_name) {
        return new Response().success(null);
    }
}
