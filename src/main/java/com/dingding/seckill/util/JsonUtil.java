package com.dingding.seckill.util;

import com.alibaba.fastjson.JSON;

/**
 * @author liudingding
 * @ClassName GsonUtil
 * @description json转化
 * @date 2019/6/24 17:01
 * Version 1.0
 */
public class JsonUtil {

    /**
     * 对象转json
     * @return
     */
    public static String objectToJson(Object object){
        String jsonString = JSON.toJSONString(object);
        return jsonString;
    }

    /**
     * json转对象
     * @param msg
     * @param object
     * @return
     */
    public static Object jsonToObject(String msg, Class<Object> object) {
       Object bean = JSON.parseObject(msg, object);
       return bean;
    }
}
