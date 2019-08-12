package com.dingding.seckill.dao;

import com.dingding.seckill.entity.BrokerMessageLog;
import com.dingding.seckill.entity.User;
import com.dingding.seckill.enums.ConstantEnum;
import com.dingding.seckill.mq.rabbitsuccesskilled.SeckillSender;
import com.dingding.seckill.util.FastJsonConvertUtil;
import com.dingding.seckill.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class BrokerMessageLogDAOTest {

    @Resource
    private BrokerMessageLogDAO brokerMessageLogDAO;
    @Resource
    private SeckillSender seckillSender;

    @Test
    public void insertBrokerMessage() throws InterruptedException {
//        for (int i=0;i<100;i++) {
            User user = new User(1000L,RandomUtil.getRandomNumber(11));
//            Thread.sleep(1000);
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(user.getPhone());
        brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(user));
        brokerMessageLog.setStatus(ConstantEnum.MESSAGE_SENDING.getStatus());
        brokerMessageLog.setNextRetry(new Date());
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        log.info("电话" + user.getPhone());
        brokerMessageLogDAO.insertBrokerMessage(brokerMessageLog);
//            seckillSender.sendMessage(user);
//        }
    }

    @Test
    public void test1(){
        System.out.println(RandomUtil.getRandomNumber(11));
    }
}