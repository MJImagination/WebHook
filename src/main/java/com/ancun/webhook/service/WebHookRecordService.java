package com.ancun.webhook.service;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.WebHookRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/5
 */
public interface WebHookRecordService {
    /**
     * 增加
     * @param webHookRecord
     * @return
     */
    WebHookRecord createdWebHookRecord(WebHookRecord webHookRecord);

    /**
     * 删除
     * @param id
     */
    boolean deleteById(Long id);

    /**
     * 更新
     * @param webHookRecord
     * @return
     */
    WebHookRecord updateWebHookRecord(WebHookRecord webHookRecord);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    WebHookRecord findOneById(Long id);


    /**
     * 分页模糊查询
     * @return
     */
    Page<WebHookRecord> findPageList(Pageable pageable, WebHookRecord webHookRecord, JPATimeVO jpaTimeVO,
                                     LinkedHashMap<String, String> orders);
}
