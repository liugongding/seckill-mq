package com.dingding.seckill.mq;

import com.dingding.seckill.entity.User;

/**
 * @author liudingding
 * @ClassName readMqService
 * @description
 * @date 2019/6/24 14:50
 * Version 1.0
 */
public interface ReadMqService {

    void createSuccessKillMq(User user) throws Exception;
}
