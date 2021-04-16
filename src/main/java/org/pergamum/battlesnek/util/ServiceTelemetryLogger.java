package org.pergamum.battlesnek.util;


	

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

	/**
	 * <h1>ServiceTelemetryLogger</h1>
	 * ServiceTelemetryLogger logs telemetry for all methods for the service hierarchy at info level.
	 * Modifying the logging level to info will switch the telemetry on for all classes under
	 * com.drumcoder.diary.service.
	 */
	@Aspect
	@Component
	@Slf4j
	public class ServiceTelemetryLogger {
	      /**
	     * <h>logBeforeAndAfterServiceMethods</h>
	     * Adds trace logging before a proceeding join point method call.
	     * @param pjp The proceeding joint point
	     * @return Result of method call
	     * @throws Throwable
	     */
	    @Around("execution(* org.pergamum.*.* (..))")
	    public Object logBeforeAndAfterServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
	        log.debug("{} has started execution.", pjp.getSignature());
	        Object resultOfMethodCall = pjp.proceed();
	        log.debug("{} finished execution", pjp.getSignature());
	        return resultOfMethodCall;
	    }
	}

