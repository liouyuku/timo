package com.linln.api.business.app;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.linln.api.business.Configuration;
import com.linln.api.business.frombean.base.Response;
import com.linln.api.business.frombean.response.VersionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short

import java.io.File;
import java.nio.charset.Charset;

/**
 * APP更新
 */
@RestController(value = "app_update")
public class Update {

    @Autowired
    Configuration configuration;

    @ApiOperation(value = "检查是否有更新", response = Response.class)
    @RequestMapping(value = "/app/version/check", method = RequestMethod.POST)
    public @ResponseBody
    Response checkVersion(@ApiParam(name = "type", value = "设备类型,android 安卓APP|ios 苹果APP", required = true) @RequestParam("type") String type) {

        JSONObject info;

        if ("android".equals(type)) {
            info = JSONUtil.readJSONObject(new File(configuration.getAndroid()), Charset.forName("utf-8"));
        } else {
            info = JSONUtil.readJSONObject(new File(configuration.getIos()), Charset.forName("utf-8"));
        }
        return new Response().success(JSONUtil.toBean(info, VersionResponse.class));
    }
}
