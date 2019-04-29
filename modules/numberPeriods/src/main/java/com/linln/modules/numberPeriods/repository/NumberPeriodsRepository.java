package com.linln.modules.numberPeriods.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linln.modules.numberPeriods.domain.NumberPeriods;
import com.linln.modules.system.repository.BaseRepository;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
public interface NumberPeriodsRepository extends BaseRepository<NumberPeriods, Long> {
	/**
	 * 根据小区的id和期数的名称查询数据
	 * 
	 * @param rId
	 * @param nName
	 * @return
	 */
	@Query(value = "select * from residential_number_periods where residential_quarters_id=:rId and name=:nName and status=1", nativeQuery = true)
	NumberPeriods getNumberPeriodsByRidAnNname(@Param("rId") Long rId, @Param("nName") String nName);

	/**
	 * 通过小区的id，期数的编号，查询期数数据
	 * 
	 * @param rId
	 * @param Number
	 * @return
	 */

	@Query(value = "select * from residential_number_periods where residential_quarters_id=:rId and number_of_periods_number=:Number and status=1", nativeQuery = true)
	NumberPeriods getNumberPeriodsByRidAndNumber(@Param("rId") Long rId, @Param("Number") String Number);
}