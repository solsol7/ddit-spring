package com.springboard.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

/**
 * business logic 의 실행에 소요된 시간과,
 * 해당 로직으로 전달되는 파라미터를 로그로 출력하기 위한 advice
 *
 */
@Slf4j
public class LoggingAdvice {
	public void before() {
		log.info("================================================");
	}
	public void after() {
		log.info("------------------------------------------------");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		log.info("{}.{}({})",targetName, methodName, args);
		long start = System.currentTimeMillis();
		Object targetResult = joinPoint.proceed(args);	// advice 내에서 target 호출함
		long end = System.currentTimeMillis();
		log.info("------------------------소요시간 : {}ms------------------------",(end-start));
		return targetResult;
	}
}
