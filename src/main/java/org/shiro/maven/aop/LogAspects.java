package org.shiro.maven.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.shiro.maven.annotation.LogAnnotation;
import org.shiro.maven.common.utli.HttpContextUtils;
import org.shiro.maven.common.utli.IpUtils;
import org.shiro.maven.po.Log;
import org.shiro.maven.service.LogService;
import org.shiro.maven.service.PersoninfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspects {


    @Autowired
    private LogService logService;

    @Pointcut("@annotation(org.shiro.maven.annotation.LogAnnotation)")
    public void logPointCut(){
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint,long time){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = new Log();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if(log != null){
            log.setModule(logAnnotation.module());
            log.setOperation(logAnnotation.operation());
        }

        //请求方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className+"."+methodName+"()");
        //设置ip地址
        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(httpServletRequest));
        log.setTime(time);
        log.setCreate_date(new Date());
        logService.saveLog(log);
    }





}
