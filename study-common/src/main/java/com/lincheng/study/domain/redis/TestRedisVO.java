package com.lincheng.study.domain.redis;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-02 09:48
 **/
@Data
public class TestRedisVO implements Serializable {

    private static final long serialVersionUID = 390621806841221807L;

    private Long key;

    private Long size;

    private String value;

    private List<String> propertys;
}
