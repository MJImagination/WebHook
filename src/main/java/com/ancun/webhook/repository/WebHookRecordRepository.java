package com.ancun.webhook.repository;


import com.ancun.webhook.model.WebHookRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/15
 */
@Repository
public interface WebHookRecordRepository extends JpaRepository<WebHookRecord,Long> ,JpaSpecificationExecutor<WebHookRecord> {
    /**
     * 根据recordNo查找
     */
    WebHookRecord findByRecordNo(String recordNo);

}
