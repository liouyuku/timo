package com.linln.modules.lowerEquipment.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.lowerEquipment.domain.LowerEquipment;
import com.linln.modules.lowerEquipment.repository.LowerEquipmentRepository;
import com.linln.modules.lowerEquipment.service.LowerEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/25
 */
@Service
public class LowerEquipmentServiceImpl implements LowerEquipmentService {

    @Autowired
    private LowerEquipmentRepository lowerEquipmentRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public LowerEquipment getById(Long id) {
        return lowerEquipmentRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
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