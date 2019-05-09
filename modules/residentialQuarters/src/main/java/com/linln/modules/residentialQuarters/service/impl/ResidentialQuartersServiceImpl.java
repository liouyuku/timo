package com.linln.modules.residentialQuarters.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.mybatis.ResidentialQuartersMapper;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import com.linln.modules.residentialQuarters.repository.ResidentialQuartersRepository;
import com.linln.modules.residentialQuarters.service.ResidentialQuartersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
@Service
public class ResidentialQuartersServiceImpl implements ResidentialQuartersService {

    @Autowired
    private ResidentialQuartersRepository residentialQuartersRepository;

    @Autowired
    private ResidentialQuartersMapper dao;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public ResidentialQuarters getById(Long id) {
        return residentialQuartersRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<ResidentialQuarters> getPageList(Example<ResidentialQuarters> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        ResidentialQuarters a = new ResidentialQuarters();
        a.setName("s");
        dao.save(null);
        return residentialQuartersRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param residentialQuarters 实体对象
     */
    @Override
    public ResidentialQuarters save(ResidentialQuarters residentialQuarters) {
        return residentialQuartersRepository.save(residentialQuarters);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        
    	return residentialQuartersRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
    /**
	 * 通过小区的名称和status查询小区的数据
	 */
	@Override
	public Optional<ResidentialQuarters> selectResidentialQuartersByrNameAndStauts(Example<ResidentialQuarters> example) {
		
		return  residentialQuartersRepository.findOne(example);
		//return residentialQuartersRepository.selectResidentialQuartersByrNameAndStauts(rName, status,address);
	}
}