package com.linln.modules.numberPeriods.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.numberPeriods.domain.NumberPeriods;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
public interface NumberPeriodsService {
	/**
	 * 通过小区的id，期数的编号，查询期数数据
	 * @param rId
	 * @param Number
	 * @return
	 */
	NumberPeriods getNumberPeriodsByRidAndNumber(Long rId,String Number);
    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<NumberPeriods> getPageList(Example<NumberPeriods> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    NumberPeriods getById(Long id);

    /**
     * 保存数据
     * @param numberPeriods 实体对象
     */
    NumberPeriods save(NumberPeriods numberPeriods);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}