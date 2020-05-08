package com.lmaye.spring.boot.cache.guava.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * -- 缓存注解
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/5/9 0:01 星期六
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Inherited
@Documented
public @interface Cache {
    /**
     * 键前缀
     * - 缓存Key前缀(eg: User-xxx)
     *
     * @return String
     */
    String value() default "";

    /**
     * 键标签
     * - 传入值作为缓存Key(eg: User-ID&Name)
     *
     * @return String[]
     */
    String[] tags() default "";

    /**
     * 描述
     *
     * @return String
     */
    String description() default "";

    /**
     * 过期时间(单位: 毫秒)
     *
     * @return int
     */
    int expiredTime() default -1;
}
