package com.lincheng.study.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lincheng.study.domain.base.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-28 16:27
 **/
@Component
@Slf4j
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        // getRule()- 资源规则的详细信息
        log.info("BlockExceptionHandler BlockException=============" + e.getRule());

        Response<Object> result = null;
        if (e instanceof FlowException) {
            result = new Response<>("901","接口限流了");
        } else if (e instanceof DegradeException) {
            result = new Response<>("902","服务降级了");
        } else if (e instanceof ParamFlowException) {
            result = new Response<>("903","热点参数限流了");
        } else if (e instanceof SystemBlockException) {
            result = new Response<>("904","触发系统保护规则了");
        } else if (e instanceof AuthorityException) {
            result = new Response<>("905","授权规则不通过");
        }

        httpServletResponse.setStatus(400);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), result);
    }
}
