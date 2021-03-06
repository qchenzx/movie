package com.chenzx.movie.config;

import io.swagger.annotations.ApiOperation;
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
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/4 15:27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("电影项目后端开放接口说明文档")
                        .description("常州机电职业技术学院22年web开发技能大赛练习用电影项目说明文档")
                        .version("1.0")
                        .contact(new Contact("陈泽宣", "http://192.168.31.100/web/index.php?p=movie.git&a=summary", "qchenzexuan@vip.qq.com"))
                        .build())
                .useDefaultResponseMessages(false);
    }
}
