package com.system.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * Swagger配置
 * </p>
 *
 * @author wushengbin
 * @Date 2021/3/4 18:49
 * @since https://www.cnblogs.com/xiaodou00/p/13539269.html 具体配置参数
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.system.base"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("资源过期项目服务接口")
                        .description("资源过期项目包括提供一套完整的资源操作流程。")
                        .version("9.0")
                        .contact(new Contact("", "https://www.cloud.com", "aaa@gmail.com"))
                        .license("The Apache License")
                        .licenseUrl("http://www.cloud.com")
                        .build());
    }

}
