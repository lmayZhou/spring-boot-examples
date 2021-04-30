package com.lmaye.activiti.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * -- Swagger Config
 *
 * @author Lmay Zhou
 * @date 2021/4/25 15:24
 * @email lmay@lmaye.com
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    /**
     * Swagger 文档控制
     */
    @Value(value = "${swagger.enabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.lmaye.activiti.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("Spring Boot2 中使用 Swagger3 构建 RESTful APIs")
                // 描述
                .description("更多Spring Boot相关文章请关注：https://www.lmaye.com")
                // 服务地址
                .termsOfServiceUrl("https://www.lmaye.com")
                // 作者信息
                .contact(new Contact("lmay", "https://www.lmaye.com", "lmay@lmaye.com"))
                // 版本
                .version("1.0").build();
    }
}
