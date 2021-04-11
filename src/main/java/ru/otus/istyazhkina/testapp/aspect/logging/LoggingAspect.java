package ru.otus.istyazhkina.testapp.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @AfterThrowing(value = "@annotation(ru.otus.istyazhkina.testapp.aspect.logging.Logger)", throwing = "exception")
    public void recordFailedExecution(JoinPoint joinPoint, Exception exception) {
        System.out.printf("Метод - %s, класса- %s, был аварийно завершен с исключением - %s\n",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception);
    }
}
