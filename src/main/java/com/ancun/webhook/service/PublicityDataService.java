package com.ancun.webhook.service;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.model.PublicityData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/1/15
 */
public interface PublicityDataService {
    /**
     * 增加
     * @param publicityData
     * @return
     */
    PublicityData createdPublicityData(PublicityData publicityData);

    /**
     * 删除
     * @param id
     */
    boolean deleteById(Long id);

    /**
     * 更新
     * @param publicityData
     * @return
     */
    PublicityData updatePublicityData(PublicityData publicityData);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    PublicityData findOneById(Long id);


    /**
     * 分页模糊查询
     * @return
     */
    Page<PublicityData> findPageList(Pageable pageable, PublicityData publicityData, JPATimeVO jpaTimeVO,
                                     LinkedHashMap<String, String> orders);
}
