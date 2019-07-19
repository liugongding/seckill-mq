package com.dingding.seckill.dao;

import com.dingding.seckill.entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrokerMessageLogDAO {

    /**
     * 插入消息重传记录
     * @param record
     * @return
     */
    int insertBrokerMessage(BrokerMessageLog record);
}
