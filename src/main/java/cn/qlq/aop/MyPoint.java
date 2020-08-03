package cn.qlq.aop;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

public class MyPoint implements Pointcut {

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                // 如果ServiceImpl就返回true
                if (clazz != null && StringUtils.contains(clazz.getName(), "cn.qlq.service")) {
                    System.out.println("======MyPoint======getClassFilter ");
                    System.out.println(clazz);
                    return true;
                }

                return false;
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {

            /**
             * 判断方法是否匹配
             */
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                System.out.println("matches=========1");
                System.out.println(method);
                System.out.println(targetClass);
                System.out.println(args);
                return true;
            }

            /**
             * 判断方法是否匹配
             */
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                System.out.println("matches=========2");
                System.out.println(method);
                System.out.println(targetClass);
                return true;
            }

            @Override
            public boolean isRuntime() {
                return false;
            }
        };
    }

}