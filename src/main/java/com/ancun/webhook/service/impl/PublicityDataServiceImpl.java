package com.ancun.webhook.service.impl;

import com.ancun.webhook.Aop.Annotation.SavePublicityData;
import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.PublicityData;
import com.ancun.webhook.repository.PublicityDataRepository;
import com.ancun.webhook.service.PublicityDataService;
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
public class PublicityDataServiceImpl implements PublicityDataService {
    @Autowired
    private PublicityDataRepository publicityDataRepository;

    @Override
    @SavePublicityData
    public PublicityData createdPublicityData(PublicityData publicityData) {
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
    public PublicityData updatePublicityData(PublicityData publicityData) {
        if (publicityData.getId() != null) {
            return publicityDataRepository.saveAndFlush(publicityData);
        }
        return null;
    }

    @Override
    public PublicityData findOneById(Long id) {
        Optional<PublicityData> publicityData = publicityDataRepository.findById(id);
        if (publicityData.isPresent()) {
            return publicityData.get();
        }
        return null;
    }

    @Override

    public Page<PublicityData> findPageList(Pageable pageable, PublicityData publicityData, JPATimeVO jpaTimeVO
            , LinkedHashMap<String, String> orders) {
        return publicityDataRepository.findAll(JPAUtil.getSpecificationByObj(publicityData, null
                , jpaTimeVO, orders), pageable);
    }
}
