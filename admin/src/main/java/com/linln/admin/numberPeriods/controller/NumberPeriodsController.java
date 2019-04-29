package com.linln.admin.numberPeriods.controller;

import com.linln.admin.numberPeriods.validator.NumberPeriodsValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.numberPeriods.domain.NumberPeriods;
import com.linln.modules.numberPeriods.service.NumberPeriodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
@Controller
@RequestMapping("/numberPeriods/numberPeriods")
public class NumberPeriodsController {

    @Autowired
    private NumberPeriodsService numberPeriodsService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("numberPeriods:numberPeriods:index")
    public String index(Model model, NumberPeriods numberPeriods) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.contains());

        // 获取数据列表
        Example<NumberPeriods> example = Example.of(numberPeriods, matcher);
        Page<NumberPeriods> list = numberPeriodsService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/numberPeriods/numberPeriods/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("numberPeriods:numberPeriods:add")
    public String toAdd() {
        return "/numberPeriods/numberPeriods/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("numberPeriods:numberPeriods:edit")
    public String toEdit(@PathVariable("id") NumberPeriods numberPeriods, Model model) {
        model.addAttribute("numberPeriods", numberPeriods);
        return "/numberPeriods/numberPeriods/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"numberPeriods:numberPeriods:add","numberPeriods:numberPeriods:edit"})
    @ResponseBody
    public ResultVo save(@Validated NumberPeriodsValid valid, NumberPeriods numberPeriods) {
        // 复制保留无需修改的数据
        if (numberPeriods.getId() != null) {
            NumberPeriods beNumberPeriods = numberPeriodsService.getById(numberPeriods.getId());
            EntityBeanUtil.copyProperties(beNumberPeriods, numberPeriods);
        }

        // 保存数据
        numberPeriodsService.save(numberPeriods);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("numberPeriods:numberPeriods:detail")
    public String toDetail(@PathVariable("id") NumberPeriods numberPeriods, Model model) {
        model.addAttribute("numberPeriods",numberPeriods);
        return "/numberPeriods/numberPeriods/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("numberPeriods:numberPeriods:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (numberPeriodsService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}