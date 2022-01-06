package com.lincheng.study.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @description:
 * @author: linCheng
 * @create: 2022-01-06 15:58
 **/
@Configuration
public class GatewayConfig {

    @PostConstruct
    public void init(){
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {

                HashMap<Object, Object> result = new HashMap<>();

                if (throwable instanceof FlowException) {
                    result.put("resultCode","901");
                    result.put("resultMsg","接口限流了gateway");
                } else if (throwable instanceof DegradeException) {
                    result.put("resultCode","902");
                    result.put("resultMsg","服务降级了gateway");
                } else if (throwable instanceof ParamFlowException) {
                    result.put("resultCode","903");
                    result.put("resultMsg","热点参数限流了gateway");
                } else if (throwable instanceof SystemBlockException) {
                    result.put("resultCode","904");
                    result.put("resultMsg","触发系统保护规则了gateway");
                } else if (throwable instanceof AuthorityException) {
                    result.put("resultCode","905");
                    result.put("resultMsg","授权规则不通过gateway");
                }

                // 自定义异常处理
                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(result));

            }
        };

        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}
