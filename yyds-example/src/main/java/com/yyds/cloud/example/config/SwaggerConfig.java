package com.yyds.cloud.example.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Parameter> paramList = new ArrayList<>();
        ParameterBuilder token = new ParameterBuilder()
//                .name(CommonConstants.AUTHORIZATION)
                .description("Bearer token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true);
        paramList.add(token.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(paramList)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(true).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.yyds.cloud"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理后台接口文档")
                .description("管理后台接口文档")
                .termsOfServiceUrl("manager")
                .version("1.0.0")
                .build();
    }
}
