package com.linln.admin.residentialQuarters.controller;

import com.linln.admin.residentialQuarters.validator.NumberofperiodsValid;
import com.linln.common.constant.StatusConst;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.GenerateNumberUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean;
import com.linln.modules.residentialQuarters.domain.Numberofperiods;
import com.linln.modules.residentialQuarters.repository.NumberofperiodsRepository;
import com.linln.modules.residentialQuarters.service.NumberofperiodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
@Controller
@RequestMapping("/residentialQuarters/numberofperiods")
public class NumberofperiodsController {

    @Autowired
    private NumberofperiodsService numberofperiodsService;
    @Autowired
    private NumberofperiodsRepository numberofperiodsRepository;
	//@Autowired
	//private NumberofperiodsService numberofperiodsService;

    /**
     * ajax请求查询（根据小区的id查询小区下面的期数）
     */
    @GetMapping("/getNumberOfPeriods")
    public @ResponseBody
    ResultVo findNumberOfPeriods(@RequestParam("rId") Long rId) {
        //通过小区的id查询小区下面的期数
        List<NumberOfPeriodsBean> data = numberofperiodsService.getDataNumberOfPeriodsByResidentialQuartersId(rId, Byte.parseByte("1"));
        return ResultVoUtil.success(data);
    }

    /**
     * 跳转到添加期数的页面
     */
    @GetMapping("/toAddNumberOfPeriods/{id}")
    @RequiresPermissions("residentialQuarters:numberofperiods:add")
    public String toAddNumberOfPeriods(@PathVariable("id") int rId, Model model) {
        System.out.println("-========");
        // 小区的id
        model.addAttribute("rId", rId);

        // 生成01-99的下拉列表的数据
        List<String> generateNumber = GenerateNumberUtil.GenerateNumber();
        model.addAttribute("numberList", generateNumber);
        return "/residentialQuarters/residentialQuarters/addNumberOfPeriods";
    }

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("residentialQuarters:numberofperiods:index")
    public String index(Model model, NumberOfPeriodsPageListBean numberOfPeriodsPageListBean) {

        Page<NumberOfPeriodsPageListBean> list = null;
        // 通过小区的名字查询
        if (numberOfPeriodsPageListBean.getRName() != null) {

            list = numberofperiodsService.getCustomPageList(
                    "%" + numberOfPeriodsPageListBean.getRName() + "%", numberOfPeriodsPageListBean.getStatus());


        } else {
            // 无条件查询
            if (numberOfPeriodsPageListBean.getStatus() == 1) {
                list = numberofperiodsService.getCustomNoPageList();
            }
            //查询冻结的数据
            else {
                list = numberofperiodsService.getCustomFrozenPageList();
            }

        }


        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/residentialQuarters/numberofperiods/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("residentialQuarters:numberofperiods:add")
    public String toAdd() {
        return "/residentialQuarters/numberofperiods/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("residentialQuarters:numberofperiods:edit")
    public String toEdit(@PathVariable("id") Numberofperiods numberofperiods, Model model) {
        model.addAttribute("numberofperiods", numberofperiods);
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("id", match -> match.contains());
        // 获取数据列表
        Example<Numberofperiods> example = Example.of(numberofperiods, matcher);
        //通过期数的id查询期数的编号
        Optional<Numberofperiods> findOne = numberofperiodsService.findOne(example);
        Numberofperiods numberofperiods1 = findOne.get();
        model.addAttribute("numberofperiods", numberofperiods1);
        // 生成01-99的下拉列表的数据
        List<String> generateNumber = GenerateNumberUtil.GenerateNumber();
        model.addAttribute("numberList", generateNumber);
        return "/residentialQuarters/numberofperiods/add";
    }

    /**
     * 保存添加/修改的数据
     *
     * @param valid 验证对象
     */
    @PostMapping({"/add", "/edit"})
    @RequiresPermissions({"residentialQuarters:numberofperiods:add", "residentialQuarters:numberofperiods:edit"})
    @ResponseBody
    public ResultVo save(@Validated NumberofperiodsValid valid, Numberofperiods numberofperiods) {
        // 通过小区的id,期数的名称查询数据（校验小区下面不能有重复的期数）
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.contains())
                .withMatcher("residential_quarters_id", match -> match.contains())
                .withMatcher("status", match -> match.contains());
        ExampleMatcher matcher1 = ExampleMatcher.matching()
                .withMatcher("number_of_periods_number", match -> match.contains())
                .withMatcher("residential_quarters_id", match -> match.contains())
                .withMatcher("status", match -> match.contains()).withIgnorePaths("id").withIgnorePaths("name");
        //添加期数时，校验期数名称不能重名
        if (numberofperiods.getId() == null) {
            // 获取数据列表
            Example<Numberofperiods> example = Example.of(numberofperiods, matcher);
            Optional<Numberofperiods> findOne = numberofperiodsService.findOne(example);
            boolean present = findOne.isPresent();
            if (present) {
                return ResultVoUtil.error(StatusConst.PERIOD_NAMES_NO_REPEATED);
            }
        }
        // 通过小区的id，期数的名称，期数编号查询数据（校验一个小区下面的期数的编号不能相同）
        if (!StringUtils.isEmpty(numberofperiods.getNumberOfPeriodsNumber())) {
            Example<Numberofperiods> example1 = Example.of(numberofperiods, matcher1);
            Optional<Numberofperiods> nDataSecond = numberofperiodsService.findOne(example1);
            if (nDataSecond.isPresent()) {
                return ResultVoUtil.error(StatusConst.DUPLICATE_NUMBER);
            }
        }
        // 复制保留无需修改的数据
        if (numberofperiods.getId() != null) {

            Numberofperiods beNumberofperiods = numberofperiodsService.getById(numberofperiods.getId());
            EntityBeanUtil.copyProperties(beNumberofperiods, numberofperiods);
        }

        // 保存数据
        numberofperiodsService.save(numberofperiods);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("residentialQuarters:numberofperiods:detail")
    public String toDetail(@PathVariable("id") Numberofperiods numberofperiods, Model model) {
        model.addAttribute("numberofperiods", numberofperiods);
        return "/residentialQuarters/numberofperiods/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("residentialQuarters:numberofperiods:status")
    @ResponseBody
    public ResultVo status(@PathVariable("param") String param,
                           @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (numberofperiodsService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}