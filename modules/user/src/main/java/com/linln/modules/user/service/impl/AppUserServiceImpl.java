package com.linln.modules.user.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.component.shiro.ShiroUtil;
import com.linln.modules.Equipment.domain.Card;
import com.linln.modules.Equipment.domain.CardThenumberofbuildings;
import com.linln.modules.Equipment.domain.CardUser;
import com.linln.modules.Equipment.repository.CardRepository;
import com.linln.modules.Equipment.repository.CardThenumberofbuildingsRepository;
import com.linln.modules.Equipment.repository.CardUserRepository;
import com.linln.modules.residentialQuarters.domain.Room;
import com.linln.modules.residentialQuarters.domain.UserRoomNumberOfBuildings;
import com.linln.modules.residentialQuarters.repository.RoomRepository;
import com.linln.modules.residentialQuarters.repository.UserRoomNumberOfBuildingsRepository;
import com.linln.modules.user.Bean.AppUserBean;
import com.linln.modules.user.Bean.AppUserByMobileBean;
import com.linln.modules.user.Bean.AppUserPageBean;
import com.linln.modules.user.domain.AppUser;
import com.linln.modules.user.repository.AppUserRepository;
import com.linln.modules.user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/12
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UserRoomNumberOfBuildingsRepository userRoomNumberOfBuildingsRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardThenumberofbuildingsRepository cardThenumberofbuildingsRepository;
    @Autowired
    private CardUserRepository cardUserRepository;
    /**
     * 根据电话或者用户名查询列表数据
     *
     * @param status
     * @param userNameOrMobile
     * @return
     */
 public  Page<AppUserPageBean> getDatePage(Byte status, String userNameOrMobile){
     // 创建分页对象
     PageRequest page = PageSort.pageRequest();
     return appUserRepository.getDatePage(status,userNameOrMobile,page);
 };
    /**
     * 没有查询条件的数据列表
     * @param status
     * @return
     */
   public Page<AppUserPageBean> getDateNoPage(Byte status){
       // 创建分页对象
       PageRequest page = PageSort.pageRequest();
       return appUserRepository.getDateNoPage(status,page);
   };

    /**
     * 通过房间号和栋数查询用户ID（判断一个房间里是否含有有一个业主）
     *
     * @param roomNumber
     * @param numberOfBuildingsId
     * @return
     */
    public Long getDataIdByRoomNumberAndNumberOfBuildingsId(int roomNumber, Long numberOfBuildingsId) {
        return appUserRepository.getDataIdByRoomNumberAndNumberOfBuildingsId(roomNumber, numberOfBuildingsId);
    }

    ;

    /**
     * 保存数据
     *
     * @param appUserBean
     */
    @Transactional
    public void saveData(AppUserBean appUserBean) {

        //构建AppUser对象
        AppUser appUser = buildAppUser(appUserBean);

        //保存用户信息
        appUserRepository.save(appUser);
        //构建Room对象
        Room room = buildRoom(appUserBean);
        //保存房间信息
        roomRepository.save(room);
        //构建UserRoomNumberOfBuildings对象
        UserRoomNumberOfBuildings userRoomNumberOfBuildings = bulidUserRoomNumberOfBuildings(appUserBean, room, appUser);
        //保存用户期数栋数关联表的数据
        userRoomNumberOfBuildingsRepository.save(userRoomNumberOfBuildings);

        if (StrUtil.isNotBlank(appUserBean.getSerialNumber()) && appUserBean.getCardType() != null) {
            //有门禁卡数据
            //构建card对象
            Card card = buildCard(appUserBean);
            //保存门禁卡信息
            cardRepository.save(card);
            //构建cardUser对象
            CardUser cardUser = buildCardUser(card, appUser);
            cardUserRepository.save(cardUser);
            //构建cardThenumberofbuildings对象
            CardThenumberofbuildings cardThenumberofbuildings = bulidCardThenumberofbuildings(card, appUserBean);
            cardThenumberofbuildingsRepository.save(cardThenumberofbuildings);

        }

    }

    ;

    /**
     * 通过电话号码查询数据
     *
     * @param mobile
     * @return
     */
    public AppUserByMobileBean getDataByMobile(String mobile) {
        return appUserRepository.getDataByMobile(mobile);
    }

    ;

    /**
     * 查询所有的用户信息
     */

    public List<AppUser> getAllData() {
        return appUserRepository.findAll();
    }

    ;

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    @Override
    @Transactional
    public AppUser getById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<AppUser> getPageList(Example<AppUser> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return appUserRepository.findAll(example, page);
    }

    /**
     * 保存数据
     *
     * @param appUser 实体对象
     */
    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return appUserRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }

    /**
     * 构建cardThenumberofbuildings对象
     */
    private CardThenumberofbuildings bulidCardThenumberofbuildings(Card card, AppUserBean appUserBean) {
        CardThenumberofbuildings cardThenumberofbuildings = new CardThenumberofbuildings();
        cardThenumberofbuildings.setCid(card.getId());
        cardThenumberofbuildings.setCreateDate(DateUtil.date(new Date()));
        cardThenumberofbuildings.setStatus(Byte.parseByte("1"));
        cardThenumberofbuildings.setTid(Long.valueOf(appUserBean.getNumberOfBuildingsId()));
        return cardThenumberofbuildings;
    }

    /**
     * 构建cardUser对象
     */
    private CardUser buildCardUser(Card card, AppUser appUser) {
        CardUser cardUser = new CardUser();
        cardUser.setCid(card.getId());
        cardUser.setUid(appUser.getId());
        cardUser.setCreateDate(DateUtil.date(new Date()));
        cardUser.setStatus(Byte.parseByte("1"));
        return cardUser;
    }

    /**
     * 构建card对象
     */
    private Card buildCard(AppUserBean appUserBean) {
        Card card = new Card();

        card.setCreateDate(DateUtil.date(new Date()));
        card.setEndDate(DateUtil.parse(appUserBean.getEndDate()));
        card.setSerialNumber(appUserBean.getSerialNumber());
        card.setType(appUserBean.getCardType());
        card.setStatus(Byte.parseByte("1"));
        return card;
    }

    /**
     * 构建Room对象
     */
    private Room buildRoom(AppUserBean appUserBean) {
        Room room = new Room();
        room.setRoomNumber(Integer.parseInt(appUserBean.getRoomNumber()));
        room.setStatus(Byte.parseByte("1"));
        return room;
    }

    /**
     * 构建UserRoomNumberOfBuildings对象
     */

    private UserRoomNumberOfBuildings bulidUserRoomNumberOfBuildings(AppUserBean appUserBean, Room room, AppUser appUser) {
        UserRoomNumberOfBuildings userRoomNumberOfBuildings = new UserRoomNumberOfBuildings();

        userRoomNumberOfBuildings.setIsChecked(Byte.parseByte("0"));

        userRoomNumberOfBuildings.setIsDefault(Byte.parseByte("1"));
        userRoomNumberOfBuildings.setNumberOfBuildingsId(Long.valueOf(appUserBean.getNumberOfBuildingsId()));
        userRoomNumberOfBuildings.setRoomId(room.getId());
        userRoomNumberOfBuildings.setStatus(Byte.parseByte("1"));
        userRoomNumberOfBuildings.setUId(appUser.getId());
        return userRoomNumberOfBuildings;
    }

    /**
     * 构建AppUser对象
     */
    private AppUser buildAppUser(AppUserBean appUserBean) {
        AppUser appUser = new AppUser();

        appUser.setCreateDate(DateUtil.date(new Date()));
        appUser.setIsOnline(Byte.valueOf("0"));
        appUser.setLastLoginTime(DateUtil.date(new Date()));
        appUser.setMobilePhone(appUserBean.getMobilePhone());
        appUser.setName(appUserBean.getName());
        //默认密码
        String salt = ShiroUtil.getRandomSalt();
        String encrypt = ShiroUtil.encrypt("123456", salt);
        appUser.setPassWord(encrypt);
        appUser.setSex(Byte.parseByte("0"));
        appUser.setSnCode(RandomUtil.randomNumbers(4));
        if (StrUtil.isNotBlank(appUserBean.getStandbyTelephone())) {
            appUser.setStandbyTelephone(appUserBean.getStandbyTelephone());
        } else {
            appUser.setStandbyTelephone(appUser.getMobilePhone());
        }
        appUser.setStatus(Byte.parseByte("1"));
        appUser.setUserName(appUserBean.getUserName());
        appUser.setUserType(appUserBean.getUserType());

        return appUser;
    }
}