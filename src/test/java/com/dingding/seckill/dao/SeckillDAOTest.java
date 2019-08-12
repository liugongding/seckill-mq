package com.dingding.seckill.dao;

import com.dingding.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SeckillDAOTest {

    @Resource
    private SeckillDAO seckillDAO;

    @Test
    public void reduceNumber() {
        Long id = 1000L;
        Date killTime = new Date();
        System.out.println(killTime);
        int updateCount = seckillDAO.reduceNumber(id, killTime);
        System.out.println(updateCount);
    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDAO.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDAO.queryAll(0,4);
        seckills.forEach(seckill -> System.out.println(seckill));
    }


}