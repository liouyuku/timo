package com.linln.modules.residentialQuarters.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean;
import com.linln.modules.residentialQuarters.domain.Numberofperiods;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
public interface NumberofperiodsService {
    /**
     * 通过小区的id查询小区下面的期数
     * @param rId
     * @return
     */
    List<NumberOfPeriodsBean> getDataNumberOfPeriodsByResidentialQuartersId(Long rId, Byte status);
    /**
     * 查询冻结的数据
     *
     * @return
     */
    Page<NumberOfPeriodsPageListBean> getCustomFrozenPageList();

    /**
     * 根据条件查询期数数据
     *
     * @param rId
     * @param nName
     * @return
     */
    Optional<Numberofperiods> findOne(Example<Numberofperiods> example);

    /**
     * 获取自定义不带查询条件列表数据
     *
     * @param sql
     * @param param
     * @return
     */
    Page<NumberOfPeriodsPageListBean> getCustomNoPageList();

    /**
     * 获取自定义带查询条件列表数据
     *
     * @param sql
     * @param param
     * @return
     */
    Page<NumberOfPeriodsPageListBean> getCustomPageList(String rName, Byte status);

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Numberofperiods> getPageList(Example<Numberofperiods> example);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    Numberofperiods getById(Long id);

    /**
     * 保存数据
     *
     * @param numberofperiods 实体对象
     */
    Numberofperiods save(Numberofperiods numberofperiods);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}