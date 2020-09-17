package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forCDAOPackage(){}

    @Pointcut("forCDAOPackage()||forControllerPackage()||forServicePackage()")
    private void forAppFlow() {}
    //add @Before advice

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        //display the method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("=========> in @Before: calling method: " + theMethod);
        //display the arguments to the method

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("=========> argument: " + arg);
        }
    }
    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //display the method we are returning form
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("=========> in @AfterReturning: calling method: " + theMethod);
        //display data returned
        logger.info("=========> result: " + result);
    }
}
