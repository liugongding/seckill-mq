package com.dingding.seckill.mq.rabbitsuccesskilled;

import com.dingding.seckill.entity.User;
import com.dingding.seckill.exception.RepeatKillException;
import com.dingding.seckill.exception.SeckillCloseException;
import com.dingding.seckill.service.impl.SuccessSeckillService;
import com.dingding.seckill.util.FastJsonConvertUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @Author liugongding
 * @Date 2019-07-24
 */
@Component
@Slf4j
public class ReceiveMQ {

    @Autowired
    private SuccessSeckillService successSeckillService;

    @RabbitListener(
            bindings = @QueueBinding(
                    //将队列持久化
                    value = @Queue(value = "successKilled-queue", durable = "true"),
                    //绑定交换机
                    exchange = @Exchange(value = "successKilled-exchange", durable = "true", type = ExchangeTypes.TOPIC),
                    //绑定路由键名称
                    key = "successKilled.*"
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload String msg, @Headers Map<String, Object> headers, Channel channel, Message message, CorrelationData correlationData) throws IOException {
        //消费者开始消费
        log.info("---------收到消息，开始消费--------");
        User user = FastJsonConvertUtil.convertJSONToObject(msg,User.class);
        log.info("javaBean" + user);
        log.info("javaBean" + user.getClass());
        log.info("秒杀电话" + user.getPhone());
        log.info("秒杀id" + user.getSeckillId());
        log.info("消息id" + correlationData.getId());
        log.info("消息" + message.getHeaders());
        // TODO
        /**
         * Delivery Tag 用来标志信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个Delivery Tag
         * 以便 Consumer 可以在消息确认是告诉 RabbitMQ 到底那条消息被确认了
         * RabbitMQ 保证在每条信道中，每条消息的 Delivery Tag 从 1 开始递增
         */
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        log.info("..." + deliveryTag);

        try{
            successSeckillService.executeSeckill(user,deliveryTag, channel);
        } catch (RepeatKillException | SeckillCloseException e) {
            /**
             * multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
             * 如果为 true， 则额外将比第一个参数指定的 Delivery Tag 小的消息一并确认
             */
            boolean multiple = false;
            channel.basicAck(deliveryTag, multiple);
            log.info("信道是否关闭", channel.isOpen());
            throw e;
        }
    }
}
