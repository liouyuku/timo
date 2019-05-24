package com.linln.modules.user.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.user.domain.FaceRecognition;
import com.linln.modules.user.repository.FaceRecognitionRepository;
import com.linln.modules.user.service.FaceRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * @author 小懒虫
 * @date 2019/05/16
 */
@Service
public class FaceRecognitionServiceImpl implements FaceRecognitionService {

    @Autowired
    private FaceRecognitionRepository faceRecognitionRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public FaceRecognition getById(Long id) {
        return faceRecognitionRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<FaceRecognition> getPageList(Example<FaceRecognition> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return faceRecognitionRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param faceRecognition 实体对象
     */
    @Override
    public FaceRecognition save(FaceRecognition faceRecognition) {
        return faceRecognitionRepository.save(faceRecognition);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return faceRecognitionRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}