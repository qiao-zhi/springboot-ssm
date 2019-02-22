package cn.qlq.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 记录service层执行时间的AOP切面
 * 
 * @author QiaoLiQiang
 * @time 2019年2月21日下午9:20:15
 */
//@Aspect
@Component
public class LogServiceTakeTime {
	private final static Logger log = LoggerFactory.getLogger(LogServiceTakeTime.class);

	@Pointcut("execution(* cn.qlq.service..*.*(..))")
	public void performance() {
	}

	/**
	 * 环绕通知记录时间
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("performance()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

		// 记录起始时间
		long begin = System.currentTimeMillis();
		Object result = "";
		/** 执行目标方法 */
		try {
			result = joinPoint.proceed();
		} catch (Exception e) {
			log.error("日志记录发生错误, errorMessage: {}", e.getMessage());
		} finally {
			/** 记录操作时间 */
			long took = (System.currentTimeMillis() - begin) / 1000;
			log.info("Service执行时间为: {}秒", took);
		}
		return result;
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("performance()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		log.info("doBefore");
	}

}
