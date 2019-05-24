package com.linln.modules.residentialQuarters.repository;

import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean;
import com.linln.modules.residentialQuarters.domain.Numberofperiods;
import com.linln.modules.system.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author 小懒虫
 * @date 2019/04/30
 */
public interface NumberofperiodsRepository
        extends BaseRepository<Numberofperiods, Long> {
    /**
     * 通过小区的id查询小区下面的期数
     * @param rId
     * @return
     */
    @Query("select new com.linln.modules.residentialQuarters.bean.NumberOfPeriodsBean(np.id,np.name,np.numberOfPeriodsNumber,np.createDate,np.status,np.residentialQuartersId)  from Numberofperiods np where np.status=:status and np.residentialQuartersId=:rId")
    List<NumberOfPeriodsBean> getDataNumberOfPeriodsByResidentialQuartersId(@Param("rId") Long rId, @Param("status") Byte status);
    /**
     * 查询冻结的数据
     *
     * @param pageable
     * @return
     */
    @Query(value = "SELECT new com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean(n.id,r.name,n.name,n.numberOfPeriodsNumber,n.createDate) FROM Numberofperiods n RIGHT JOIN ResidentialQuarters r ON n.residentialQuartersId = r.id WHERE  n.status = 2 AND r.status = 1"
    )
    Page<NumberOfPeriodsPageListBean> getCustomFrozenPageList(Pageable pageable);

    /**
     * 自定义带查询条件列表数据
     *
     * @param sql
     * @param rName
     * @param pageable
     * @return
     */
    @Query(value = "SELECT new com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean(n.id,r.name,n.name,n.numberOfPeriodsNumber,n.createDate) FROM Numberofperiods n RIGHT JOIN ResidentialQuarters r ON n.residentialQuartersId = r.id WHERE r.name like :rName and n.status = :status AND r.status = 1"
    )
    Page<NumberOfPeriodsPageListBean> getCustomPageList(@Param("rName") String rName, @Param("status") Byte status, Pageable pageable);

    /**
     * 自定义不带查询条件列表数据
     *
     * @param sql
     * @param rName
     * @param pageable
     * @return
     */

    @Query(value = "SELECT new com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean(n.id,r.name,n.name,n.numberOfPeriodsNumber,n.createDate) FROM Numberofperiods n RIGHT JOIN ResidentialQuarters r ON n.residentialQuartersId = r.id")
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