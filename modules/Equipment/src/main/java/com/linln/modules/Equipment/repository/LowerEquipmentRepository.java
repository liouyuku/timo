package com.linln.modules.Equipment.repository;

import com.linln.modules.Equipment.Bean.LowerEquipmentBean;
import com.linln.modules.Equipment.Bean.LowerEquipmentRelationBean;
import com.linln.modules.Equipment.domain.LowerEquipment;
import com.linln.modules.system.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 小懒虫
 * @date 2019/05/08
 */
public interface LowerEquipmentRepository extends BaseRepository<LowerEquipment, Long> {
    /**
     * 通过设备id修改数据
     *
     * @param id
     * @param buildType
     * @param isAdvertising
     * @param isTest
     * @param numberOfBuildingsId
     */
    @Modifying
    @Query("update LowerEquipment e set e.bulidType=:buildType , e.isAdvertising=:isAdvertising,e.isTest=:isTest,e.numberOfBuildingsId=:numberOfBuildingsId where e.id=:id")
    public void updateDataById(@Param("id") Long id, @Param("buildType") Byte buildType, @Param("isAdvertising") Byte isAdvertising, @Param("isTest") Byte isTest, @Param("numberOfBuildingsId") Long numberOfBuildingsId);

    /* 通过设备的id查询与设备相关联的数据（用户数据的回显）
     *
     * @param lowerEquipmentId
     * @param status
     * @return
     */
    @Query("select new com.linln.modules.Equipment.Bean.LowerEquipmentRelationBean(e.id,e.mac,e.version,e.createDate,e.bulidType,e.isTest,e.isAdvertising,r.id,r.name,np.id,np.name,nb.id,nb.name) from LowerEquipment e left join NumberOfBuildings nb on e.numberOfBuildingsId=nb.id left join Numberofperiods np on nb.numberOfPeriodsId=np.id left join ResidentialQuarters r on r.id=np.residentialQuartersId  where e.id=:lowerEquipmentId and e.status=:status and r.status=:status and np.status=:status and nb.status=:status")
    LowerEquipmentRelationBean getDataLowerEquipmentById(@Param("lowerEquipmentId") Long lowerEquipmentId, @Param("status") Byte status);

    /**
     * 条件查询（根据数据状态，mac或者位置）
     *
     * @param status
     * @param queryCriteria
     * @return
     */
    @Query("select new com.linln.modules.Equipment.Bean.LowerEquipmentBean(e.id,e.mac,e.version,concat(r.name,np.name,nb.name,e.name),e.createDate,e.bulidType,e.isTest,e.isAdvertising) from LowerEquipment e left join NumberOfBuildings nb on e.numberOfBuildingsId=nb.id left join Numberofperiods np on nb.numberOfPeriodsId=np.id left join ResidentialQuarters r on r.id=np.residentialQuartersId  where (concat(r.name,np.name,nb.name,e.name) like :queryCriteria or e.mac like :queryCriteria) and e.status=:status and r.status=:status and np.status=:status and nb.status=:status")
    public Page<LowerEquipmentBean> getDataByStatusAndqueryCriteria(@Param("status") Byte status, @Param("queryCriteria") String queryCriteria, Pageable pageable);

    /**
     * 无条件查询
     *
     * @param status
     * @param pageable
     * @return
     */
    @Query("select new com.linln.modules.Equipment.Bean.LowerEquipmentBean(e.id,e.mac,e.version,concat(r.name,np.name,nb.name,e.name),e.createDate,e.bulidType,e.isTest,e.isAdvertising) from LowerEquipment e left join NumberOfBuildings nb on e.numberOfBuildingsId=nb.id left join Numberofperiods np on nb.numberOfPeriodsId=np.id left join ResidentialQuarters r on r.id=np.residentialQuartersId  where e.status=:status and r.status=:status and np.status=:status and nb.status=:status")
    //@Query("select case when e.bulidType=1 then new com.linln.modules.Equipment.Bean.LowerEquipmentBean(e.id,e.mac,e.version,concat(r.name,np.name,nb.name,e.name),e.createDate,e.bulidType,e.isTest,e.isAdvertising) else new com.linln.modules.Equipment.Bean.LowerEquipmentBean(e.id,e.mac,e.version,r.name,e.createDate,e.bulidType,e.isTest,e.isAdvertising) end from LowerEquipment e left join NumberOfBuildings nb on e.numberOfBuildingsId=nb.id left join Numberofperiods np on nb.numberOfPeriodsId=np.id left join ResidentialQuarters r on r.id=np.residentialQuartersId  where e.status=:status and r.status=:status and np.status=:status and nb.status=:status ")
    Page<LowerEquipmentBean> getDataByStatus(@Param("status") Byte status, Pageable pageable);
}