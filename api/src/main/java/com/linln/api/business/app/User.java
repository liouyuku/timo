package com.linln.api.business.app;

import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.request.APPLoginRequest;
import com.linln.api.business.frombean.request.FindPasswordRequest;
import com.linln.api.business.frombean.request.UntyingRequest;
import com.linln.api.business.frombean.request.UserRegsiterRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用戶相關
 */
@RestController
public class User {


    @ApiOperation(value = "找回密码", response = Response.class)
    @RequestMapping(value = "/findPassword", method = RequestMethod.POST)
    public @ResponseBody
    Response findPassword(@Validated @RequestBody FindPasswordRequest request,
                          BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                return new Response().failure(error.getDefaultMessage());
            }
        }
        return new Response().success(null);
    }

    /**
     * 修改密码
     *
     * @param newPassWord
     * @return
     */
    @ApiOperation(value = "修改密码", response = Response.class)
    @RequestMapping(value = "/newPassWord", method = RequestMethod.POST)
    public @ResponseBody
    Response newPassWord(@ApiParam(name = "newPassWord", value = "新密码", required = true) @RequestParam("newPassWord") String newPassWord) {
        return new Response().success(null);
    }

    /**
     * 登陆
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "登录", response = Response.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody APPLoginRequest request) {
        return null;
    }


    @ApiOperation(value = "注册", response = Response.class)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Response register(@Validated @RequestBody UserRegsiterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrors = result.getAllErrors();
            for (ObjectError error : objectErrors) {
                return new Response().failure(error.getDefaultMessage());
            }

        }
        return new Response().success(null);
    }

    @ApiOperation(value = "登出", response = Response.class)
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public @ResponseBody
    Response loginOut(@ApiParam(name = "uId", value = "用户id", required = true)
                      @RequestParam("uId") String uId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "解绑", response = Response.class)
    @RequestMapping(value = "/untying", method = RequestMethod.POST)
    public @ResponseBody
    Response untying(@Validated @RequestBody UntyingRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrors = result.getAllErrors();
            for (ObjectError error : objectErrors) {
                return new Response().failure(error.getDefaultMessage());
            }

        }
        return new Response().success(null);
    }

}
