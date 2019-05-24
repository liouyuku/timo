package com.linln.modules.residentialQuarters.service.impl;


import cn.hutool.core.date.DateUtil;
import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.component.shiro.ShiroUtil;
import com.linln.modules.residentialQuarters.bean.NoticeBean;
import com.linln.modules.residentialQuarters.bean.NoticeDataBean;
import com.linln.modules.residentialQuarters.domain.Notice;
import com.linln.modules.residentialQuarters.repository.NoticeRepository;
import com.linln.modules.residentialQuarters.service.NoticeService;
import com.linln.modules.system.domain.User;
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
 * @date 2019/05/10
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;
    /**
     * 有条件查询的分页数据
     *
     * @param status
     * @param title
     * @return
     */
    public Page<NoticeDataBean> getDataPageList(Byte status, String title){
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return noticeRepository.getDataPageList(status,title,page);
    };
    /**
     * 无条件查询分页数据
     * @return
     */
   public  Page<NoticeDataBean> getDataNoPageList(Byte status){
       // 创建分页对象
       PageRequest page = PageSort.pageRequest();
        return  noticeRepository.getDataNoPageList(status,page);
    };
    /**
     * 保存数据
     * @param noticeBean
     */
    public void saveData(NoticeBean noticeBean){
        //构建Notice对象
       Notice notice= buildNotice(noticeBean);

        noticeRepository.save(notice);
    };
    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Notice getById(Long id) {
        return noticeRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Notice> getPageList(Example<Notice> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return noticeRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param notice 实体对象
     */
    @Override
    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return noticeRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
    //构建notice对象
    private Notice buildNotice(NoticeBean noticeBean){
        //获取用户名
        User user = ShiroUtil.getSubject();
        Notice notice = new Notice();
        notice.setAuthor(user.getId());
        notice.setBuildType(Byte.parseByte(String.valueOf(noticeBean.getBuildType())));
        notice.setContent(noticeBean.getContent());
        notice.setCreateDate(DateUtil.date(new Date()));
        notice.setDurationDate(noticeBean.getDurationDate());
        notice.setEndDate(DateUtil.parse(noticeBean.getEndDate()));
        notice.setIsTop(Byte.parseByte("0"));
        notice.setRelationId(noticeBean.getRelationId());
        notice.setStatus(Byte.parseByte("1"));
        notice.setTitle(noticeBean.getTitle());
        return notice;
    }
}