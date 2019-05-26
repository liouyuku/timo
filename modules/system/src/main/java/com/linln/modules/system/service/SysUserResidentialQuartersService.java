package com.linln.modules.system.service;

import com.linln.modules.system.domain.SysUserResidentialQuarters;

import java.util.HashSet;
import java.util.List;

public interface SysUserResidentialQuartersService {
    /**
     *用户绑定小区业务
     */


   void  bindresidentialQuarters(Long sysUserId, HashSet<Long> residentialQuartersId) throws  Exception;
    /**
     * 通过用户的id查询对应的小区的id
     */
    List<Long> getByUid(Long uId);


}
