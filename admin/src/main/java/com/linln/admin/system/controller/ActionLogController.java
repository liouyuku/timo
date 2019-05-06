package com.linln.admin.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.system.domain.ActionLog;
import com.linln.modules.system.service.ActionLogService;

@Controller
@RequestMapping("/system/actionLog")
public class ActionLogController {

    @Autowired
    private ActionLogService actionLogService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("system:actionLog:index")
    public String index(Model model, ActionLog actionLog){

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching();

        // 获取日志列表
        Example<ActionLog> example = Example.of(actionLog, matcher);
        Page<ActionLog> list = actionLogService.getPageList(example);

        // 封装数据
        model.addAttribute("list",list.getContent());
        System.out.println("获取当前页码==="+list.getNumber());
        System.out.println("总共有多少条数据==="+list.getTotalElements());
        System.out.println("总共有多少页==="+list.getTotalPages());
        System.out.println("每页指定有多少元素==="+list.getSize());
        System.out.println("当前页实际有多少元素==="+list.getNumberOfElements());
        System.out.println("当前页是否有数据==="+list.hasContent());
        System.out.println("当前页是否是第一页==="+list.isFirst());
        System.out.println("当前页是否是最后一页==="+list.isLast());
        
        System.out.println("是否有上一页==="+list.hasPrevious());
        System.out.println("是否有下一页==="+list.hasNext());
        model.addAttribute("page",list);
        return "/system/actionLog/index";
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("system:actionLog:detail")
    public String toDetail(@PathVariable("id") ActionLog actionLog, Model model){
        model.addAttribute("actionLog",actionLog);
        return "/system/actionLog/detail";
    }

    /**
     * 删除指定的日志
     */
    @RequestMapping("/status/delete")
    @RequiresPermissions("system:actionLog:delete")
    @ResponseBody
    public ResultVo delete(
            @RequestParam(value = "ids", required = false) Long id){
        if (id != null){
            actionLogService.deleteId(id);
            return ResultVoUtil.success("删除日志成功");
        }else {
            actionLogService.emptyLog();
            return ResultVoUtil.success("清空日志成功");
        }
    }
}
