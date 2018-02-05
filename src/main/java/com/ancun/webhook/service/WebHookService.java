package com.ancun.webhook.service;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.WebHook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/5
 */
public interface WebHookService {
    /**
     * 增加
     * @param webHook
     * @return
     */
    WebHook createdWebHook(WebHook webHook);

    /**
     * 删除
     * @param id
     */
    boolean deleteById(Long id);

    /**
     * 更新
     * @param webHook
     * @return
     */
    WebHook updateWebHook(WebHook webHook);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    WebHook findOneById(Long id);


    /**
     * 分页模糊查询
     * @return
     */
    Page<WebHook> findPageList(Pageable pageable, WebHook webHook, JPATimeVO jpaTimeVO,
                                     LinkedHashMap<String, String> orders);
}
