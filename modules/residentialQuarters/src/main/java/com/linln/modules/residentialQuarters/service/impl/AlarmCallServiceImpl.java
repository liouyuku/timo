package com.linln.modules.residentialQuarters.service.impl;


import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.domain.AlarmCall;
import com.linln.modules.residentialQuarters.repository.AlarmCallRepository;
import com.linln.modules.residentialQuarters.service.AlarmCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/23
 */
@Service
public class AlarmCallServiceImpl implements AlarmCallService {

    @Autowired
    private AlarmCallRepository alarmCallRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public AlarmCall getById(Long id) {
        return alarmCallRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<AlarmCall> getPageList(Example<AlarmCall> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return alarmCallRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param alarmCall 实体对象
     */
    @Override
    public AlarmCall save(AlarmCall alarmCall) {
        return alarmCallRepository.save(alarmCall);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return alarmCallRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}