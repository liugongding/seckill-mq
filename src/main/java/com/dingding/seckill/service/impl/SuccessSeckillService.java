package com.dingding.seckill.service.impl;

import com.dingding.seckill.dao.SeckillDAO;
import com.dingding.seckill.dao.SuccessKillDAO;
import com.dingding.seckill.entity.Seckill;
import com.dingding.seckill.entity.SuccessKilled;
import com.dingding.seckill.entity.User;
import com.dingding.seckill.exception.RepeatKillException;
import com.dingding.seckill.exception.SeckillCloseException;
import com.dingding.seckill.exception.SeckillException;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author liugongding
 * @Date 2019-07-24
 */
@Service
@Slf4j
public class SuccessSeckillService {

    @Resource
    private SuccessKillDAO successKillDAO;

    @Resource
    private SeckillDAO seckillDAO;

    public void executeSeckill(User user,Long deliveryTag, Channel channel) {
        log.info("秒杀对象" + user);
        Seckill seckill = null;
        //记录购买明细
        try {
            int insertCount = successKillDAO.insertSuccessKilled(user.getSeckillId(),user.getPhone());
            log.info("{是否插入成功}" + insertCount);
            //购买明细是否被重复插入，即是否重复秒杀
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                seckill = seckillDAO.queryById(user.getSeckillId());
                log.info("{秒杀实体}"+seckill);
                int updateCount = seckillDAO.reduceNumber(user.getSeckillId(),new Date());
                log.info("{是否有库存}" + updateCount);
                if (updateCount <= 0) {
                    //如果没有库存了，秒杀结束
                    throw new SeckillCloseException("seckill close");
                } else {
                    SuccessKilled successKilled = successKillDAO.queryByIdWithSeckill(user.getSeckillId(),user.getPhone());
                    log.info("关联查询" + successKilled);
                    // TODO 更新缓存
                    log.info("购买明细" + successKilled);
                }
            }
        } catch (RepeatKillException | SeckillCloseException e) {
//            channel.basicAck(deliveryTag, false);
            throw e;
        } catch (Exception e1) {
            throw new SeckillException("seckill inner error" + e1.getMessage());
        }
    }
}
