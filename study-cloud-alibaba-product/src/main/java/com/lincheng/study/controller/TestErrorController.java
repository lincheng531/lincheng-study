package com.lincheng.study.controller;

import com.lincheng.study.domain.product.ErrorVO;
import com.lincheng.study.exception.BusinessException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-05 22:31
 **/
@RestController
@RequestMapping("/error")
public class TestErrorController {


    @PostMapping("/user")
    public boolean insert(@RequestBody ErrorVO user) {
        System.out.println("开始新增...");
        // 如果姓名为空就手动抛出一个自定义的异常！
        if (user.getName() == null) {
            throw new BusinessException("-1", "用户姓名不能为空！");
        }
        return true;
    }

    @PutMapping("/user")
    public boolean update(@RequestBody ErrorVO user) {
        System.out.println("开始更新...");
        // 这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestBody ErrorVO user) {
        System.out.println("开始删除...");
        // 这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/user")
    public List<ErrorVO> findByUser(ErrorVO user) {
        System.out.println("开始查询...");
        List<ErrorVO> userList = new ArrayList<>();
        ErrorVO user2 = new ErrorVO();
        user2.setId(1);
        user2.setName("xuwujing");
        user2.setAge(18);
        userList.add(user2);
        return userList;
    }

}
