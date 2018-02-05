package com.ancun.webhook.Aop;

import com.ancun.webhook.model.PublicityData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: publicity保存切面
 * @Author: MJ
 * @Date: Created in 2018/1/15
 */
@Aspect
@Component
public class SavePublicityAspect {
    public static  final Logger logger = LoggerFactory.getLogger(SavePublicityAspect.class);

    @Autowired
//    private SimpMessagingTemplate messagingTemplate;


    @Pointcut("@annotation(com.ancun.webhook.Aop.Annotation.SavePublicityData)")
    public void SavePublicityPointCut() {
    }

    /**
     * 保存系统日志到数据库
     *
     * @param joinPoint 切点
     */
    @AfterReturning("SavePublicityPointCut()")
    public void sendWebSocketMessage(JoinPoint joinPoint) {
        // 获取目标方法体参数
        Object[] args = joinPoint.getArgs();
        PublicityData publicityData = (PublicityData) args[0];
        if(publicityData!=null){
            try {
//                callback(publicityData);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

//    @SendTo("/topic/callback")
//    public Object callback(PublicityData publicityData) throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        messagingTemplate.convertAndSend("/topic/callback", publicityData);
//        logger.info("广播消息到客户端，内容：{}",publicityData);
//        return "callback";
//    }

}
