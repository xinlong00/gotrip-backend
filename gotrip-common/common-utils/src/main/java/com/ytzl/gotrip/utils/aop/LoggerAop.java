package com.ytzl.gotrip.utils.aop;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 日志打印
 *
 * @author sam
 */
@Component
@Aspect
@Order(2)
public class LoggerAop {

    private static Logger LOG = LoggerFactory.getLogger(LoggerAop.class);

    @Pointcut("execution(* com.ytzl.gotrip.controller.*.*(..))")
    public void pointCut() {
    }

//    @Before("pointCut()")
//    public void before(JoinPoint jp) {
//        //拼接日志输出内容
//        StringBuffer sb = new StringBuffer();
//        sb.append(" exec class[").append(jp.getTarget().getClass().getName())
//                .append("] --> method[")
//                .append(jp.getSignature().getName())
//                .append("] params:{}");
//        LOG.info(sb.toString(), JSON.toJSONString(jp.getArgs()));
//    }

//    @AfterReturning(value = "pointCut()", returning = "result")
//    public void after(JoinPoint jp, Object result) {
//        //拼接日志输出内容
//        StringBuffer sb = new StringBuffer();
//        sb.append(" exec class[").append(jp.getTarget().getClass().getName())
//                .append("] --> method[")
//                .append(jp.getSignature().getName())
//                .append("] result:{}");
//        LOG.info(sb.toString(), JSON.toJSONString(result));
//    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        StringBuffer sb = new StringBuffer();
        try {
            //前置
            sb.append(" exec class[").append(jp.getTarget().getClass().getName())
                    .append("] --> method[")
                    .append(jp.getSignature().getName())
                    .append("] params:{}");
            Object[] args = jp.getArgs();
            //序列化时过滤掉request和response
            List<Object> logArgs = LoggerAop.streamOf(args)
                    .filter(arg -> (!(arg instanceof HttpServletRequest)
                            && !(arg instanceof HttpServletResponse)
                            && !(arg instanceof MultipartFile)))
                    .collect(Collectors.toList());
            String argStr = JSON.toJSONString(logArgs);
            LOG.info(sb.toString(), argStr);
            Object result = jp.proceed();
            //后置
            //清空 sb
            sb.setLength(0);
            sb.append(" exec class[").append(jp.getTarget().getClass().getName())
                    .append("] --> method[")
                    .append(jp.getSignature().getName())
                    .append("] result:{}");
            LOG.info(sb.toString(), JSON.toJSONString(result));
            return result;
        } catch (Throwable throwable) {
            //清空 sb
            /*sb.setLength(0);
            sb.append(" exec class[").append(jp.getTarget().getClass().getName())
                    .append("] --> method[")
                    .append(jp.getSignature().getName())
                    .append("] throwable:{}");
            LOG.error(sb.toString(), throwable.getMessage());*/
            throw throwable;
        } finally {
            //清空 sb
            sb.setLength(0);
            sb.append(" exec class[").append(jp.getTarget().getClass().getName())
                    .append("] --> method[")
                    .append(jp.getSignature().getName())
                    .append("] over");
            LOG.info(sb.toString());
        }
    }

    public static <T> Stream<T> streamOf(T[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : Arrays.asList(array).stream();
    }
}
