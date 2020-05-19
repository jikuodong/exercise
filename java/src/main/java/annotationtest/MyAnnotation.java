package annotationtest;

/**
 * @projectName: java
 * @package: annotationtest
 * @className: MyAnnotation
 * @author: JKD
 * @description: 自定义注解
 * @date: 2020/5/19 14:05
 * @version: 1.0
 */
public @interface MyAnnotation {

    String value() default "hello";
}
