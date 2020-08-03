package cn.qlq.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod() + "==方法执行前==");
        Object proceed = invocation.proceed();
        System.out.println(invocation.getArguments() + "--方法执行后--");
        return proceed;
    }

}