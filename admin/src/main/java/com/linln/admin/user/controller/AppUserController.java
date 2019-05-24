package com.linln.admin.user.controller;

import cn.hutool.core.util.StrUtil;
import com.linln.admin.user.validator.AppUserValid;
import com.linln.common.constant.StatusConst;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import com.linln.modules.residentialQuarters.service.ResidentialQuartersService;
import com.linln.modules.user.Bean.AppUserBean;
import com.linln.modules.user.Bean.AppUserByMobileBean;
import com.linln.modules.user.Bean.AppUserPageBean;
import com.linln.modules.user.domain.AppUser;
import com.linln.modules.user.service.AppUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/12
 */
@Controller
@RequestMapping("/user/appUser")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ResidentialQuartersService residentialQuartersService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("user:appUser:index")
    public String index(Model model, AppUser appUser, String userNameOrMobile) {
        Page<AppUserPageBean> list = null;
        //没有查询条件列表数据
        //有查询条件的列表数据
        if (StrUtil.isBlank(userNameOrMobile)) {
            list = appUserService.getDateNoPage(appUser.getStatus());

        } else {
            list = appUserService.getDatePage(appUser.getStatus(), userNameOrMobile);

        }

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/user/appUser/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("user:appUser:add")
    public String toAdd(Model model) {
        //查询所有小区的数据，用户显示到小区下拉列表
        List<ResidentialQuarters> dataAllResidentialQuarters = residentialQuartersService.getDataAllResidentialQuarters();
        model.addAttribute("residentialQuartersList", dataAllResidentialQuarters);

        return "/user/appUser/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("user:appUser:edit")
    public String toEdit(@PathVariable("id") AppUser appUser, Model model) {
        //通过id查询appUser信息
        AppUser byId = appUserService.getById(appUser.getId());
        model.addAttribute("appUser", byId);
        return "/user/appUser/edit";
    }

    /**
     * 修改业务
     *
     * @param appUserBean
     * @param id
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public ResultVo edit(AppUserBean appUserBean, Long id) {
        //修改数据
        AppUser byId = appUserService.getById(id);

        //构造appUsr对象


        appUserService.save(buildAppUser(byId,appUserBean));
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 保存添加/修改的数据
     *
     * @param valid 验证对象
     */
    @PostMapping({"/add"})
    @RequiresPermissions({"user:appUser:add", "user:appUser:edit"})
    @ResponseBody
    public ResultVo save(@Validated AppUserValid valid, Long id, AppUserBean appUserBean) {

        //校验通过电话号码查询是否该用户是否注册
        AppUserByMobileBean dataByMobile = appUserService.getDataByMobile(appUserBean.getMobilePhone());
        if (dataByMobile != null) {
            return ResultVoUtil.error(StatusConst.DUPLICATE_USER);
        }
        //校验该房间只能添加一个业主（通过房间号和栋数查询用户是否含有业主）
        Long dataIdByRoomNumberAndNumberOfBuildingsId = appUserService.getDataIdByRoomNumberAndNumberOfBuildingsId(Integer.parseInt(appUserBean.getRoomNumber()), appUserBean.getNumberOfBuildingsId());
        if (dataIdByRoomNumberAndNumberOfBuildingsId != null) {
            return ResultVoUtil.error(StatusConst.DUPLICATE_OWNER);
        }

        //保存数据
        appUserService.saveData(appUserBean);


        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("user:appUser:detail")
    public String toDetail(@PathVariable("id") AppUser appUser, Model model) {
        model.addAttribute("appUser", appUser);
        return "/user/appUser/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("user:appUser:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (appUserService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }

    //构造appUser对象
    private AppUser buildAppUser(AppUser byId,AppUserBean appUserBean) {


        byId.setUserName(appUserBean.getUserName());
        byId.setName(appUserBean.getName());
        byId.setUserType(appUserBean.getUserType());
        byId.setMobilePhone(appUserBean.getMobilePhone());
        byId.setStandbyTelephone(appUserBean.getStandbyTelephone());
        return byId;
    }

    ;
}