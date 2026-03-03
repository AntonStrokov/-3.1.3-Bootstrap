package ru.kata.spring.boot_security.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut("execution(* ru.kata.spring.boot_security.demo.controller.*.*(..)) || " +
			"execution(* ru.kata.spring.boot_security.demo.service.*.*(..))")
	public void applicationPackagePointcut() {}

	@Before("applicationPackagePointcut()")
	public void logBefore(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getSignature().getDeclaringTypeName();
		Object[] args = joinPoint.getArgs();

		log.info(">>>> [AOP LOG] Entering: {}.{}()", className, methodName);

		if (args != null && args.length > 0) {
			log.info(">>>> [AOP ARGS] Arguments: {}", Arrays.toString(args));
		}
	}
}
