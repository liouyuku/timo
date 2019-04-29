package com.linln.admin.lowerEquipment.controller;

import com.linln.admin.lowerEquipment.validator.LowerEquipmentValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.lowerEquipment.domain.LowerEquipment;
import com.linln.modules.lowerEquipment.service.LowerEquipmentService;
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
 * @date 2019/04/25
 */
@Controller
@RequestMapping("/lowerEquipment/lowerEquipment")
public class LowerEquipmentController {

	@Autowired
	private LowerEquipmentService lowerEquipmentService;

	/**
	 * 列表页面
	 */
	@GetMapping("/index")
	@RequiresPermissions("lowerEquipment:lowerEquipment:index")
	public String index(Model model, LowerEquipment lowerEquipment) {

		// 创建匹配器，进行动态查询匹配
		ExampleMatcher matcher = ExampleMatcher.matching();

		// 获取数据列表
		Example<LowerEquipment> example = Example.of(lowerEquipment, matcher);
		Page<LowerEquipment> list = lowerEquipmentService.getPageList(example);
		System.out.println("======" + list);
		// 封装数据
		model.addAttribute("list", list.getContent());
		model.addAttribute("page", list);
		return "/lowerEquipment/lowerEquipment/index";
	}

	/**
	 * 跳转到添加页面
	 */
	@GetMapping("/add")
	@RequiresPermissions("lowerEquipment:lowerEquipment:add")
	public String toAdd() {
		return "/lowerEquipment/lowerEquipment/add";
	}

	/**
	 * 跳转到编辑页面
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("lowerEquipment:lowerEquipment:edit")
	public String toEdit(@PathVariable("id") LowerEquipment lowerEquipment, Model model) {
		model.addAttribute("lowerEquipment", lowerEquipment);
		return "/lowerEquipment/lowerEquipment/add";
	}

	/**
	 * 保存添加/修改的数据
	 * 
	 * @param valid
	 *            验证对象
	 */
	@PostMapping({ "/add", "/edit" })
	@RequiresPermissions({ "lowerEquipment:lowerEquipment:add", "lowerEquipment:lowerEquipment:edit" })
	@ResponseBody
	public ResultVo save(@Validated LowerEquipmentValid valid, LowerEquipment lowerEquipment) {
		// 复制保留无需修改的数据
		if (lowerEquipment.getId() != null) {
			LowerEquipment beLowerEquipment = lowerEquipmentService.getById(lowerEquipment.getId());
			EntityBeanUtil.copyProperties(beLowerEquipment, lowerEquipment);
		}

		// 保存数据
		lowerEquipmentService.save(lowerEquipment);
		return ResultVoUtil.SAVE_SUCCESS;
	}

	/**
	 * 跳转到详细页面
	 */
	@GetMapping("/detail/{id}")
	@RequiresPermissions("lowerEquipment:lowerEquipment:detail")
	public String toDetail(@PathVariable("id") LowerEquipment lowerEquipment, Model model) {
		model.addAttribute("lowerEquipment", lowerEquipment);
		return "/lowerEquipment/lowerEquipment/detail";
	}

	/**
	 * 设置一条或者多条数据的状态
	 */
	@RequestMapping("/status/{param}")
	@RequiresPermissions("lowerEquipment:lowerEquipment:status")
	@ResponseBody
	public ResultVo status(@PathVariable("param") String param,
			@RequestParam(value = "ids", required = false) List<Long> ids) {
		// 更新状态
		StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
		if (lowerEquipmentService.updateStatus(statusEnum, ids)) {
			return ResultVoUtil.success(statusEnum.getMessage() + "成功");
		} else {
			return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
		}
	}
}