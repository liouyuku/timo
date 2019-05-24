package com.linln.api.business.app;

import com.linln.api.business.BaseApi;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.*;
import com.linln.api.business.util.sms.SendingSMSUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * 用戶相關
 */
@RestController
public class User extends BaseApi {


    /**
     * 找回密码
     *
     * @param request
     * @param result
     * @return
     */
    @ApiOperation(value = "找回密码", response = Response.class)
    @RequestMapping(value = "/app/findPassword", method = RequestMethod.POST)
    public @ResponseBody
    Response findPassword(@Validated @RequestBody FindPasswordRequest request,
                          BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    /**
     * 修改密码
     *
     * @param newPassWord
     * @return
     */
    @ApiOperation(value = "修改密码", response = Response.class)
    @RequestMapping(value = "/app/newPassWord", method = RequestMethod.POST)
    public @ResponseBody
    Response newPassWord(@ApiParam(name = "newPassWord", value = "新密码", required = true) @RequestParam("newPassWord") String newPassWord) {
        return new Response().success(null);
    }

    /**
     * 验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "注册驗證碼", response = Response.class)
    @RequestMapping(value = "/app/captcha", method = RequestMethod.POST)
    public @ResponseBody
    String captcha(@ApiParam(name = "mobile", value = "用户手机号码", required = true) @RequestParam("mobile") String mobile) {
        SendingSMSUtil.initSDK();
        SendingSMSUtil.pushVerification(mobile);
        return null;
    }


    /**
     * 登陆
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "登录", response = Response.class)
    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    public @ResponseBody
    Response login(@Validated @RequestBody APPLoginRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }


    /**
     * 用户注册
     *
     * @param request
     * @param result
     * @return
     */
    @ApiOperation(value = "注册", response = Response.class)
    @RequestMapping(value = "/app/register", method = RequestMethod.POST)
    public @ResponseBody
    Response register(@Validated @RequestBody UserRegsiterRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    /**
     * 用户登出
     *
     * @param uId
     * @return
     */
    @ApiOperation(value = "登出", response = Response.class)
    @RequestMapping(value = "/app/loginOut", method = RequestMethod.POST)
    public @ResponseBody
    Response loginOut(@ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") String uId) {
        return new Response().success(null);
    }

    /**
     * 数据解绑
     *
     * @param request
     * @param result
     * @return
     */
    @ApiOperation(value = "解绑", response = Response.class)
    @RequestMapping(value = "/app/untying", method = RequestMethod.POST)
    public @ResponseBody
    Response untying(@Validated @RequestBody UntyingRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    @ApiOperation(value = "绑定房产", response = Response.class)
    @RequestMapping(value = "/app/bind", method = RequestMethod.POST)
    public @ResponseBody
    Response bind(@Validated @RequestBody BindRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    @ApiOperation(value = "获取首页即我的资产数据", response = Response.class)
    @RequestMapping(value = "/app/myProperty", method = RequestMethod.POST)
    public @ResponseBody
    Response myProperty(@ApiParam(name = "uId", value = "用户id", required = true)
                        @RequestParam("uId") String uId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "修改头像", response = Response.class)
    @RequestMapping(value = "/app/avatar", method = RequestMethod.POST)
    public @ResponseBody
    Response avatar(@ApiParam(name = "uId", value = "用户id", required = true)
                    @RequestParam("uId") String uId, HttpServletRequest req) {
        return new Response().success(null);
    }

    @ApiOperation(value = "提交投訴/建議", response = Response.class)
    @RequestMapping(value = "/app/complaints", method = RequestMethod.POST)
    public @ResponseBody
    Response complaints(@Validated @RequestBody ComplaintsRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    @ApiOperation(value = "修改用戶信息", response = Response.class)
    @RequestMapping(value = "/app/updateUser", method = RequestMethod.POST)
    public @ResponseBody
    Response complaints(@Validated @RequestBody UpdateUserRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }

    /**
     * 查询用户APP设置
     *
     * @param uId 用户ID
     * @return
     */
    @ApiOperation(value = "查詢當前用戶設置", response = Response.class)
    @RequestMapping(value = "/app/setting/get", method = RequestMethod.GET)
    public @ResponseBody
    Response userSetting(@ApiParam(name = "uId", value = "用户id", required = true)
                         @RequestParam("uId") String uId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "查詢當前用戶設置", response = Response.class)
    @RequestMapping(value = "/app/setting/defaultProperty", method = RequestMethod.GET)
    public @ResponseBody
    Response defaultProperty(@ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") String uId,
                             @ApiParam(name = "houseNumberId", value = "门牌ID", required = true) @RequestParam("uId") String houseNumberId) {
        return new Response().success(null);
    }


    /**
     * 用户APP 设置
     *
     * @param request
     * @param result
     * @return
     */
    @ApiOperation(value = "APP设置", response = Response.class)
    @RequestMapping(value = "/app/setting", method = RequestMethod.GET)
    public @ResponseBody
    Response setting(@Validated @RequestBody SettingRequest request, BindingResult result) {
        Response res = verification(result);
        if (!res.getMeta().isSuccess()) return res;
        return res;
    }


    /**
     * 修改REGKEY
     *
     * @param uId 用户ID
     * @return
     */
    @ApiOperation(value = "查詢當前用戶設置", response = Response.class)
    @RequestMapping(value = "/app/afterSetRegKey", method = RequestMethod.GET)
    public @ResponseBody
    Response afterSetRegKey(@ApiParam(name = "uId", value = "用户id", required = true) @RequestParam("uId") String uId,
                            @ApiParam(name = "regkey", value = "pushkey", required = true) @RequestParam("regkey") String regkey) {
        return new Response().success(null);
    }


}
