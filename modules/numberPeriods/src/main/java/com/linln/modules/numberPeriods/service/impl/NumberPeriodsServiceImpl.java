package com.linln.modules.numberPeriods.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.numberPeriods.domain.NumberPeriods;
import com.linln.modules.numberPeriods.repository.NumberPeriodsRepository;
import com.linln.modules.numberPeriods.service.NumberPeriodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
@Service
public class NumberPeriodsServiceImpl implements NumberPeriodsService {

	@Autowired
	private NumberPeriodsRepository numberPeriodsRepository;

	/**
	 * 根据ID查询数据
	 * 
	 * @param id
	 *            主键ID
	 */
	@Override
	@Transactional
	public NumberPeriods getById(Long id) {
		return numberPeriodsRepository.findById(id).orElse(null);
	}

	/**
	 * 获取分页列表数据
	 * 
	 * @param example
	 *            查询实例
	 * @return 返回分页数据
	 */
	@Override
	public Page<NumberPeriods> getPageList(Example<NumberPeriods> example) {
		// 创建分页对象
		PageRequest page = PageSort.pageRequest();
		return numberPeriodsRepository.findAll(example, page);
	}

	/**
	 * 保存数据
	 * 
	 * @param numberPeriods
	 *            实体对象
	 */
	@Override
	public NumberPeriods save(NumberPeriods numberPeriods) {
		return numberPeriodsRepository.save(numberPeriods);
	}

	/**
	 * 状态(启用，冻结，删除)/批量状态处理
	 */
	@Override
	@Transactional
	public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
		return numberPeriodsRepository.updateStatus(statusEnum.getCode(), idList) > 0;
	}

	/**
	 * 通过小区的id，期数的编号，查询期数数据
	 * 
	 * @param rId
	 * @param Number
	 * @return
	 */
	@Override
	public NumberPeriods getNumberPeriodsByRidAndNumber(Long rId, String Number) {

		return numberPeriodsRepository.getNumberPeriodsByRidAndNumber(rId, Number);
	}

	/**
	 * 根据小区的id和期数的名称查询数据
	 */
	@Override
	public NumberPeriods getNumberPeriodsByRidAnNname(Long rId, String nName) {
		
		return numberPeriodsRepository.getNumberPeriodsByRidAnNname(rId, nName);
	}
}