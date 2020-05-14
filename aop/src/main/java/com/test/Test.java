package com.test;

import com.config.Appconfig;
import com.dao.IndexDao;
import com.dao.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @projectName: aop
 * @package: com.test
 * @className: Test
 * @author: JKD
 * @description: 测试类
 * @date: 2020/5/14 17:45
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        IndexDaoImpl dao = (IndexDaoImpl) annotationConfigApplicationContext.getBean(IndexDao.class);
        dao.query();
    }
}
