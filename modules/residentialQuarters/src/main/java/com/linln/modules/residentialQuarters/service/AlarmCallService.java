package com.linln.modules.residentialQuarters.service;


import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.domain.AlarmCall;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/23
 */
public interface AlarmCallService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<AlarmCall> getPageList(Example<AlarmCall> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    AlarmCall getById(Long id);

    /**
     * 保存数据
     * @param alarmCall 实体对象
     */
    AlarmCall save(AlarmCall alarmCall);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}