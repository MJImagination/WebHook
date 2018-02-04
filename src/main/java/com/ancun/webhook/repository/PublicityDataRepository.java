package com.ancun.webhook.repository;


import com.ancun.webhook.model.PublicityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/15
 */
@Repository
public interface PublicityDataRepository extends JpaRepository<PublicityData,Long> ,JpaSpecificationExecutor<PublicityData> {
    /**
     * 通过保全号查询
     * @param userName
     * @return
     */
    PublicityData findByrecoredNo(String userName);
}
