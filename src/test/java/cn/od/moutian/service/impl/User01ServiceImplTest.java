package cn.od.moutian.service.impl;

import cn.od.moutian.BaseTest;
import cn.od.moutian.service.User01Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class User01ServiceImplTest extends BaseTest {
    @Autowired
    User01Service user01Service;

    @Test
    public void countAll() {
        System.out.println(user01Service.countAll());
    }
}