package com.dingding.seckill.dao;

import com.dingding.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liudingding
 */
@Mapper
public interface SuccessKillDAO {

    /**
     * 根据用户id和手机号插入购买明细，可过滤重复
     * 如果出现主键冲突，返回0
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     *
     * @param seckillId
     * @Param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
