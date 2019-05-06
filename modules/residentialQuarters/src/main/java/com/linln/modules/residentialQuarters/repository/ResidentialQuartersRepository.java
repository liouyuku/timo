package com.linln.modules.residentialQuarters.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import com.linln.modules.system.repository.BaseRepository;



/**
 * @author 小懒虫
 * @date 2019/04/30
 */
public interface ResidentialQuartersRepository extends BaseRepository<ResidentialQuarters, Long> {
	/**
	 * 通过小区的名称和status查询小区的数据
	 * 
	 * @param rName
	 * @param status
	 * @return
	 */
	@Query(value="select * from residential_residential_quarters r where r.`name`=:rName and r.`status`=:status and address=:address",nativeQuery=true)
	ResidentialQuarters selectResidentialQuartersByrNameAndStauts(@Param("rName") String rName,@Param("status") byte status,@Param("address")  String address);
}