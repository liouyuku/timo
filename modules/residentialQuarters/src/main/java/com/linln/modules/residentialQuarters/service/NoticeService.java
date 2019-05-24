package com.linln.modules.residentialQuarters.service;


import com.linln.common.enums.StatusEnum;
import com.linln.modules.residentialQuarters.bean.NoticeBean;
import com.linln.modules.residentialQuarters.bean.NoticeDataBean;
import com.linln.modules.residentialQuarters.domain.Notice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2019/05/10
 */
public interface NoticeService {
    /**
     * 有条件查询的分页数据
     *
     * @param status
     * @param title
     * @return
     */
    Page<NoticeDataBean> getDataPageList(Byte status, String title);

    /**
     * 无条件查询分页数据
     *
     * @return
     */
    Page<NoticeDataBean> getDataNoPageList(Byte status);

    /**
     * 保存数据
     *
     * @param noticeBean
     */
    void saveData(NoticeBean noticeBean);

    /**
     * 获取分页列表数据
     *
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Notice> getPageList(Example<Notice> example);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     */
    Notice getById(Long id);

    /**
     * 保存数据
     *
     * @param notice 实体对象
     */
    Notice save(Notice notice);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}