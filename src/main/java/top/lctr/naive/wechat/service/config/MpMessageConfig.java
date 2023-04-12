package top.lctr.naive.wechat.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 微信公众号配置
 * <p>消息处理模块</p>
 *
 * @author LCTR
 * @date 2023-04-12
 */
@Primary
@Component
@ConfigurationProperties("mp.message")
@Data
public class MpMessageConfig {
    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 线程池大小
     */
    private int threadPoolSize;
}
