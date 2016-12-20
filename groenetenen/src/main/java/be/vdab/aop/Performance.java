package be.vdab.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Performance {
	private final static Logger LOGGER = Logger.getLogger(Performance.class.getName());
	
	@Around("execution(* be.vdab.services.*.*(..))")
	Object schrijfPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
		
	}
}
