package com.lincheng.study.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

/**
 * @description: JSON工具类
 * @author: linCheng
 * @create: 2021-10-08 21:36
 **/
public class JsonUtils {


    /**
     * @throws JsonProcessingException
     * @Description: 将对象的大写转换为下划线加小写。
     * 例如：userName-->user_name
     * @author: linCheng
     * @Date: 2021/10/8 21:38
     * @param: object
     * @Return: java.lang.String
     */
    public static String toUnderlineJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }


    /**
     * @throws IOException
     * @Description: 将下划线转换为驼峰的形式。
     * 例如：user_name-->userName
     * @author: linCheng
     * @Date: 2021/10/8 21:40
     * @param: json
     * @param: clazz
     * @Return: T
     */
    public static <T> T toSnakeObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper.readValue(json, clazz);
    }


    /**
     * @Description: 首字母大写json串
     * @author: linCheng
     * @Date: 2021/10/8 21:49
     * @param: object
     * @Return: java.lang.String
     */
    public static String CapitalizeFirstLetterToJSONString(Object object) {
        return JSON.toJSONString(object, new PascalNameFilter());
    }

}
