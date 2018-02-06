package com.ancun.webhook.service.impl;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.WebHookTransport;
import com.ancun.webhook.repository.WebHookTransportRepository;
import com.ancun.webhook.service.WebHookTransportService;
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
public class WebHookTransportServiceImpl implements WebHookTransportService {
    @Autowired
    private WebHookTransportRepository webHookTransportRepository;

    @Override
    public WebHookTransport createdWebHookTransport(WebHookTransport webHookTransport) {
        return webHookTransportRepository.save(webHookTransport);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            webHookTransportRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public WebHookTransport updateWebHookTransport(WebHookTransport webHookTransport) {
        if (webHookTransport.getId() != null) {
            return webHookTransportRepository.saveAndFlush(webHookTransport);
        }
        return null;
    }

    @Override
    public WebHookTransport findOneById(Long id) {
        Optional<WebHookTransport> webHookTransport = webHookTransportRepository.findById(id);
        if (webHookTransport.isPresent()) {
            return webHookTransport.get();
        }
        return null;
    }

    @Override
    public Page<WebHookTransport> findPageList(Pageable pageable, WebHookTransport webHookTransport, JPATimeVO jpaTimeVO
            , LinkedHashMap<String, String> orders) {
        return webHookTransportRepository.findAll(JPAUtil.getSpecificationByObj(webHookTransport, null
                , jpaTimeVO, orders), pageable);
    }
}
