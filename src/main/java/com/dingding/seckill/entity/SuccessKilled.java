package com.dingding.seckill.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liudingding
 * 成功秒杀明细实体
 */
@Data
public class SuccessKilled implements Serializable {

    /**
     * 对应哪个秒杀的id
     */
    private Long seckillId;

    /**
     * 用户电话
     */
    private Long userPhone;

    /**
     * 秒杀状态
     */
    private Short state;

    /**
     * 秒杀成功的创建时间
     */
    private Date createTime;

    /**
     * 一个秒杀记录对应多个实体
     */
    private Seckill seckill;

    /**
     * 秒杀商品名称
     */
    private String name;

}
