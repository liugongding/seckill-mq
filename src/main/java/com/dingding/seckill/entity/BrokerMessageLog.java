package com.dingding.seckill.entity;

import lombok.Data;

import java.util.Date;
/**
 * @author liudingding
 * @ClassName BrokerMessageLog
 * @description mq消息确认机制的broker
 * @date 2019/6/24 16:35
 * Version 1.0
 */
@Data
public class BrokerMessageLog {

    private Long messageId;

    private String message;

    private Integer tryCount;

    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;
}
