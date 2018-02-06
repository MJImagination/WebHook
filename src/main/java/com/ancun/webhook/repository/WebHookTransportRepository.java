package com.ancun.webhook.repository;


import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.model.WebHookTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/15
 */
@Repository
public interface WebHookTransportRepository extends JpaRepository<WebHookTransport, Long>, JpaSpecificationExecutor<WebHookTransport> {

}
