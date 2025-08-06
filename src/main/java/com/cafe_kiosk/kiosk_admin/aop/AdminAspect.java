package com.cafe_kiosk.kiosk_admin.aop;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.log.AdminLog;
import com.cafe_kiosk.kiosk_admin.service.AdminLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAspect {

    private final AdminLogService adminLogService;

    private String getCurrentAdminId() {
        // Spring Security 기반이라면 이렇게 가져올 수 있어
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private String extractTargetTable(String className) {
        // "AdminMenuService" → "menu"
        if (className.startsWith("Admin")) {
            className = className.substring("Admin".length());
        }

        if (className.endsWith("Service")) {
            className = className.substring(0, className.length() - "Service".length());
        }

        return className.toLowerCase();
    }


    private String extractActionType(String methodName) {
        if (methodName.startsWith("insert") || methodName.startsWith("add")) return "insert";
        if (methodName.startsWith("update") || methodName.startsWith("modify")) return "update";
        if (methodName.startsWith("delete") || methodName.startsWith("remove")) return "delete";
        return "unknown";
    }

    private Object extractTargetId(Object[] args) {
        // 1) DTO 내부 get*Id() 메서드 먼저 찾기
        for (Object arg : args) {
            if (arg == null) continue;

            try {
                List<Method> idMethods = Arrays.stream(arg.getClass().getMethods())
                    .filter(m -> m.getName().matches("get.*Id") && m.getParameterCount() == 0)
                    .sorted(Comparator.comparingInt(m -> {
                        if (m.getName().equals("getMenuId")) return 0;
                        if (m.getName().equals("getOptionId")) return 1;
                        if (m.getName().equals("getCategoryId")) return 2;
                        return 10;
                    }))
                    .collect(Collectors.toList());

                for (Method method : idMethods) {
                    Object value = method.invoke(arg);
                    log.info("extractTargetId: Found id from method {} = {}", method.getName(), value);
                    if (value != null && (value instanceof Integer || value instanceof String || value instanceof Long)) {
                        if (value instanceof Long) return ((Long) value).intValue();
                        return value;
                    }
                }
            } catch (Exception e) {
                // 무시하거나 로깅
            }
        }
        // 2) DTO에서 못 찾았으면 기본 타입 먼저 찾기
        for (Object arg : args) {
            if (arg instanceof Integer || arg instanceof String) {
                return arg;
            }
            if (arg instanceof Long) {
                return ((Long) arg).intValue();
            }
        }
        return null;
    }


    @Pointcut("execution(* com.cafe_kiosk.kiosk_admin.service..*.insert*(..)) || " +
            "execution(* com.cafe_kiosk.kiosk_admin.service..*.update*(..)) || " +
            "execution(* com.cafe_kiosk.kiosk_admin.service..*.delete*(..)) || " +
            "execution(* com.cafe_kiosk.kiosk_admin.service..*.add*(..)) || " +
            "execution(* com.cafe_kiosk.kiosk_admin.service..*.remove*(..)) || " +
            "execution(* com.cafe_kiosk.kiosk_admin.service..*.modify*(..))")
    private void allAdminModifyingMethods() {}

    @Pointcut("!within(com.cafe_kiosk.kiosk_admin.service.AdminLogService)")
    private void excludeAdminLogService() {}

    @Pointcut("allAdminModifyingMethods() && excludeAdminLogService()")
    private void adminModifyingMethods() {}

    @AfterReturning(pointcut = "adminModifyingMethods()", returning = "result")
    public void logAdminAction(JoinPoint joinPoint, Object result) {
        // Retrieve annotation from the method
        MethodSignature signature = (org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AdminLoggable annotation = method.getAnnotation(AdminLoggable.class);

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        String actionType = annotation.actionType().isEmpty() ? extractActionType(methodName) : annotation.actionType(); // insert / update / delete
        String targetTable = extractTargetTable(className);
        String description = annotation.description();
        Object rawTargetId = extractTargetId(joinPoint.getArgs());
        String targetId = (rawTargetId != null) ? rawTargetId.toString() : "NOT_FOUND";
        String adminId = getCurrentAdminId();

        AdminLog adminLog = AdminLog.builder()
                .actionType(actionType)
                .targetTable(targetTable)
                .targetId(targetId)
                .adminId(adminId)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();

        log.info("실행된 메소드:{}", methodName);
        log.info("방금 로딩 로그:{} ",adminLog);
        adminLogService.insertAdminLog(adminLog);
    }

}
