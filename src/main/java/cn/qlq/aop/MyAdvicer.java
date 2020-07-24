package cn.qlq.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.stereotype.Component;

//@Component
public class MyAdvicer implements PointcutAdvisor {

	@Override
	public Advice getAdvice() {
		return new MyInterceptor();
	}

	@Override
	public boolean isPerInstance() {
		return true;
	}

	@Override
	public Pointcut getPointcut() {
		return new MyPoint();
	}

}