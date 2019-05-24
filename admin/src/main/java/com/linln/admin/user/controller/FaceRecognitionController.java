package com.linln.admin.user.controller;


import com.linln.admin.user.validator.FaceRecognitionValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.user.domain.FaceRecognition;
import com.linln.modules.user.service.FaceRecognitionService;
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
 * @date 2019/05/16
 */
@Controller
@RequestMapping("/user/faceRecognition")
public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("user:faceRecognition:index")
    public String index(Model model, FaceRecognition faceRecognition) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("mobile", match -> match.contains());

        // 获取数据列表
        Example<FaceRecognition> example = Example.of(faceRecognition, matcher);
        Page<FaceRecognition> list = faceRecognitionService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/user/faceRecognition/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("user:faceRecognition:add")
    public String toAdd() {
        return "/user/faceRecognition/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("user:faceRecognition:edit")
    public String toEdit(@PathVariable("id") FaceRecognition faceRecognition, Model model) {
        model.addAttribute("faceRecognition", faceRecognition);
        return "/user/faceRecognition/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"user:faceRecognition:add","user:faceRecognition:edit"})
    @ResponseBody
    public ResultVo save(@Validated FaceRecognitionValid valid, FaceRecognition faceRecognition) {
        // 复制保留无需修改的数据
        if (faceRecognition.getId() != null) {
            FaceRecognition beFaceRecognition = faceRecognitionService.getById(faceRecognition.getId());
            EntityBeanUtil.copyProperties(beFaceRecognition, faceRecognition);
        }

        // 保存数据
        faceRecognitionService.save(faceRecognition);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("user:faceRecognition:detail")
    public String toDetail(@PathVariable("id") FaceRecognition faceRecognition, Model model) {
        model.addAttribute("faceRecognition",faceRecognition);
        return "/user/faceRecognition/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("user:faceRecognition:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (faceRecognitionService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}