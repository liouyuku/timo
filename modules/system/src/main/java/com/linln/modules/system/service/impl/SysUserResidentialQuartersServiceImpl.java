package com.linln.modules.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.linln.modules.system.domain.SysUserResidentialQuarters;
import com.linln.modules.system.repository.SysUserResidentialQuartersRepository;
import com.linln.modules.system.service.SysUserResidentialQuartersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class SysUserResidentialQuartersServiceImpl implements SysUserResidentialQuartersService {
    @Autowired
    private SysUserResidentialQuartersRepository sysUserResidentialQuartersRepository;
    /**
     * 通过用户的id查询对应的小区的id
     */
    public List<Long> getByUid(Long uId){
        return  sysUserResidentialQuartersRepository.getByUid(uId);
    };

    /**
     * 用户绑定小区业务
     */

    @Transactional
    public void bindresidentialQuarters(Long sysUserId, HashSet<Long> residentialQuartersId) throws Exception{
        //通过用户id删除数据
        sysUserResidentialQuartersRepository.deleteBySysUserId(sysUserId);
        Iterator<Long> iterator = residentialQuartersId.iterator();
        while (iterator.hasNext()) {
            SysUserResidentialQuarters sysUserResidentialQuarters = new SysUserResidentialQuarters();
            sysUserResidentialQuarters.setSysUserId(sysUserId);
            sysUserResidentialQuarters.setCreateDate(DateUtil.date(new Date()));
            Long next = iterator.next();
            sysUserResidentialQuarters.setResidentialQuartersId(next);
            // 保存数据
            sysUserResidentialQuartersRepository.save(sysUserResidentialQuarters);
        }



    }


    ;;
}
