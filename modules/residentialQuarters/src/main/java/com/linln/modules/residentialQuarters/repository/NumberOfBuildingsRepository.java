package com.linln.modules.residentialQuarters.repository;


import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsPageListBean;
import com.linln.modules.residentialQuarters.domain.NumberOfBuildings;
import com.linln.modules.system.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/07
 */
public interface NumberOfBuildingsRepository extends BaseRepository<NumberOfBuildings, Long> {
    /**
     * 通过期数的id查询栋数的信息
     *
     * @param numberOfPeriodsId
     * @param status
     * @return
     */
    @Query("SELECT NEW com.linln.modules.residentialQuarters.bean.NumberOfBuildingsBean(nb.id,nb.name,nb.numberOfBuildingsNumber,nb.createDate,nb.numberOfPeriodsId,nb.ladderControlFristMac,nb.ladderControlSecondMac,nb.ladderControlFristRemark,nb.ladderControlSecondRemark,nb.status) from NumberOfBuildings nb where nb.numberOfPeriodsId=:numberOfPeriodsId and nb.status=:status")
    List<NumberOfBuildingsBean> getDataBynumberOfPeriodsIdAndStatus(@Param("numberOfPeriodsId") Long numberOfPeriodsId, @Param("status") Byte status);

    /**
     * 通过期数的id,栋数的编号，栋数的数据状态查询
     *
     * @param numberOfPeriodsId
     * @param numberOfBuildingsNumber
     * @param status
     * @return
     */
    @Query(" select new com.linln.modules.residentialQuarters.domain.NumberOfBuildings(nb.name,nb.numberOfBuildingsNumber) from NumberOfBuildings nb where nb.numberOfPeriodsId =:numberOfPeriodsId and nb.status=:status and nb.numberOfBuildingsNumber=:numberOfBuildingsNumber")
    NumberOfBuildings getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndNumberAndStatus(@Param("numberOfPeriodsId") Long numberOfPeriodsId, @Param("numberOfBuildingsNumber") String numberOfBuildingsNumber, @Param("status") Byte status);

    /**
     * 通过期数的id和栋数的名称，栋数的数据状态查询
     *
     * @param numberOfPeriodsId
     * @param BuildingsName
     * @param stauts
     * @return
     */
    @Query("select new com.linln.modules.residentialQuarters.domain.NumberOfBuildings(nb.name,nb.numberOfBuildingsNumber) from NumberOfBuildings nb where nb.name=:BuildingsName and nb.numberOfPeriodsId =:numberOfPeriodsId and nb.status=:status")
    NumberOfBuildings getDataByNumberofperiodsNameAndNumberOfBuildingsIdAndStatus(@Param("numberOfPeriodsId") Long numberOfPeriodsId, @Param("BuildingsName") String BuildingsName, @Param("status") Byte stauts);

    /**
     * 根据状态和小区的名称查询数据列表
     *
     * @param status
     * @param pageable
     * @return
     */
    @Query("SELECT new com.linln.modules.residentialQuarters.bean.NumberOfBuildingsPageListBean(nb.id,nb.name,nb.numberOfBuildingsNumber,r.name,np.name,nb.createDate) from NumberOfBuildings nb Left join Numberofperiods np on np.id=nb.numberOfPeriodsId left join ResidentialQuarters r on r.id=np.residentialQuartersId where nb.status=:status and r.name=:rName ")
    Page<NumberOfBuildingsPageListBean> findPageList(@Param("status") Byte status, @Param("rName") String rName, Pageable pageable);

    /**
     * 没有条件查询列表数据
     *
     * @param pageable
     * @return
     */
    @Query("SELECT new com.linln.modules.residentialQuarters.bean.NumberOfBuildingsPageListBean(nb.id,nb.name,nb.numberOfBuildingsNumber,r.name,np.name,nb.createDate) from NumberOfBuildings nb Left join Numberofperiods np on np.id=nb.numberOfPeriodsId left join ResidentialQuarters r on r.id=np.residentialQuartersId where nb.status=:status ")
    Page<NumberOfBuildingsPageListBean> findNoPageList(@Param("status") Byte status, Pageable pageable);
}