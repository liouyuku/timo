package com.linln.modules.system.repository;

import com.linln.modules.system.domain.ActionLog;
import com.linln.modules.system.domain.SysUserResidentialQuarters;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysUserResidentialQuartersRepository extends JpaRepository<SysUserResidentialQuarters, Long> {
    /**
     * 通过用户id删除数据
     */
    @Modifying
    @Transactional
    @Query("update  SysUserResidentialQuarters s set  s.status=3 WHERE  s.sysUserId=:sysUserId and s.status=1")
    void deleteBySysUserId(@Param("sysUserId") Long sysUserId);
    /**
     * 通过用户的id查询对应的小区的id
     */
    @Query("SELECT s.residentialQuartersId from SysUserResidentialQuarters s where s.sysUserId=:uId and s.status=1")
    List<Long> getByUid(@Param("uId") Long uId);
}
