package com.xgcd.lab.aop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class OperateAdvice {

    @Pointcut("execution(* com.xgcd.lab.controller.*.*(..))")
    private void logAdvicePointCut() {
    }

    @Before("logAdvicePointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        // Model info
        Class declaringType = signature.getDeclaringType();
        Annotation annotation = declaringType.getAnnotation(Api.class);
        Api api = (Api) annotation;
        String[] apiTags = api.tags();
        System.out.println("Api value is " + apiTags[0]);// Api value is Web启动测试1

        // Method info
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ApiOperation methodAnnotation = method.getAnnotation(ApiOperation.class);
        String value = methodAnnotation.value();
        System.out.println("ApiOperation value is " + value);// ApiOperation value is sayHello

    }
}
