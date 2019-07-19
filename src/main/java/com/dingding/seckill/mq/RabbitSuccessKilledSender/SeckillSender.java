package com.dingding.seckill.mq.RabbitSuccessKilledSender;

import com.dingding.seckill.dao.BrokerMessageLogDAO;
import com.dingding.seckill.entity.BrokerMessageLog;
import com.dingding.seckill.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author liudingding
 * @ClassName SeckillSender
 * @description
 * @date 2019/6/25 17:23
 * Version 1.0
 */
public class SeckillSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private BrokerMessageLogDAO brokerMessageLogDAO;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            String messageId = correlationData.getId();
        }
    };

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend();
    }
}
