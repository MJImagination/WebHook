package com.ancun.webhook.service;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.WebHookTransport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/5
 */
public interface WebHookTransportService {
    /**
     * 增加
     *
     * @param webHookTransport
     * @return
     */
    WebHookTransport createdWebHookTransport(WebHookTransport webHookTransport);

    /**
     * 删除
     *
     * @param id
     */
    boolean deleteById(Long id);

    /**
     * 更新
     *
     * @param webHookTransport
     * @return
     */
    WebHookTransport updateWebHookTransport(WebHookTransport webHookTransport);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    WebHookTransport findOneById(Long id);


    /**
     * 分页模糊查询
     *
     * @return
     */
    Page<WebHookTransport> findPageList(Pageable pageable, WebHookTransport webHookTransport, JPATimeVO jpaTimeVO,
                                        LinkedHashMap<String, String> orders);
}
