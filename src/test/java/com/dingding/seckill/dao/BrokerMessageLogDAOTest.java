package com.dingding.seckill.dao;

import com.dingding.seckill.entity.BrokerMessageLog;
import com.dingding.seckill.entity.User;
import com.dingding.seckill.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BrokerMessageLogDAOTest {

    @Resource
    private BrokerMessageLogDAO brokerMessageLogDAO;
    @Test
    public void insertBrokerMessage() {
        User user = new User(1l,1l);
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(user.getPhone());
        brokerMessageLog.setMessage(JsonUtil.objectToJson(user));
        brokerMessageLog.setStatus("0");
        brokerMessageLog.setNextRetry(new Date());
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogDAO.insertBrokerMessage(brokerMessageLog);
    }
}