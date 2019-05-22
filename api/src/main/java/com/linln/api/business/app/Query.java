package com.linln.api.business.app;


import com.linln.api.business.frombean.base.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查詢基礎數據/小區/期數/棟數/小區電話
 */
public class Query {

    @ApiOperation(value = "获取小区电话", response = Response.class)
    @RequestMapping(value = "/getServicePhone", method = RequestMethod.GET)
    public @ResponseBody
    Response serviceCall(@ApiParam(name = "villageId", value = "小区的id") @RequestParam("villageId") String villageId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取小区", response = Response.class)
    @RequestMapping(value = "/queryVillage", method = RequestMethod.GET)
    public @ResponseBody
    Response queryVillage() {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取期數", response = Response.class)
    @RequestMapping(value = "/queryPeriods", method = RequestMethod.GET)
    public @ResponseBody
    Response queryPeriods(@ApiParam(name = "id", value = "小区的id") @RequestParam("id") String id) {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取棟數", response = Response.class)
    @RequestMapping(value = "/queryPeriods", method = RequestMethod.GET)
    public @ResponseBody
    Response queryBuilding(@ApiParam(name = "id", value = "期數的id") @RequestParam("id") String id) {
        return new Response().success(null);
    }
}
