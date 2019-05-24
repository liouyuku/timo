package com.linln.admin.residentialQuarters.controller;


import com.linln.admin.residentialQuarters.validator.NoticeValid;
import com.linln.common.constant.StatusConst;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.residentialQuarters.bean.NoticeBean;
import com.linln.modules.residentialQuarters.bean.NoticeDataBean;
import com.linln.modules.residentialQuarters.domain.Notice;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import com.linln.modules.residentialQuarters.service.NoticeService;
import com.linln.modules.residentialQuarters.service.ResidentialQuartersService;
import com.linln.modules.user.domain.AppUser;
import com.linln.modules.user.service.AppUserService;
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
 * @author Mr. Zhou
 * @date 2019/05/10
 */
@Controller
@RequestMapping("/residential/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ResidentialQuartersService residentialQuartersService;
    @Autowired
    private AppUserService appUserService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("residential:notice:index")
    public String index(Model model, Notice notice) {

       /* // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains());

        // 获取数据列表
        Example<Notice> example = Example.of(notice, matcher);
        Page<Notice> list = noticeService.getPageList(example);*/
        Page<NoticeDataBean> list = null;

        if (notice.getTitle() == null) {
            //没有搜索条件
            list = noticeService.getDataNoPageList(notice.getStatus());
        } else {
            //根据标题进行搜索
           list= noticeService.getDataPageList(notice.getStatus(), notice.getTitle());
        }

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/residential/notice/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("residential:notice:add")
    public String toAdd(Model model) {
        //查询小区信息将小区的信息显示到页面上、
        List<ResidentialQuarters> dataAllResidentialQuarters = residentialQuartersService.getDataAllResidentialQuarters();
        model.addAttribute("residentialQuartersList", dataAllResidentialQuarters);
        //查询所有用户，显示到页面上
        List<AppUser> allData = appUserService.getAllData();
        model.addAttribute("userList", allData);
        //将发布位置下拉列表显示到页面上
        List<String> buildList = new ArrayList<String>();


        return "/residential/notice/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("residential:notice:edit")
    public String toEdit(@PathVariable("id") Notice notice, Model model) {
        //通过通告的id查询通告的信息
        Notice noticeFrist = noticeService.getById(notice.getId());
        model.addAttribute("noticeFrist", noticeFrist);
        model.addAttribute("notice", notice);
        return "/residential/notice/add";
    }

    /**
     * 保存添加/修改的数据
     *
     * @param valid 验证对象
     */
    @PostMapping({"/add", "/edit"})
    @RequiresPermissions({"residential:notice:add", "residential:notice:edit"})
    @ResponseBody
    public ResultVo save(@Validated NoticeValid valid, NoticeBean noticeBean) {
        if (noticeBean.getBuildType() == null) {
            //没有选中
            return ResultVoUtil.error(StatusConst.PUBLISHING_LOCATION_NO_CHOICE);

        }

        if (noticeBean.getBuildType() == 1) {
            //绑定为所有人
            //保存
            noticeBean.setRelationId(Long.valueOf("-1"));
            noticeService.saveData(noticeBean);
        } else if (noticeBean.getBuildType() == 2) {
            //判断uid是否为空
            if (noticeBean.getUserId() == null) {
                return ResultVoUtil.error(StatusConst.NO_FIXED_PERSON_WAS_SELECTED);
            }
            //绑定类型为固定人
            noticeBean.setRelationId(noticeBean.getUserId());
            noticeService.saveData(noticeBean);
        } else if (noticeBean.getBuildType() == 3) {
            //绑定类型为小区
            if (noticeBean.getResidentialQuartersId() == null) {
                return ResultVoUtil.error(StatusConst.NO_SELECTED_DISTRICTS);
            }
            noticeBean.setRelationId(noticeBean.getResidentialQuartersId());
            noticeService.saveData(noticeBean);
        } else if (noticeBean.getBuildType() == 4) {
            //绑定为期数
            if (noticeBean.getNumberOfPeriodsId() == null) {
                return ResultVoUtil.error(StatusConst.NO_SELECTED_RESIDENTIALQUARTERS);
            }
            noticeBean.setRelationId(noticeBean.getNumberOfPeriodsId());
            noticeService.saveData(noticeBean);
        } else if (noticeBean.getBuildType() == 5) {
            //绑定为栋数
            if (noticeBean.getNumberOfBuildingsId() == null) {
                return ResultVoUtil.error(StatusConst.NO_SELECTED_BUILDINGS);
            }
            noticeBean.setRelationId(noticeBean.getNumberOfBuildingsId());
            noticeService.saveData(noticeBean);
        } else {
            //没有选中
            return ResultVoUtil.error(StatusConst.PUBLISHING_LOCATION_NO_CHOICE);

        }


        // 复制保留无需修改的数据
       /* if (noticeBean.getId() != null) {
            Notice beNotice = noticeService.getById(noticeBean.getId());
            EntityBeanUtil.copyProperties(beNotice, noticeBean);
        }

        // 保存数据
        noticeService.save(noticeBean);*/
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("residential:notice:detail")
    public String toDetail(@PathVariable("id") Notice notice, Model model) {
        model.addAttribute("notice", notice);
        return "/residential/notice/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("residential:notice:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (noticeService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}