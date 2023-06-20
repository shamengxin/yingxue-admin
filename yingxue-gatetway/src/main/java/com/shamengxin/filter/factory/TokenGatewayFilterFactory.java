package com.shamengxin.filter.factory;

import com.shamengxin.constants.RedisPrefix;
import com.shamengxin.exceptions.IllegalTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

//自定义token工厂类
@Slf4j
@Component //在工厂中创建对象
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public TokenGatewayFilterFactory(StringRedisTemplate redisTemplate) {
        super(Config.class);
        this.redisTemplate = redisTemplate;
    }


    @Override
    public GatewayFilter apply(Config config) {

        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("进入自定义filer.....");
                // 1.获取token信息
                if (exchange.getRequest().getQueryParams().get("token")==null) throw new IllegalTokenException("非法令牌!");
                String token = exchange.getRequest().getQueryParams().get("token").get(0);
                if (!redisTemplate.hasKey(RedisPrefix.TOKEN_KEY + token)) {
                    throw new IllegalTokenException("不合法的令牌!");
                }
                return chain.filter(exchange);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return super.shortcutFieldOrder();
    }

    public static class Config {

    }

}
