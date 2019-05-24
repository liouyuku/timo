package com.linln.modules.Equipment.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.Equipment.Bean.LowerEquipmentBean;
import com.linln.modules.Equipment.Bean.LowerEquipmentRelationBean;
import com.linln.modules.Equipment.domain.LowerEquipment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/08
 */
public interface LowerEquipmentService {
    /**
     * 通过设备id修改数据
     *
     * @param id
     * @param buildType
     * @param isAdvertising
     * @param isTest
     * @param numberOfBuildingsId
     */
    void updateDataById(Long id, Byte buildType, Byte isAdvertising, Byte isTest, Long numberOfBuildingsId);

    /**
     * 通过设备的id查询与设备相关联的数据（用户数据的回显）
     *
     * @param lowerEquipmentId
     * @param status
     * @return
     */
    LowerEquipmentRelationBean getDataLowerEquipmentById(Long lowerEquipmentId, Byte status);

    /**
     * 条件查询（根据数据状态，mac或者位置）
     *
     * @param status
     * @param queryCriteria
     * @return
     */
    Page<LowerEquipmentBean> getDataByStatusAndqueryCriteria(Byte status, String queryCriteria);

    /**
     * 无条件查询（根据数据状态查询）
     *
     * @param status
     * @return
     */
    Page<LowerEquipmentBean> getDataByStatus(Byte status);

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<LowerEquipment> getPageList(Example<LowerEquipment> example);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    LowerEquipment getById(Long id);

    /**
     * 保存数据
     *
     * @param lowerEquipment 实体对象
     */
    LowerEquipment save(LowerEquipment lowerEquipment);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}