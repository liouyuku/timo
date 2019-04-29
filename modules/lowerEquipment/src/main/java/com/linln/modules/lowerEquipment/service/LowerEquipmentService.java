package com.linln.modules.lowerEquipment.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.lowerEquipment.domain.LowerEquipment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/25
 */
public interface LowerEquipmentService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<LowerEquipment> getPageList(Example<LowerEquipment> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    LowerEquipment getById(Long id);

    /**
     * 保存数据
     * @param lowerEquipment 实体对象
     */
    LowerEquipment save(LowerEquipment lowerEquipment);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}