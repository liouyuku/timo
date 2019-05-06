package com.linln.modules.residentialQuarters.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
public interface ResidentialQuartersService {
	/**
	 * 通过小区的名称和status查询小区的数据
	 * 
	 * @param rName
	 * @param status
	 * @return
	 */
	Optional<ResidentialQuarters> selectResidentialQuartersByrNameAndStauts(Example<ResidentialQuarters> example);

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<ResidentialQuarters> getPageList(Example<ResidentialQuarters> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    ResidentialQuarters getById(Long id);

    /**
     * 保存数据
     * @param residentialQuarters 实体对象
     */
    ResidentialQuarters save(ResidentialQuarters residentialQuarters);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}