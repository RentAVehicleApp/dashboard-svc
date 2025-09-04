package rent.vehicle.dashboardserviceapi.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Around("execution(* rent.vehicle.dashboardserviceapi.*.service.*.*(..))")
    public Object logServiceCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        log.info("Executing service method: {}", method);

        try {
            Object result = joinPoint.proceed();
            log.info("Service method executed successfully: {}", method);
            return result;
        } catch (Throwable e) {
            log.error("Error executing service method: {}", method, e);
            throw e;
        } finally {
            log.info("Finished executing service method: {}", method);
        }
    }
}
