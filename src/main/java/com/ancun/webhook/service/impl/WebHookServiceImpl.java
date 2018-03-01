package com.ancun.webhook.service.impl;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.enums.WebHookEnum;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.redis.redisImpl.RedisWebHook;
import com.ancun.webhook.repository.WebHookRepository;
import com.ancun.webhook.service.WebHookService;
import com.ancun.webhook.utils.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/5
 */
@Service
public class WebHookServiceImpl implements WebHookService {
    @Autowired
    private WebHookRepository webHookRepository;

    @Autowired
    private RedisWebHook redisWebHook;

    @Resource(name = "hashOperations")
    private HashOperations<String, String, String> hashOperations;

    private static final String webHookRedisKey = "webHook";

    @Override
    public WebHook createdWebHook(WebHook webHook) {
        WebHook tempWebHook = webHookRepository.save(webHook);
        //增加到reids中
        if (tempWebHook != null && tempWebHook.getStatus() == WebHookEnum.STATUS_ON.getValue()) {
            hashOperations.put(webHookRedisKey, String.valueOf(tempWebHook.getPartnerId()), tempWebHook.getCallBackUrl());
        }
        return tempWebHook;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            WebHook tempWebHook = findOneById(id);
            hashOperations.delete(webHookRedisKey, String.valueOf(tempWebHook.getPartnerId()), tempWebHook.getCallBackUrl());
            webHookRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public WebHook updateWebHook(WebHook webHook) {
        if (webHook.getId() != null) {
            WebHook tempWebHook = webHookRepository.saveAndFlush(webHook);
            //更新reids
            if (tempWebHook != null && tempWebHook.getStatus() == WebHookEnum.STATUS_ON.getValue()) {
                hashOperations.put(webHookRedisKey, String.valueOf(tempWebHook.getPartnerId()), tempWebHook.getCallBackUrl());
            } else if (tempWebHook != null && tempWebHook.getStatus() == WebHookEnum.STATUS_OFF.getValue()) {
                hashOperations.delete(webHookRedisKey, String.valueOf(tempWebHook.getPartnerId()), tempWebHook.getCallBackUrl());
            }
            return tempWebHook;
        }
        return null;
    }

    @Override
    public WebHook findOneById(Long id) {
        Optional<WebHook> webHook = webHookRepository.findById(id);
        if (webHook.isPresent()) {
            return webHook.get();
        }
        return null;
    }

    @Override
    public Page<WebHook> findPageList(Pageable pageable, WebHook webHook, JPATimeVO jpaTimeVO
            , LinkedHashMap<String, String> orders) {
        return webHookRepository.findAll(JPAUtil.getSpecificationByObj(webHook, null
                , jpaTimeVO, orders), pageable);
    }

    @Override
    public List<WebHook> findAllWebHook(Integer status) {
        return webHookRepository.findAllByStatus(status);
    }
}
