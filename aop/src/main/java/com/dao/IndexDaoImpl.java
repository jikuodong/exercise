package com.dao;

import org.springframework.stereotype.Component;

/**
 * @projectName: aop
 * @package: com.dao
 * @className: IndexDaoImpl
 * @author: JKD
 * @description: 实现类
 * @date: 2020/5/14 17:43
 * @version: 1.0
 */
@Component
public class IndexDaoImpl implements IndexDao {
    @Override
    public void query() {
        System.out.println("dao-------------query");
    }
}
