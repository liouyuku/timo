package com.linln.admin.residentialQuarters.controller;

import com.linln.admin.residentialQuarters.validator.ResidentialValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.GenerateNumberUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.numberPeriods.domain.NumberPeriods;
import com.linln.modules.numberPeriods.service.NumberPeriodsService;
import com.linln.modules.residentialQuarters.domain.Residential;
import com.linln.modules.residentialQuarters.service.ResidentialService;
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
@RequestMapping("/residentialQuarters/residential")
public class ResidentialController {

    @Autowired
    private ResidentialService residentialService;
    @Autowired
    private NumberPeriodsService numberPeriodsService;
    /**
     *增加期数 
     */
     @PostMapping(value="/addNumberPeriods")
     @ResponseBody
     public ResultVo addNumberPeriods(NumberPeriods numberPeriods){
    	 //通过小区的id查询和期数的编号查询数据（有数据不能插入）
    	 NumberPeriods numberPeriodsByRidAndNumber = numberPeriodsService.getNumberPeriodsByRidAndNumber(numberPeriods.getResidentialQuartersId(), numberPeriods.getNumberOfPeriodsNumber());
    	 numberPeriodsService.save(numberPeriods);
    	 return ResultVoUtil.SAVE_SUCCESS;
     }
    /**
     * 跳转到添加期数页面
     */
@GetMapping("/addNumberPeriods/{id}")

public String toAddNumberPeriods(Model model,@PathVariable("id") int id){
	//小区id
	model.addAttribute("rId",id);
	List<String> generateNumber = GenerateNumberUtil.GenerateNumber();
	model.addAttribute("number", generateNumber);
	return "/residentialQuarters/residential/addNumberPeriods";
	
}
    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("residentialQuarters:residential:index")
    public String index(Model model, Residential residential) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.contains())
                .withMatcher("address", match -> match.contains());

        // 获取数据列表
        Example<Residential> example = Example.of(residential, matcher);
        Page<Residential> list = residentialService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/residentialQuarters/residential/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("residentialQuarters:residential:add")
    public String toAdd() {
        return "/residentialQuarters/residential/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("residentialQuarters:residential:edit")
    public String toEdit(@PathVariable("id") Residential residential, Model model) {
        model.addAttribute("residential", residential);
        return "/residentialQuarters/residential/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"residentialQuarters:residential:add","residentialQuarters:residential:edit"})
    @ResponseBody
    public ResultVo save(@Validated ResidentialValid valid, Residential residential) {
        // 复制保留无需修改的数据
        if (residential.getId() != null) {
            Residential beResidential = residentialService.getById(residential.getId());
            EntityBeanUtil.copyProperties(beResidential, residential);
        }

        // 保存数据
        residentialService.save(residential);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("residentialQuarters:residential:detail")
    public String toDetail(@PathVariable("id") Residential residential, Model model) {
        model.addAttribute("residential",residential);
        return "/residentialQuarters/residential/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("residentialQuarters:residential:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (residentialService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}