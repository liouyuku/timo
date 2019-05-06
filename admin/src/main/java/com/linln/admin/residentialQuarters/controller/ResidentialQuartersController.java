package com.linln.admin.residentialQuarters.controller;

import com.linln.admin.residentialQuarters.validator.ResidentialQuartersValid;
import com.linln.common.constant.StatusConst;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
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
import java.util.Optional;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
@Controller
@RequestMapping("/residentialQuarters/residentialQuarters")
public class ResidentialQuartersController {

	@Autowired
	private ResidentialQuartersService residentialQuartersService;

	/**
	 * 列表页面
	 */
	@GetMapping("/index")
	@RequiresPermissions("residentialQuarters:residentialQuarters:index")
	public String index(Model model, ResidentialQuarters residentialQuarters) {

		// 创建匹配器，进行动态查询匹配
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.contains())
				.withMatcher("address", match -> match.contains());

		// 获取数据列表
		Example<ResidentialQuarters> example = Example.of(residentialQuarters, matcher);
		Page<ResidentialQuarters> list = residentialQuartersService.getPageList(example);

		// 封装数据
		model.addAttribute("list", list.getContent());
		
		model.addAttribute("page", list);
		return "/residentialQuarters/residentialQuarters/index";
	}

	/**
	 * 跳转到添加页面
	 */
	@GetMapping("/add")
	@RequiresPermissions("residentialQuarters:residentialQuarters:add")
	public String toAdd() {
		return "/residentialQuarters/residentialQuarters/add";
	}

	/**
	 * 跳转到编辑页面
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("residentialQuarters:residentialQuarters:edit")
	public String toEdit(@PathVariable("id") ResidentialQuarters residentialQuarters, Model model) {
		model.addAttribute("residentialQuarters", residentialQuarters);
		return "/residentialQuarters/residentialQuarters/add";
	}

	/**
	 * 保存添加/修改的数据
	 * 
	 * @param valid
	 *            验证对象
	 */
	@PostMapping({ "/add", "/edit" })
	@RequiresPermissions({ "residentialQuarters:residentialQuarters:add",
			"residentialQuarters:residentialQuarters:edit" })
	@ResponseBody
	public ResultVo save(@Validated ResidentialQuartersValid valid, ResidentialQuarters residentialQuarters) {
		// 通过小区的名称和status查询小区数据（校验小区不能重复）
		/*
		 * ResidentialQuarters rData = residentialQuartersService
		 * .selectResidentialQuartersByrNameAndStauts(residentialQuarters.
		 * getName(), Byte.valueOf("1"),residentialQuarters.getAddress());
		 */
		// 创建匹配器，进行动态查询匹配
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.contains())
				.withMatcher("status", match -> match.contains());

		Example<ResidentialQuarters> example = Example.of(residentialQuarters, matcher);
		Optional<ResidentialQuarters> rData=residentialQuartersService.selectResidentialQuartersByrNameAndStauts(example);
		if (rData != null) {
			return ResultVoUtil.error(StatusConst.RESIDENTIALQUARTERS_NAMES_NO_REPEATED);

		}
		// 复制保留无需修改的数据
		if (residentialQuarters.getId() != null) {
			ResidentialQuarters beResidentialQuarters = residentialQuartersService.getById(residentialQuarters.getId());
			EntityBeanUtil.copyProperties(beResidentialQuarters, residentialQuarters);
		}

		// 保存数据
		residentialQuartersService.save(residentialQuarters);
		return ResultVoUtil.SAVE_SUCCESS;
	}

	/**
	 * 跳转到详细页面
	 */
	@GetMapping("/detail/{id}")
	@RequiresPermissions("residentialQuarters:residentialQuarters:detail")
	public String toDetail(@PathVariable("id") ResidentialQuarters residentialQuarters, Model model) {
		model.addAttribute("residentialQuarters", residentialQuarters);
		return "/residentialQuarters/residentialQuarters/detail";
	}

	/**
	 * 设置一条或者多条数据的状态
	 */
	@RequestMapping("/status/{param}")
	@RequiresPermissions("residentialQuarters:residentialQuarters:status")
	@ResponseBody
	public ResultVo status(@PathVariable("param") String param,
			@RequestParam(value = "ids", required = false) List<Long> ids) {
		// 更新状态
		StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
		if (residentialQuartersService.updateStatus(statusEnum, ids)) {
			return ResultVoUtil.success(statusEnum.getMessage() + "成功");
		} else {
			return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
		}
	}
}