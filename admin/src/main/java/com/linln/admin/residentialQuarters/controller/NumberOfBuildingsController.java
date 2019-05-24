package com.linln.admin.residentialQuarters.controller;


import com.linln.admin.residentialQuarters.validator.NumberOfBuildingsValid;
import com.linln.common.constant.StatusConst;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.GenerateNumberUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsPageListBean;
import com.linln.modules.residentialQuarters.domain.NumberOfBuildings;
import com.linln.modules.residentialQuarters.service.NumberOfBuildingsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/07
 */
@Controller
@RequestMapping("/residential/numberOfBuildings")
public class NumberOfBuildingsController {

    @Autowired
    private NumberOfBuildingsService numberOfBuildingsService;

    /**
     * ajax请求（通过期数的id查询栋数的信息）
     */
    @GetMapping("/getDataNumberOfBuildings")
    public @ResponseBody
    ResultVo getDataNumberOfBuildings(@RequestParam("numberOfPeriodsId") Long numberOfPeriodsId) {
        //通过期数的id查询栋数信息
        List<NumberOfBuildingsBean> data = numberOfBuildingsService.getDataBynumberOfPeriodsIdAndStatus(numberOfPeriodsId, Byte.parseByte("1"));
        return ResultVoUtil.success(data);
    }

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("residential:numberOfBuildings:index")
    public String index(Model model, NumberOfBuildings numberOfBuildings, String rName) {

        /*// 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.contains());

        // 获取数据列表
        Example<NumberOfBuildings> example = Example.of(numberOfBuildings, matcher);
        Page<NumberOfBuildings> list = numberOfBuildingsService.getPageList(example);*/
        Page<NumberOfBuildingsPageListBean> list = null;
        if (numberOfBuildings.getName() == null) {
            //没有查询条件
            list = numberOfBuildingsService.findNoPageList(numberOfBuildings.getStatus());
        } else {
            //有条件查询(小区名称，状态查询)
            list = numberOfBuildingsService.findPageList(numberOfBuildings.getStatus(), rName);
        }
        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/residential/numberOfBuildings/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add/{id}")
    @RequiresPermissions("residential:numberOfBuildings:add")
    public String toAdd(@PathVariable("id") int id, Model model) {
        // model.addAttribute("numberofperiods",null);
        //给页面设置期数的id
        model.addAttribute("nId", id);
        List<String> stringList = GenerateNumberUtil.GenerateNumber();
        //给页面设置栋数编号
        model.addAttribute("numList", stringList);
        return "/residential/numberOfBuildings/addNumberOfBuildings";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("residential:numberOfBuildings:edit")
    public String toEdit(@PathVariable("id") NumberOfBuildings numberOfBuildings, Model model) {
        //通过栋数的id查询栋数信息
        numberOfBuildingsService.getById(numberOfBuildingsService.getById(numberOfBuildings.getId()).getId());
        model.addAttribute("numberOfBuildings", numberOfBuildings);
        List<String> stringList = GenerateNumberUtil.GenerateNumber();
        model.addAttribute("numList", stringList);
        return "/residential/numberOfBuildings/add";
    }

    /**
     * 保存添加/修改的数据
     *
     * @param valid 验证对象
     */
    @PostMapping({"/add", "/edit"})
    @RequiresPermissions({"residential:numberOfBuildings:add", "residential:numberOfBuildings:edit"})
    @ResponseBody
    public ResultVo save(@Validated NumberOfBuildingsValid valid, NumberOfBuildings numberOfBuildings) {
        //校验同一期数下面的栋数的名称不能相同（通过期数的id，栋数的名称，栋数的数据状态查询）
        NumberOfBuildings data = numberOfBuildingsService.getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndStatus(numberOfBuildings.getNumberOfPeriodsId(), numberOfBuildings.getName(), Byte.valueOf("1"));
        if (data != null) {
            return ResultVoUtil.error(StatusConst.DUPLICATE_NAME);
        }
        //校验栋数的编号不能相同（有编号就要校验，通过期数的id,栋数的名称，栋数的编号，栋数的数据状态查询）
        if (numberOfBuildings.getNumberOfBuildingsNumber() != null) {
            NumberOfBuildings dataSecond = numberOfBuildingsService.getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndNumberAndStatus(numberOfBuildings.getNumberOfPeriodsId(), numberOfBuildings.getNumberOfBuildingsNumber(), Byte.valueOf("1"));
            if (dataSecond != null) {
                return ResultVoUtil.error(StatusConst.DUPLICATE_NUMBER);
            }
        }
        // 复制保留无需修改的数据
        if (numberOfBuildings.getId() != null) {
            NumberOfBuildings beNumberOfBuildings = numberOfBuildingsService.getById(numberOfBuildings.getId());
            EntityBeanUtil.copyProperties(beNumberOfBuildings, numberOfBuildings);
        }

        // 保存数据
        numberOfBuildingsService.save(numberOfBuildings);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("residential:numberOfBuildings:detail")
    public String toDetail(@PathVariable("id") NumberOfBuildings numberOfBuildings, Model model) {
        model.addAttribute("numberOfBuildings", numberOfBuildings);
        return "/residential/numberOfBuildings/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("residential:numberOfBuildings:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (numberOfBuildingsService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}