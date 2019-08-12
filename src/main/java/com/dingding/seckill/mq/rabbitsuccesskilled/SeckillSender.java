package com.dingding.seckill.mq.rabbitsuccesskilled;

import com.dingding.seckill.dao.BrokerMessageLogDAO;
import com.dingding.seckill.entity.User;
import com.dingding.seckill.enums.ConstantEnum;
import com.dingding.seckill.exception.RepeatKillException;
import com.dingding.seckill.exception.SeckillCloseException;
import com.dingding.seckill.util.FastJsonConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author liudingding
 * @ClassName SeckillSender
 * @description
 * @date 2019/6/25 17:23
 * Version 1.0
 */
@Component
@Slf4j
public class SeckillSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private BrokerMessageLogDAO brokerMessageLogDAO;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("回调" + correlationData);
            String messageId = correlationData.getId();
            log.info("..." + ack);
            if (ack) {
                //如果confirm返回成功，则改变broker表的状态
                //MQ成功到达交换机
                brokerMessageLogDAO.changeBrokerMessageLogStatus(messageId, String.valueOf(ConstantEnum.MESSAGE_SEND_SUCCESS.getStatus()), new Date());
                log.info("消息成功达到mq");
            } else {
                //TODO 没有达到交换机进行异常处理
                log.error("MQ消息达到Exchange异常");
            }
        }
    };

    public void sendMessage(User user) throws Exception{
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(String.valueOf(user.getPhone()));
        String msg = FastJsonConvertUtil.convertObjectToJSON(user);
        log.info("..." + correlationData);
        log.info("对象字符串" + msg);
        try {
            rabbitTemplate.convertAndSend("successKilled-exchange", "successKilled.send", msg, correlationData);
        } catch (RepeatKillException | SeckillCloseException e) {
            throw e;
        }
    }
}
