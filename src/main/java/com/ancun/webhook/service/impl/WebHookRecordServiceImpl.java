package com.ancun.webhook.service.impl;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.repository.WebHookRecordRepository;
import com.ancun.webhook.service.WebHookRecordService;
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
public class WebHookRecordServiceImpl implements WebHookRecordService {
    @Autowired
    private WebHookRecordRepository publicityDataRepository;

    @Override
    public WebHookRecord createdWebHookRecord(WebHookRecord publicityData) {
        return publicityDataRepository.save(publicityData);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            publicityDataRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public WebHookRecord updateWebHookRecord(WebHookRecord publicityData) {
        if (publicityData.getId() != null) {
            return publicityDataRepository.saveAndFlush(publicityData);
        }
        return null;
    }

    @Override
    public WebHookRecord findOneById(Long id) {
        Optional<WebHookRecord> publicityData = publicityDataRepository.findById(id);
        if (publicityData.isPresent()) {
            return publicityData.get();
        }
        return null;
    }

    @Override
    public Page<WebHookRecord> findPageList(Pageable pageable, WebHookRecord publicityData, JPATimeVO jpaTimeVO
            , LinkedHashMap<String, String> orders) {
        return publicityDataRepository.findAll(JPAUtil.getSpecificationByObj(publicityData, null
                , jpaTimeVO, orders), pageable);
    }
}
