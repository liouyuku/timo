package com.linln.admin.Equipment.controller;

import cn.hutool.core.util.StrUtil;
import com.linln.admin.Equipment.validator.LowerEquipmentValid;
import com.linln.common.constant.StatusConst;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.Equipment.Bean.LowerEquipmentBean;
import com.linln.modules.Equipment.Bean.LowerEquipmentEditBean;
import com.linln.modules.Equipment.Bean.LowerEquipmentRelationBean;
import com.linln.modules.Equipment.domain.LowerEquipment;
import com.linln.modules.Equipment.service.LowerEquipmentService;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import com.linln.modules.residentialQuarters.service.ResidentialQuartersService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/08
 */
@Controller
@RequestMapping("/Equipment/lowerEquipment")
public class LowerEquipmentController {

    @Autowired
    private LowerEquipmentService lowerEquipmentService;
    @Autowired
    private ResidentialQuartersService residentialQuartersService;

    /**
     * 重新绑定下位机
     */
    @PostMapping("/edit")
    public @ResponseBody
    ResultVo edit(LowerEquipmentEditBean lowerEquipmentEditBean) {

        if (lowerEquipmentEditBean.getBulidType() == 2) {
            //绑定类型为小区
            //根据设备的id,修改绑定类型，是否为广告机，是否为测试机，number_of_buildings_id
            lowerEquipmentService.updateDataById(lowerEquipmentEditBean.getId(), lowerEquipmentEditBean.getBulidType(), lowerEquipmentEditBean.getIsAdvertising(), lowerEquipmentEditBean.getIsTest(), lowerEquipmentEditBean.getResidentialQuartersId());

        } else {

            //绑定类型为栋数
            //校验栋数是否为空
            if (lowerEquipmentEditBean.getNumberOfBuildings() == null) {
                return ResultVoUtil.error(StatusConst.NUMBER_OF_BUILDINGS_NOT_NULL);
            }
            //根据设备的id,修改绑定类型，是否为广告机，是否为测试机，number_of_buildings_id
            lowerEquipmentService.updateDataById(lowerEquipmentEditBean.getId(), lowerEquipmentEditBean.getBulidType(), lowerEquipmentEditBean.getIsAdvertising(), lowerEquipmentEditBean.getIsTest(), lowerEquipmentEditBean.getNumberOfBuildings());

        }

        return ResultVoUtil.success();
    }

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("Equipment:lowerEquipment:index")
    public String index(Model model, LowerEquipment lowerEquipment, String queryCriteria) {

       /* // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("mac", match -> match.contains());

        // 获取数据列表
        Example<LowerEquipment> example = Example.of(lowerEquipment, matcher);
        Page<LowerEquipment> list = lowerEquipmentService.getPageList(example);
*/
        Page<LowerEquipmentBean> list = null;

        if (StrUtil.isBlank(queryCriteria)) {
            //没有查询条件(通过数据状态进行查询)
            list = lowerEquipmentService.getDataByStatus(lowerEquipment.getStatus());
        } else {
            //有查询条件(通过数据状态，位置或mac查询)
            list = lowerEquipmentService.getDataByStatusAndqueryCriteria(lowerEquipment.getStatus(), "%" + queryCriteria + "%");


        }
        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/Equipment/lowerEquipment/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("Equipment:lowerEquipment:add")
    public String toAdd() {
        return "/Equipment/lowerEquipment/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("Equipment:lowerEquipment:edit")
    public String toEdit(@PathVariable("id") LowerEquipment lowerEquipment, Model model) {
        //通过设备id查询下位机关联的信息（小区，期数，栋数），用于数据回显
        LowerEquipmentRelationBean lowerEquipment1 = lowerEquipmentService.getDataLowerEquipmentById(lowerEquipment.getId(), Byte.parseByte("1"));
        model.addAttribute("lowerEquipment", lowerEquipment1);
        //查询全部小区数据
        List<ResidentialQuarters> residentialQuartersList = residentialQuartersService.getDataAllResidentialQuarters();
        model.addAttribute("residentialQuartersList", residentialQuartersList);
        //显示是否为广告机下拉列表
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        model.addAttribute("isAdver", list);
        //显示是否门禁机下拉列表
        model.addAttribute("isEquipment", list);
        //显示是否为测试机下拉列表
        model.addAttribute("isTest", list);
        //显示绑定类型下拉列表
        List<Integer> buildList = new ArrayList<Integer>();
        buildList.add(1);
        buildList.add(2);
        model.addAttribute("buildType", buildList);
        return "/Equipment/lowerEquipment/edit";
    }

    /**
     * 保存添加/修改的数据
     *
     * @param valid 验证对象
     */
    @PostMapping({"/add"})
    @RequiresPermissions({"Equipment:lowerEquipment:add", "Equipment:lowerEquipment:edit"})
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
    @RequiresPermissions("Equipment:lowerEquipment:detail")
    public String toDetail(@PathVariable("id") LowerEquipment lowerEquipment, Model model) {
        model.addAttribute("lowerEquipment", lowerEquipment);
        return "/Equipment/lowerEquipment/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("Equipment:lowerEquipment:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
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