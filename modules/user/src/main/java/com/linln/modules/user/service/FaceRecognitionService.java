package com.linln.modules.user.service;


import com.linln.common.enums.StatusEnum;
import com.linln.modules.user.domain.FaceRecognition;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/16
 */
public interface FaceRecognitionService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<FaceRecognition> getPageList(Example<FaceRecognition> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    FaceRecognition getById(Long id);

    /**
     * 保存数据
     * @param faceRecognition 实体对象
     */
    FaceRecognition save(FaceRecognition faceRecognition);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}