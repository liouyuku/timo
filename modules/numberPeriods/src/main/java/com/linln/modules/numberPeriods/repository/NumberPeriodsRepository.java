package com.linln.modules.numberPeriods.repository;

import org.springframework.data.jpa.repository.Query;

import com.linln.modules.numberPeriods.domain.NumberPeriods;
import com.linln.modules.system.repository.BaseRepository;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
public interface NumberPeriodsRepository extends BaseRepository<NumberPeriods, Long> {
	/**
	 * 通过小区的id，期数的编号，查询期数数据
	 * @param rId
	 * @param Number
	 * @return
	 */
	
	@Query("select * from residential_number_periods where residential_quarters_id=?1 and number_of_periods_number=?2")
	NumberPeriods getNumberPeriodsByRidAndNumber(Long rId,String Number);
}