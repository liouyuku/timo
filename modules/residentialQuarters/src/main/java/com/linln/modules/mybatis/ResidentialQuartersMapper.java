package com.linln.modules.mybatis;

import com.linln.modules.residentialQuarters.domain.ResidentialQuarters;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: wusq
 * @date: 2018/11/28
 */
@Mapper
public interface ResidentialQuartersMapper {

    /**
     * 保存
     * @param user
     * @return
     */
    int save(ResidentialQuarters user);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(String[] ids);
}
