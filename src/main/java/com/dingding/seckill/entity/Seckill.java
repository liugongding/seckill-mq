package com.dingding.seckill.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liudingding
 * 秒杀内容实体
 * 所有变量都对应数据库的字段
 */
@Data
public class Seckill implements Serializable {
    /**
     * 秒杀id
     */
    private Long seckillId;

    /**
     * 秒杀内容
     */
    private String name;

    /**
     * 库存
     */
    private Integer number;

    /**
     * 秒杀开启时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 版本号
     */
    private Integer version;

}
