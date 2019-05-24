package com.linln.api.business.app;


import com.linln.api.business.frombean.base.Response;
import com.linln.modules.residentialQuarters.service.NumberofperiodsService;
import com.linln.modules.residentialQuarters.service.ResidentialQuartersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> short

/**
 * 查詢基礎數據/小區/期數/棟數/小區電話
 */
@RestController
public class Query {

    @Autowired
    private NumberofperiodsService numberofperiodsService;

    @Autowired
    private ResidentialQuartersService residentialQuartersService;



    @ApiOperation(value = "获取小区电话", response = Response.class)
    @RequestMapping(value = "/app/queryServicePhone", method = RequestMethod.GET)
    public @ResponseBody
    Response queryServicePhone(@ApiParam(name = "villageId", value = "小区的id",example="1") @RequestParam("villageId") Long villageId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取小区", response = Response.class)
    @RequestMapping(value = "/app/queryVillages", method = RequestMethod.GET)
    public @ResponseBody
    Response queryVillage() {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取期數", response = Response.class)
    @RequestMapping(value = "/app/queryPeriods", method = RequestMethod.GET)
    public @ResponseBody
    Response queryPeriods(@ApiParam(name = "id", value = "小区的id") @RequestParam("id") String id) {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取棟數", response = Response.class)
    @RequestMapping(value = "/app/queryBuilding", method = RequestMethod.GET)
    public @ResponseBody
    Response queryBuilding(@ApiParam(name = "id", value = "期數的id") @RequestParam("id") String id) {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取APP通告", response = Response.class)
    @RequestMapping(value = "/app/queryAnnouncement", method = RequestMethod.GET)
    public @ResponseBody
    Response queryAnnouncement(@ApiParam(name = "uId", value = "用户ID") @RequestParam("uId") String uId) {
        return new Response().success(null);
    }

    @ApiOperation(value = "获取APP通告", response = Response.class)
    @RequestMapping(value = "/app/queryComplaints", method = RequestMethod.GET)
    public @ResponseBody
    Response queryComplaints(@ApiParam(name = "uId", value = "用户ID") @RequestParam("uId") String uId) {
        return new Response().success(null);
    }
}
