package com.test.testbackend.copy;

//import com.dzqq.intercepter.JwtTokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class JwtConfig implements WebMvcConfigurer {
    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePatterns = new String[]{"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                "/api", "/api-docs", "/api-docs/**", "/doc.html/**"};

        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/login2","/registry").excludePathPatterns(excludePatterns); // 排除不需要拦截的路径
    }
@Bean
public Docket docket2() {
    ApiInfo apiInfo = new ApiInfoBuilder()
            .title("银行app文档")
            .version("1.0")
            .description("银行app接口文档")
            .build();
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .groupName("用户接口")
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.test.testbackend.controller"))
            .paths(PathSelectors.any())
            .build();
    return docket;
}

    /**
     * 设置静态资源映射
     * @param registry
     */

}
