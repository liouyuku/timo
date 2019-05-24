package com.linln.modules.residentialQuarters.service;


import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsPageListBean;
import com.linln.modules.residentialQuarters.domain.NumberOfBuildings;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/07
 */
public interface NumberOfBuildingsService {
    /**
     *通过期数的id查询栋数的信息
     * @param numberOfPeriodsId
     * @param status
     * @return
     */
    List<NumberOfBuildingsBean> getDataBynumberOfPeriodsIdAndStatus(Long numberOfPeriodsId, Byte status);
    /**
     * 通过期数的id,栋数的编号，栋数的数据状态查询
     *
     * @param numberOfPeriodsId
     *
     * @param numberOfBuildingsNumber
     * @param status
     * @return
     */
    NumberOfBuildings getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndNumberAndStatus(Long numberOfPeriodsId, String numberOfBuildingsNumber, Byte status);

    /**
     * 通过期数的id和栋数的名称，栋数的数据状态查询
     *
     * @param numberOfPeriodsId
     * @param buildingsName
     * @param stauts
     * @return
     */
    NumberOfBuildings getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndStatus(Long numberOfPeriodsId, String buildingsName, Byte stauts);

    /**
     * 根据状态和小区的名称查询列表数据
     *
     * @param status
     * @param rName
     * @return
     */
    Page<NumberOfBuildingsPageListBean> findPageList(Byte status, String rName);

    /**
     * 没有条件查询列表数据
     */
    Page<NumberOfBuildingsPageListBean> findNoPageList(Byte status);

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<NumberOfBuildings> getPageList(Example<NumberOfBuildings> example);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    NumberOfBuildings getById(Long id);

    /**
     * 保存数据
     *
     * @param numberOfBuildings 实体对象
     */
    NumberOfBuildings save(NumberOfBuildings numberOfBuildings);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}