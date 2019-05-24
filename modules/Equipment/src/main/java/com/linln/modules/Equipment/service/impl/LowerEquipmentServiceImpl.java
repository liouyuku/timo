package com.linln.modules.Equipment.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.Equipment.Bean.LowerEquipmentBean;
import com.linln.modules.Equipment.Bean.LowerEquipmentRelationBean;
import com.linln.modules.Equipment.domain.LowerEquipment;
import com.linln.modules.Equipment.repository.LowerEquipmentRepository;
import com.linln.modules.Equipment.service.LowerEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/08
 */
@Service
public class LowerEquipmentServiceImpl implements LowerEquipmentService {

    @Autowired
    private LowerEquipmentRepository lowerEquipmentRepository;
    /**
     * 通过设备id修改数据
     *
     * @param id
     * @param buildType
     * @param isAdvertising
     * @param isTest
     * @param numberOfBuildingsId
     */
    @Transactional
   public  void updateDataById(Long id, Byte buildType, Byte isAdvertising, Byte isTest, Long numberOfBuildingsId){
        lowerEquipmentRepository.updateDataById(id,buildType,isAdvertising,isTest,numberOfBuildingsId);
    }

    /**
     * 通过设备的id查询与设备相关联的数据（用户数据的回显）
     *
     * @param lowerEquipmentId
     * @param status
     * @return
     */
    public LowerEquipmentRelationBean getDataLowerEquipmentById(Long lowerEquipmentId, Byte status){
        return lowerEquipmentRepository.getDataLowerEquipmentById(lowerEquipmentId,status);
    };

    /**
     * 条件查询（根据数据状态，mac或者位置）
     *
     * @param status
     * @param queryCriteria
     * @return
     */
    public Page<LowerEquipmentBean> getDataByStatusAndqueryCriteria(Byte status, String queryCriteria) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        lowerEquipmentRepository.getDataByStatusAndqueryCriteria(status, queryCriteria, page);
        return lowerEquipmentRepository.getDataByStatusAndqueryCriteria(status, queryCriteria, page);
    }

    ;

    /**
     * 无条件查询（根据数据状态查询）
     *
     * @param status
     * @return
     */
    public Page<LowerEquipmentBean> getDataByStatus(Byte status) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return lowerEquipmentRepository.getDataByStatus(status, page);
    }

    ;

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    @Override
    @Transactional
    public LowerEquipment getById(Long id) {
        return lowerEquipmentRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<LowerEquipment> getPageList(Example<LowerEquipment> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return lowerEquipmentRepository.findAll(example, page);
    }

    /**
     * 保存数据
     *
     * @param lowerEquipment 实体对象
     */
    @Override
    public LowerEquipment save(LowerEquipment lowerEquipment) {
        return lowerEquipmentRepository.save(lowerEquipment);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return lowerEquipmentRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}