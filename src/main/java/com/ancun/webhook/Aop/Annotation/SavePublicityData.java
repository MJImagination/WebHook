package com.ancun.webhook.Aop.Annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/1/15
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SavePublicityData {
    String value() default "";
}