package com.lmaye.spring.boot.file.handle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * -- Swagger2 Config
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/13 0:40 星期四
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 创建API接口文档
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string"))
                .parameterType("header").required(true).defaultValue("Token For File Handle");
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("File Handle")
                .apiInfo(apiInfo())
                .select()
                //选择controller包
                .apis(RequestHandlerSelectors.basePackage("com.lmaye.spring.boot.file.handle.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    /**
     * ApiInfo
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("Spring Boot2 中使用 Swagger2 构建 RESTful APIs")
                // 描述
                .description("更多Spring Boot相关文章请关注：https://www.lmaye.com")
                // 服务地址
                .termsOfServiceUrl("https://www.lmaye.com")
                // 联系方式
                .contact(new Contact("lmay.Zhou", "https://www.lmaye.com", "lmay@lmaye.com"))
                // 版本
                .version("1.0")
                .build();
    }
}
