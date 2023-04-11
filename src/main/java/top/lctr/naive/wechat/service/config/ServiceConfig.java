package top.lctr.naive.wechat.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 服务配置
 *
 * @author LCTR
 * @date 2023-03-03
 */
@Primary
@Component
@ConfigurationProperties("service")
@Data
public class ServiceConfig {
    /**
     * 服务标识
     */
    private String key;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 版本号
     */
    private String version;

    /**
     * 启用Swagger
     */
    private Boolean enableSwagger;

    /**
     * 反向代理地址
     */
    private String pathMapping;

    /**
     * 站点资源文件根目录路径
     */
    private String wwwRootDirectory;
}
