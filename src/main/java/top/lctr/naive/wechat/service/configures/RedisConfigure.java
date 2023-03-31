package top.lctr.naive.wechat.service.configures;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import project.extension.redis.config.NaiveRedisConfigure;
import top.lctr.naive.wechat.service.dto.userInfoDTO.RedisScopeInfo;
import top.lctr.naive.wechat.service.dto.userInfoDTO.RedisUserInfo;

/**
 * Redis配置类
 *
 * @author LCTR
 * @date 2023-03-29
 */
@Configuration
@EnableCaching
public class RedisConfigure
        extends NaiveRedisConfigure {
    /**
     * 配置微信用户授权信息redis
     *
     * @param connectionFactory 连接工厂
     */
    @Bean
    public RedisTemplate<String, RedisScopeInfo> scopeInfoRedisTemplate(RedisConnectionFactory connectionFactory) {
        return super.redisTemplate(connectionFactory,
                                   RedisScopeInfo.class);
    }

    /**
     * 配置微信用户信息redis
     *
     * @param connectionFactory 连接工厂
     */
    @Bean
    public RedisTemplate<String, RedisUserInfo> userInfoRedisTemplate(RedisConnectionFactory connectionFactory) {
        return super.redisTemplate(connectionFactory,
                                   RedisUserInfo.class);
    }

    /**
     * 配置限流脚本
     */
    @Bean
    public DefaultRedisScript<Boolean> limitScript() {
        return super.limitScript();
    }
}
