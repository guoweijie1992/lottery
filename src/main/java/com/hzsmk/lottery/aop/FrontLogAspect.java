package com.hzsmk.lottery.aop;

import com.hzsmk.common.util.HttpUtil;
import com.hzsmk.common.util.StringUtil;
import com.hzsmk.common.util.TLocalHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端接口日志
 *
 * @author jiangjh
 * @date 2020/4/3 15:44
 */
@Order(1)
@Slf4j
@Component
@Aspect
public class FrontLogAspect {

    /**
     * 用于记录请求时间
     */
    ThreadLocal<Long> start = new ThreadLocal<>();

    /**
     * 记录接口日志
     *
     * @param proceeding
     * @return
     */
    @Around(value = "execution(* com.hzsmk.lottery.controller.*.*(..))")
    public Object log(ProceedingJoinPoint proceeding) throws Throwable {
        start.set(System.currentTimeMillis());
        // 创建流水号
        TLocalHelper.createSeq();
        Thread thread = Thread.currentThread();
        thread.setName(TLocalHelper.getSeq());
        HttpServletRequest request = HttpUtil.getRequest();
        log.info("前端接口请求路径:{},请求参数:{},流水号:{}", request.getRequestURI(), StringUtil.toJsonString(proceeding.getArgs()), TLocalHelper.getSeq());
        Object result = proceeding.proceed();
        log.info("接口执行结束,耗时:{}ms,返回数据:{},流水号:{}", System.currentTimeMillis() - start.get(), result.toString(), TLocalHelper.getSeq());

        return result;
    }
}
