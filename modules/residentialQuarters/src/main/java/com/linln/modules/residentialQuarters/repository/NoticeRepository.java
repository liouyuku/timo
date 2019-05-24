package com.linln.modules.residentialQuarters.repository;


import com.linln.modules.residentialQuarters.bean.NoticeDataBean;
import com.linln.modules.residentialQuarters.domain.Notice;
import com.linln.modules.system.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 小懒虫
 * @date 2019/05/10
 */
public interface NoticeRepository extends BaseRepository<Notice, Long> {
    /**
     * 有条件查询的分页数据
     *
     * @param status
     * @param title
     * @return
     */
    @Query("select new com.linln.modules.residentialQuarters.bean.NoticeDataBean(n.id,n.title,n.content,u.username,n.createDate,n.endDate,n.buildType) from Notice n left join User u on n.author=u.id where n.status=:status and u.status=:status and n.title=:title")
    Page<NoticeDataBean> getDataPageList(@Param("status") Byte status, @Param("title") String title, Pageable pageable);

    /**
     * 无条件查询分页数据
     *
     * @return
     */
    @Query("select new com.linln.modules.residentialQuarters.bean.NoticeDataBean(n.id,n.title,n.content,u.username,n.createDate,n.endDate,n.buildType) from Notice n left join User u on n.author=u.id where n.status=:status and u.status=:status")
    Page<NoticeDataBean> getDataNoPageList(@Param("status") Byte status, Pageable pageable);
}