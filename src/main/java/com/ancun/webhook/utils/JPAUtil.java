package com.ancun.webhook.utils;

import com.ancun.webhook.base.JPATimeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/2
 */
public class JPAUtil {
    public static final Logger logger = LoggerFactory.getLogger(JPAUtil.class);
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 通用模糊查询
     * @param t 查询实体类
     * @param equalFiledNames 设置equals查询字段，默认like查询
     * @param orders 设置排序字段 key:column vaue: asc/desc
     * @param <T>
     * @return
     */
    public static <T> Specification getSpecificationByObj(T t, String[] equalFiledNames, JPATimeVO jpaTimeVO
                                                          ,LinkedHashMap<String, String> orders) {
        return new Specification<T>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (Field field : t.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object val = field.get(t);
                        if (val != null && field.getAnnotation(Column.class)!=null) {
                            String filedName = field.getName();
                            boolean flag = true;
                            if (equalFiledNames != null) {
                                for (String name : equalFiledNames) {
                                    if (filedName.toUpperCase().equals(name.toUpperCase())) {
                                        predicates.add(criteriaBuilder.equal(root.get(field.getName()), val));
                                        flag = false;
                                        break;
                                    }
                                }
                            }
                            if (flag) {
                                if (Date.class == field.getType()) {
                                   continue;
                                } else {
                                    predicates.add(criteriaBuilder.like(root.get(field.getName()).as(String.class),
                                            "%" + val + "%"));
                                }
                            }
                        }

                    } catch (IllegalAccessException e) {
                        logger.error("反射出错：{}",e.getMessage(),e);
                    }
                }
                if(validJPATimeVO(jpaTimeVO)){
                    predicates.add(criteriaBuilder.between(root.get(jpaTimeVO.getTimeFiledName()).as(String.class),
                            simpleDateFormat.format(jpaTimeVO.getStartTime()),
                            simpleDateFormat.format(jpaTimeVO.getEndTime())));
                }
                if(orders != null){
                    List<Order> orderList = new ArrayList<>();
                    for (String s : orders.keySet()) {
                        if(orders.get(s).toUpperCase().equals("ASC")){
                            orderList.add(criteriaBuilder.asc(root.get(s)));
                        }else if(orders.get(s).toUpperCase().equals("DESC")){
                            orderList.add(criteriaBuilder.desc(root.get(s)));
                        }
                    }
                    criteriaQuery.orderBy(orderList);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static boolean validJPATimeVO(JPATimeVO jpaTimeVO){
        if(jpaTimeVO == null){
            return false;
        }else if(StringUtils.isEmpty(jpaTimeVO.getTimeFiledName())){
            return false;
        }else if(ObjectUtils.isEmpty(jpaTimeVO.getStartTime())){
            return false;
        }
        return true;
    }
}
