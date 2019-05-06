package com.linln.modules.residentialQuarters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean;
import com.linln.modules.residentialQuarters.domain.Numberofperiods;
import com.linln.modules.system.repository.BaseRepository;


/**
 * @author 小懒虫
 * @date 2019/04/30
 */
public interface NumberofperiodsRepository
		extends  BaseRepository<Numberofperiods, Long> {
	/**
	 * 自定义带查询条件列表数据
	 * @param sql
	 * @param rName
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT 	n.id, r.`name` AS rName, n.`name` AS name,n.number_of_periods_number as numberOfPeriodsNumber,n.createDate as createDate FROM residential_numberofperiods n RIGHT JOIN residential_residential_quarters r ON n.residential_quarters_id = r.id :sql :rName", 
		countQuery = "SELECT count(*) FROM residential_numberofperiods n RIGHT JOIN residential_residential_quarters r ON n.residential_quarters_id = r.id :sql :rName", nativeQuery = true)
	Page<Object[]> getCustomPageList(@Param("sql") String sql,@Param("rName") String rName, Pageable pageable);
	/**
	 * 自定义不带查询条件列表数据
	 * @param sql
	 * @param rName
	 * @param pageable
	 * @return
	 */
	/*@Query(value = "SELECT n.id, r.`name` AS rName, n.`name` AS name,n.number_of_periods_number as numberOfPeriodsNumber,n.createDate as createDate FROM residential_numberofperiods n RIGHT JOIN residential_residential_quarters r ON n.residential_quarters_id = r.id", 
		countQuery = "SELECT count(*) FROM residential_numberofperiods n RIGHT JOIN residential_residential_quarters r ON n.residential_quarters_id = r.id :sql :rName", nativeQuery = true)
	*/
	@Query(value = "SELECT new com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean(n.id,n.name,r.name,n.numberOfPeriodsNumber,n.createDate) FROM Numberofperiods n RIGHT JOIN ResidentialQuarters r ON n.residentialQuartersId = r.id")
	Page<NumberOfPeriodsPageListBean> getCustomNoPageList(Pageable pageable);
	// Pageable pageable

	/**
	 * 根据小区的id和期数的名称查询数据
	 * 
	 * @param rId
	 * @param nName
	 * @return
	 */
	@Query(value = "select * from residential_numberofperiods where residential_quarters_id=:rId and name=:nName and status=:status", nativeQuery = true)
	Numberofperiods getNumberPeriodsByRidAnNname(@Param("rId") Long rId, @Param("nName") String nName,
			@Param("status") byte status);

	/**
	 * 通过小区的id，期数的名称，期数的编号，查询期数数据
	 * 
	 * @param rId
	 * @param Number
	 * @return
	 */

	@Query(value = "select * from residential_numberofperiods where residential_quarters_id=:rId and number_of_periods_number=:Number and status=:status and name=:nName", nativeQuery = true)
	Numberofperiods getNumberPeriodsByRidAndNumber(@Param("rId") Long rId, @Param("Number") String Number,
			@Param("status") byte status, @Param("nName") String nName);
}