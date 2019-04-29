package com.linln.modules.residentialQuarters.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.domain.Residential;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
public interface ResidentialService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Residential> getPageList(Example<Residential> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    Residential getById(Long id);

    /**
     * 保存数据
     * @param residential 实体对象
     */
    Residential save(Residential residential);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}