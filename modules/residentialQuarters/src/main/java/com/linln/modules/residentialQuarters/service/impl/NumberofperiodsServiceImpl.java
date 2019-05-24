package com.linln.modules.residentialQuarters.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfPeriodsPageListBean;
import com.linln.modules.residentialQuarters.domain.Numberofperiods;
import com.linln.modules.residentialQuarters.repository.NumberofperiodsRepository;
import com.linln.modules.residentialQuarters.service.NumberofperiodsService;
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
public class NumberofperiodsServiceImpl implements NumberofperiodsService {

    @Autowired
    private NumberofperiodsRepository numberofperiodsRepository;
    /**
     * 通过小区的id查询小区下面的期数
     * @param rId
     * @return
     */
    public List<NumberOfPeriodsBean> getDataNumberOfPeriodsByResidentialQuartersId(Long rId, Byte status){
        return numberofperiodsRepository.getDataNumberOfPeriodsByResidentialQuartersId(rId,status);
    };

    /**
     * 查询冻结的数据
     *
     * @return
     */
   public Page<NumberOfPeriodsPageListBean> getCustomFrozenPageList() {
       // 创建分页对象
       PageRequest page = PageSort.pageRequest();
       Page<NumberOfPeriodsPageListBean> data= numberofperiodsRepository.getCustomFrozenPageList(page);
        return  data;
    }

    ;

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Numberofperiods getById(Long id) {
        return numberofperiodsRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Numberofperiods> getPageList(Example<Numberofperiods> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();

        return numberofperiodsRepository.findAll(example, page);
    }

    /**
     * 保存数据
     *
     * @param numberofperiods 实体对象
     */
    @Override
    public Numberofperiods save(Numberofperiods numberofperiods) {
        return numberofperiodsRepository.save(numberofperiods);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return numberofperiodsRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }

    /**
     * 根据条件查询期数数据
     */
    @Override
    public Optional<Numberofperiods> findOne(Example<Numberofperiods> example) {

        return numberofperiodsRepository.findOne(example);
    }


    /**
     * 自定义分页不带查询条件查询列表数据
     */
    @Override
    public Page<NumberOfPeriodsPageListBean> getCustomNoPageList() {
        // 创建分页对象
        try {
            PageRequest page = PageSort.pageRequest();
            Page<NumberOfPeriodsPageListBean> customPageList = numberofperiodsRepository.getCustomNoPageList(page);
            //List<NumberOfPeriodsPageListBean> content = Conver.toBeanList(customPageList.getContent(), NumberOfPeriodsPageListBean.class);

            return customPageList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 自定义分页带查询条件查询列表数据
     */
    public Page<NumberOfPeriodsPageListBean> getCustomPageList(String rName, Byte stauts) {
        PageRequest pageRequest = PageSort.pageRequest();
        Page<NumberOfPeriodsPageListBean> customPageList = numberofperiodsRepository.getCustomPageList(rName, stauts, pageRequest);
        return customPageList;
    }

}