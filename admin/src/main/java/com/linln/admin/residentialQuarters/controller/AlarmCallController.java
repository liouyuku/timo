package com.linln.admin.residentialQuarters.controller;


import com.linln.admin.residentialQuarters.validator.AlarmCallValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.residentialQuarters.domain.AlarmCall;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import com.linln.modules.residentialQuarters.service.AlarmCallService;
import com.linln.modules.residentialQuarters.service.ResidentialQuartersService;
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
 * @author 小懒虫
 * @date 2019/05/23
 */
@Controller
@RequestMapping("/residential/alarmCall")
public class AlarmCallController {

    @Autowired
    private AlarmCallService alarmCallService;
    @Autowired
    private ResidentialQuartersService residentialQuartersService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("residential:alarmCall:index")
    public String index(Model model, AlarmCall alarmCall) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("telePhone", match -> match.contains());

        // 获取数据列表
        Example<AlarmCall> example = Example.of(alarmCall, matcher);
        Page<AlarmCall> list = alarmCallService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/residential/alarmCall/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("residential:alarmCall:add")

    public String toAdd(Model model) {
        //查询所有小区的信息
        List<ResidentialQuarters> dataAllResidentialQuarters = residentialQuartersService.getDataAllResidentialQuarters();
        //将小区的信息显示在下拉列表中
        model.addAttribute("rList",dataAllResidentialQuarters);

        return "/residential/alarmCall/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("residential:alarmCall:edit")
    public String toEdit(@PathVariable("id") AlarmCall alarmCall, Model model) {
        //查询所有小区的信息
        List<ResidentialQuarters> dataAllResidentialQuarters = residentialQuartersService.getDataAllResidentialQuarters();

        //将小区的信息显示在下拉列表中
        model.addAttribute("rList",dataAllResidentialQuarters);

        model.addAttribute("alarmCall", alarmCall);

        return "/residential/alarmCall/edit";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"residential:alarmCall:add","residential:alarmCall:edit"})
    @ResponseBody
    public ResultVo save(@Validated AlarmCallValid valid, AlarmCall alarmCall) {
        // 复制保留无需修改的数据
        if (alarmCall.getId() != null) {
            AlarmCall beAlarmCall = alarmCallService.getById(alarmCall.getId());
            EntityBeanUtil.copyProperties(beAlarmCall, alarmCall);
        }

        // 保存数据
        alarmCallService.save(alarmCall);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("residential:alarmCall:detail")
    public String toDetail(@PathVariable("id") AlarmCall alarmCall, Model model) {
        model.addAttribute("alarmCall",alarmCall);
        return "/residential/alarmCall/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("residential:alarmCall:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (alarmCallService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}