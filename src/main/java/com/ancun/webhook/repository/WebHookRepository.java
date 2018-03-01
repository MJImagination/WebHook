package com.ancun.webhook.repository;



import com.ancun.webhook.model.WebHook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/15
 */
@Repository
public interface WebHookRepository extends JpaRepository<WebHook,Long> ,JpaSpecificationExecutor<WebHook> {
    /**
     * 更具状态查询所有数据
     *
     * @param status
     * @return
     */
    List<WebHook> findAllByStatus(Integer status);

}
