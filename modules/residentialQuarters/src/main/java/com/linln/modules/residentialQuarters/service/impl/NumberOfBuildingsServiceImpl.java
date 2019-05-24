package com.linln.modules.residentialQuarters.service.impl;


import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsBean;
import com.linln.modules.residentialQuarters.bean.NumberOfBuildingsPageListBean;
import com.linln.modules.residentialQuarters.domain.NumberOfBuildings;
import com.linln.modules.residentialQuarters.repository.NumberOfBuildingsRepository;
import com.linln.modules.residentialQuarters.service.NumberOfBuildingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/07
 */
@Service
public class NumberOfBuildingsServiceImpl implements NumberOfBuildingsService {

    @Autowired
    private NumberOfBuildingsRepository numberOfBuildingsRepository;
    /**
     *通过期数的id查询栋数的信息
     * @param numberOfPeriodsId
     * @param status
     * @return
     */
    public List<NumberOfBuildingsBean> getDataBynumberOfPeriodsIdAndStatus(Long numberOfPeriodsId, Byte status){
        return  numberOfBuildingsRepository.getDataBynumberOfPeriodsIdAndStatus(numberOfPeriodsId,status);
    };
    /**
     * 通过期数的id,栋数的名称，栋数的编号，栋数的数据状态查询
     *
     * @param numberOfPeriodsId
     * @param numberOfBuildingsName
     * @param numberOfBuildingsNumber
     * @param status
     * @return
     */
  public NumberOfBuildings getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndNumberAndStatus(Long numberOfPeriodsId, String numberOfBuildingsNumber, Byte status){
        return numberOfBuildingsRepository.getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndNumberAndStatus(numberOfPeriodsId,numberOfBuildingsNumber,  status);
    };

    /**
     * 通过期数的id和栋数的名称，栋数的数据状态查询
     * @param numberOfPeriodsId
     * @param BuildingsName
     * @param stauts
     * @return
     */
    public NumberOfBuildings getDataByNumberofperiodsIdAndNumberOfBuildingsNameAndStatus(Long numberOfPeriodsId, String BuildingsName, Byte stauts){
        return numberOfBuildingsRepository.getDataByNumberofperiodsNameAndNumberOfBuildingsIdAndStatus(numberOfPeriodsId,BuildingsName,stauts);
    };

    /**
     * 根据状态和小区的名称查询列表数据
     * @param status
     * @param rName
     * @return
     */
    public Page<NumberOfBuildingsPageListBean> findPageList(Byte status,String rName){
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return numberOfBuildingsRepository.findPageList(status,rName,page);
    };
    /**
     * 没有条件查询列表数据
     */
 public   Page<NumberOfBuildingsPageListBean> findNoPageList(Byte status){
     // 创建分页对象
     PageRequest page = PageSort.pageRequest();
     return numberOfBuildingsRepository.findNoPageList(status,page);
 };
    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public NumberOfBuildings getById(Long id) {
        return numberOfBuildingsRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<NumberOfBuildings> getPageList(Example<NumberOfBuildings> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return numberOfBuildingsRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param numberOfBuildings 实体对象
     */
    @Override
    public NumberOfBuildings save(NumberOfBuildings numberOfBuildings) {
        return numberOfBuildingsRepository.save(numberOfBuildings);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return numberOfBuildingsRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}