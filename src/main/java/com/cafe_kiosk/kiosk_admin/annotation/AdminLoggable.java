package com.cafe_kiosk.kiosk_admin.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminLoggable {
    String actionType() default "";
    String targetTable() default "";
    String description() default "";  // 작업 상세 설명 추가
}
