package com.hzsmk.talentcode.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验,用户需要拥有value的权限才能访问接口
 *
 * @author jiangjh
 * @date 2020/3/24 17:32
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuth {
    /**
     * 权限auth
     *
     * @return
     */
    String value() default "";
}
