package com.fuzamei.aop;

import com.fuzamei.annotation.AnalysisActuator;
import com.fuzamei.pojo.OrderPO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by ylx on 2018/12/14.
 */
@Aspect
@Component
@Slf4j
public class AopAspect {

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut(value = "@annotation(analysisActuator)")
    public void serviceStatistics(AnalysisActuator analysisActuator) {
    }

    @Before(value = "serviceStatistics(analysisActuator)")
    public void doBefore(JoinPoint joinPoint, AnalysisActuator analysisActuator) {
        // 记录请求到达时间
        beginTime.set(System.currentTimeMillis());
        log.info("before statistic 起始时间time:{}, note:{}", beginTime.get(), analysisActuator.note());
    }

    @After(value = "serviceStatistics(analysisActuator)")
    public void doAfter(AnalysisActuator analysisActuator) {
        log.info("after statistic 结束，并耗时->time:{}, note:{}", System.currentTimeMillis() - beginTime.get(), analysisActuator.note());
    }

    @Around(value = "serviceStatistics(analysisActuator)")
    public void doAround(ProceedingJoinPoint joinPoint,AnalysisActuator analysisActuator){
        log.info("环绕通知之开始");
        log.info("环绕通知里面{}",analysisActuator.note());
        Object target = joinPoint.getTarget();
        AnalysisActuator annotation = joinPoint.getClass().getAnnotation(AnalysisActuator.class);
        log.info("注解是否存在" + (annotation == null));

        //获取参数对象
        Object[] args = joinPoint.getArgs();
        Object arg = args[0];
        System.out.println(arg);
//
//
//        //获取的是CacheController的class对象
//        Method[] methods = target.getClass().getMethods();
//        for(Method method : methods){
//            System.out.println(method.getName());
//        }

        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info("环绕通知之结束");
    }

    @AfterReturning(value = "serviceStatistics(analysisActuator)")
    public void doAfterReturning(JoinPoint joinPoint,AnalysisActuator analysisActuator){
        log.info("after returning");
    }

    @AfterThrowing(value = "serviceStatistics(analysisActuator)",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,AnalysisActuator analysisActuator,Exception e){
        log.info("捕获到异常："+e.getMessage());
        log.info("after throwing");
    }

}
