package com.lincheng.study.domain.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-05 22:31
 **/
@Data
public class ErrorVO implements Serializable {


    private static final long serialVersionUID = -5115509403816088275L;


    // 编号
    private int id;
    // 姓名
    private String name;
    // 年龄
    private int age;
}
