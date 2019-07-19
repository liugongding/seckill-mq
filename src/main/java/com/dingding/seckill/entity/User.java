package com.dingding.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liudingding
 * @ClassName User
 * @description
 * @date 2019/6/24 14:41
 * Version 1.0
 */
@Data
@AllArgsConstructor
public class User implements Serializable {

    private Long seckillId;
    private Long phone;
}
