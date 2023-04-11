package top.lctr.naive.wechat.service.configures;

import io.swagger.models.auth.In;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import io.swagger.v3.oas.models.servers.Server;
import top.lctr.naive.wechat.service.config.ServiceConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Swagger3的接口配置
 *
 * @author LCTR
 * @date 2023-01-16
 */
@Configuration
@EnableConfigurationProperties({ServiceConfig.class})
public class SwaggerConfigure
        implements WebMvcOpenApiTransformationFilter {
    public SwaggerConfigure(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    /**
     * 服务配置
     */
    private final ServiceConfig serviceConfig;

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(serviceConfig.getEnableSwagger())
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo(serviceConfig))
//                .groupName("全部接口")
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                // .apis(RequestHandlerSelectors.basePackage("com.ruoyi.project.tool.swagger"))
//                 扫描所有
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping(serviceConfig.getPathMapping());
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    public static List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization",
                                  "Authorization",
                                  In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    public static List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                               .securityReferences(defaultAuth())
                               .operationSelector(o -> o.requestMappingPattern()
                                                        .matches("/.*"))
                               .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    public static List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global",
                                                                       "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization",
                                                     authorizationScopes));
        return securityReferences;
    }

    /**
     * 添加摘要信息
     */
    public static ApiInfo apiInfo(ServiceConfig serviceConfig) {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title(String.format("%s 接口文档",
                                     serviceConfig.getName()))
                // 描述
                .description("用于提供微信服务")
                // 作者信息
                .contact(new Contact(serviceConfig.getName(),
                                     null,
                                     null))
                // 版本
                .version("版本号:" + serviceConfig.getVersion())
                .build();
    }

    /**
     * 根据实际情况转换http模式
     */
    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI swagger = context.getSpecification();

        if (!context.request()
                    .isPresent())
            return swagger;

        HttpServletRequest request = context.request()
                                            .get();

        String scheme = "http";
        String referer = request.getHeader("Referer");

        if (StringUtils.hasLength(referer)) {
            //获取协议
            scheme = referer.split(":")[0];
        }

        List<Server> servers = new ArrayList<>();
        String finalScheme = scheme;
        //重新组装server信息
        swagger.getServers()
               .forEach(item -> {

                   //替换协议,去掉默认端口
                   item.setUrl(clearDefaultPort(item.getUrl()
                                                    .replace("http",
                                                             finalScheme)));
                   servers.add(item);
               });
        swagger.setServers(servers);
        return swagger;
    }

    /**
     * 清除默认端口
     */
    private String clearDefaultPort(String url) {
        String port = url.split(":")[2];
        if ("80".equals(port) || "443".equals(port)) {
            return url.replace(":80",
                               "")
                      .replace(":443",
                               "");
        }
        return url;
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return documentationType.equals(DocumentationType.OAS_30);
    }
}
