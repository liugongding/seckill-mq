package com.dingding.seckill.service.impl;

import com.dingding.seckill.dao.SeckillDAO;
import com.dingding.seckill.dao.SuccessKillDAO;
import com.dingding.seckill.entity.User;
import com.dingding.seckill.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Description
 * @Author liugongding
 * @Date 2019-07-29
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SuccessSeckillServiceTest {

    @Resource
    private SuccessSeckillService successSeckillService;

    @Resource
    private SuccessKillDAO successKillDAO;

    @Resource
    private SeckillDAO seckillDAO;
    @Test
    public void TEST1() {
        User user = new User(1000l, RandomUtil.getRandomNumber(11));

//        String msg= JsonUtil.objectToJson(user);
//        System.out.println(msg);
//        JSONObject jsonObject = JSONObject.parseObject(msg);
//        System.out.println(jsonObject);
//        User data = JSON.parseObject(msg, User.class);
//        System.out.println(data);
//        System.out.println(data.getClass());
//        successSeckillService.executeSeckill(user);
    }
}