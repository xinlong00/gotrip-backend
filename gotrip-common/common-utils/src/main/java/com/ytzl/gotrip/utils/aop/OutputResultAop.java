package com.ytzl.gotrip.utils.aop;

import com.ytzl.gotrip.utils.common.DtoUtil;
import com.ytzl.gotrip.utils.common.ErrorCode;
import com.ytzl.gotrip.utils.exception.GotripException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class OutputResultAop {

    private static Logger LOG = LoggerFactory.getLogger(OutputResultAop.class);

    @Pointcut("execution(* com.ytzl.gotrip.controller.*.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint jp){
        Object result = null;
        try {
            result = jp.proceed();
        } catch (GotripException e) {
            outputErrorLog(jp,e);
            return DtoUtil.returnFail(e.getMessage(),e.getErrorCode());
        }catch (Throwable throwable){
            outputErrorLog(jp,throwable);
            return DtoUtil.returnFail(throwable.getMessage(),
                    ErrorCode.AUTH_UNKNOWN);
        }
        return result;
    }

    /**
     * 输出异常信息
     * @param jp
     */
    public void outputErrorLog(ProceedingJoinPoint jp,Throwable throwable){
        StringBuffer sb = new StringBuffer();
        sb.append(" exec class[").append(jp.getTarget().getClass().getName())
                .append("] --> method[")
                .append(jp.getSignature().getName())
                .append("] throwable:{}");
        LOG.error(sb.toString(), throwable.getMessage());
    }
}
