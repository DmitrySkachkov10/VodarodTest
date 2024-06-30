package by.dmitry_skachkov.vodarodtest.aop;


import by.dmitry_skachkov.vodarodtest.core.dto.RateDto;
import by.dmitry_skachkov.vodarodtest.core.exception.CustomApplicationException;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Pointcut("execution(void by.dmitry_skachkov.vodarodtest.service.*.*(..))")
    public void serviceVoidMethods() {
    }

    @Pointcut("execution(by.dmitry_skachkov.vodarodtest.core.dto.RateDto by.dmitry_skachkov.vodarodtest.service.*.*(..))")
    public void serviceGetMethods() {
    }


    @Around("serviceVoidMethods()")
    public void logAndHandleServiceVoidMethods(ProceedingJoinPoint joinPoint) {
        try {
            RateDto rateDto = (RateDto) joinPoint.proceed();
            log.info("Exiting method: {} ", joinPoint.getSignature());
        } catch (CustomApplicationException e) {
            log.error("Exception in method: {} with message: {}", joinPoint.getSignature(), e.getMessage());
            throw e;
        } catch (Throwable throwable) {
            log.error("Unexpected exception in method: {} with message: {}", joinPoint.getSignature(), throwable.getMessage());
            throw new CustomApplicationException("An unintended error occurred");
        }
    }


    @Around("serviceGetMethods()")
    public Object logAndHandleServiceMethods(ProceedingJoinPoint joinPoint) {
        try {
            RateDto rateDto = (RateDto) joinPoint.proceed();
            log.info("Exiting method: {} ", joinPoint.getSignature());
            return rateDto;
        } catch (CustomApplicationException e) {
            log.error("Exception in method: {} with message: {}", joinPoint.getSignature(), e.getMessage());
            throw e;
        } catch (Throwable throwable) {
            log.error("Unexpected exception in method: {} with message: {}", joinPoint.getSignature(), throwable.getMessage());
            throw new CustomApplicationException("An unintended error occurred");
        }
    }
}
