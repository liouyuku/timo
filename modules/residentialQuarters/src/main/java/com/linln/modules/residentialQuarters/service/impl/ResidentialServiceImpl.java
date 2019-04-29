package com.linln.modules.residentialQuarters.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.domain.Residential;
import com.linln.modules.residentialQuarters.repository.ResidentialRepository;
import com.linln.modules.residentialQuarters.service.ResidentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
@Service
public class ResidentialServiceImpl implements ResidentialService {

    @Autowired
    private ResidentialRepository residentialRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Residential getById(Long id) {
        return residentialRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Residential> getPageList(Example<Residential> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return residentialRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param residential 实体对象
     */
    @Override
    public Residential save(Residential residential) {
        return residentialRepository.save(residential);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return residentialRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}