package com.linln.modules.user.repository;

import com.linln.modules.system.repository.BaseRepository;
import com.linln.modules.user.Bean.AppUserByMobileBean;
import com.linln.modules.user.Bean.AppUserPageBean;
import com.linln.modules.user.domain.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 小懒虫
 * @date 2019/05/12
 */
public interface AppUserRepository extends BaseRepository<AppUser, Long> {
    /**
     * 根据电话或者用户名查询列表数据
     *
     * @param status
     * @param userNameOrMobile
     * @return
     */
    @Query("SELECT new com.linln.modules.user.Bean.AppUserPageBean(u.id,u.userName,CONCAT(r.name,np.name,nb.name,room.roomNumber),u.createDate,u.mobilePhone ) from AppUser u " +
            "left join  UserRoomNumberOfBuildings urn on urn.uId=u.id " +
            "left join Room room on room.id=urn.roomId " +
            "left join NumberOfBuildings nb on nb.id=urn.numberOfBuildingsId " +
            "left join Numberofperiods np on np.id=nb.numberOfPeriodsId " +
            "left join ResidentialQuarters r on r.id=np.residentialQuartersId " +
            "where (u.mobilePhone=:userNameOrMobile or u.userName=:userNameOrMobile) and  nb.status=:status and room.status=:status and urn.status=:status and r.status=:status and np.status=:status  ")

    Page<AppUserPageBean> getDatePage(@Param("status") Byte status,@Param("userNameOrMobile") String userNameOrMobile,Pageable pageable);
    /**
     * 没有查询条件的数据列表
     * @param status
     * @return
     */
    @Query("SELECT new com.linln.modules.user.Bean.AppUserPageBean(u.id,u.userName,CONCAT(r.name,np.name,nb.name,room.roomNumber),u.createDate,u.mobilePhone ) from AppUser u " +
            "left join  UserRoomNumberOfBuildings urn on urn.uId=u.id " +
            "left join Room room on room.id=urn.roomId " +
            "left join NumberOfBuildings nb on nb.id=urn.numberOfBuildingsId " +
            "left join Numberofperiods np on np.id=nb.numberOfPeriodsId " +
            "left join ResidentialQuarters r on r.id=np.residentialQuartersId " +
            "where nb.status=:status and r.status=:status and urn.status=:status and room.status=:status and np.status=:status  ")
     Page<AppUserPageBean> getDateNoPage(@Param("status") Byte status, Pageable pageable);
    /**
     * 通过房间号和栋数查询用户ID（判断一个房间里是否含有有一个业主）
     *
     * @param roomNumber
     * @param numberOfBuildingsId
     * @return
     */
    @Query("SELECT u.id FROM AppUser u left join UserRoomNumberOfBuildings urn on urn.uId=u.id left join Room r on r.id=urn.roomId left join NumberOfBuildings nb on nb.id=urn.numberOfBuildingsId where nb.status=1 and r.status=1 and urn.status=1 and u.userType=1 and r.roomNumber=:roomNumber and nb.id=:numberOfBuildingsId")
    Long getDataIdByRoomNumberAndNumberOfBuildingsId(@Param("roomNumber") int roomNumber, @Param("numberOfBuildingsId") Long numberOfBuildingsId);

    /**
     * 通过电话号码查询数据
     *
     * @param mobile
     * @return
     */
    @Query("SELECT new com.linln.modules.user.Bean.AppUserByMobileBean(u.id,u.name,u.sex,u.userName,u.createDate,u.mobilePhone,u.standbyTelephone,u.isOnline,u.lastLoginTime,u.userType,u.snCode,u.regKey,u.regKeyType,u.osVersion,u.appVersion,u.phoneVersion,u.status) from AppUser u where u.mobilePhone=:mobile and u.status=1")
    AppUserByMobileBean getDataByMobile(@Param("mobile") String mobile);
}