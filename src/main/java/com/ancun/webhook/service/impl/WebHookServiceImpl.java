package com.ancun.webhook.service.impl;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.repository.WebHookRepository;
import com.ancun.webhook.service.WebHookService;
import com.ancun.webhook.utils.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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

    @Override
    public WebHook createdWebHook(WebHook webHook) {
        return webHookRepository.save(webHook);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            webHookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public WebHook updateWebHook(WebHook webHook) {
        if (webHook.getId() != null) {
            return webHookRepository.saveAndFlush(webHook);
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
}
