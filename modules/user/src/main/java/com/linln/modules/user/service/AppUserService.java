package com.linln.modules.user.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.user.Bean.AppUserBean;
import com.linln.modules.user.Bean.AppUserByMobileBean;
import com.linln.modules.user.Bean.AppUserPageBean;
import com.linln.modules.user.domain.AppUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/12
 */
public interface AppUserService {
    /**
     * 根据电话或者用户名查询列表数据
     *
     * @param status
     * @param userNameOrMobile
     * @return
     */
    Page<AppUserPageBean> getDatePage(Byte status, String userNameOrMobile);

    /**
     * 没有查询条件的数据列表
     *
     * @param status
     * @return
     */
    Page<AppUserPageBean> getDateNoPage(Byte status);

    /**
     * 通过房间号和栋数查询用户ID（判断一个房间里是否含有有一个业主）
     *
     * @param roomNumber
     * @param numberOfBuildingsId
     * @return
     */
    Long getDataIdByRoomNumberAndNumberOfBuildingsId(int roomNumber, Long numberOfBuildingsId);

    /**
     * 保存数据
     *
     * @param appUserBean
     */
    void saveData(AppUserBean appUserBean);

    /**
     * 通过电话号码查询数据
     *
     * @param mobile
     * @return
     */
    AppUserByMobileBean getDataByMobile(String mobile);

    /**
     * 查询所有的用户信息
     */
    List<AppUser> getAllData();

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<AppUser> getPageList(Example<AppUser> example);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    AppUser getById(Long id);

    /**
     * 保存数据
     *
     * @param appUser 实体对象
     */
    AppUser save(AppUser appUser);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}