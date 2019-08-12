package com.dingding.seckill.mq;

import com.dingding.seckill.dao.BrokerMessageLogDAO;
import com.dingding.seckill.entity.BrokerMessageLog;
import com.dingding.seckill.entity.User;
import com.dingding.seckill.enums.ConstantEnum;
import com.dingding.seckill.exception.RepeatKillException;
import com.dingding.seckill.exception.SeckillCloseException;
import com.dingding.seckill.mq.rabbitsuccesskilled.SeckillSender;
import com.dingding.seckill.util.FastJsonConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author liudingding
 * @ClassName ReadMqServiceImpl
 * @description
 * @date 2019/6/24 15:34
 * Version 1.0
 */
@Service
public class ReadMqServiceImpl implements ReadMqService {

    @Resource
    private BrokerMessageLogDAO brokerMessageLogDAO;

    @Resource
    private SeckillSender seckillSender;

    @Override
    public void createSuccessKillMq(User user) throws Exception {
        //插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        //消息唯一id
        brokerMessageLog.setMessageId(user.getPhone());
        //保存消息整体，转json入库
        brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(user));
        //设置消息状态为0，表示发送中
        brokerMessageLog.setStatus(String.valueOf(ConstantEnum.MESSAGE_SENDING));
        //设置消息未确认，超时时间窗口为一分钟
        brokerMessageLog.setNextRetry(new Date());
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogDAO.insertBrokerMessage(brokerMessageLog);
        try {
            seckillSender.sendMessage(user);
        } catch (RepeatKillException | SeckillCloseException e) {
            throw e;
        }
    }
}
