package cn.qlq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 
 * @author QiaoLiQiang
 * @time 2019年2月21日下午9:45:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLogAnnotation {
	String operateDescription();// 记录日志的操作类型，不写默认值就是一个必须填的注解
}
