package designstrategy.demo.config.annotation;

import java.lang.annotation.*;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:29
 * @Version 1.0
 * @Discription 自定义注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {

    String value();
}
