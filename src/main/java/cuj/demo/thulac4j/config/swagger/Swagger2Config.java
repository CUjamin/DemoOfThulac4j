package cuj.demo.thulac4j.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by jie liu on 2017/7/8.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger.method.submit}")
    private String submitMethod;

    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        return properties;
    }

    @Bean
    public Docket aiOcsApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Demo Of thulac4j 接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .securitySchemes(apiKeys())
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cuj.demo.thulac4j.controller"))
                .paths(regex("/v1/thulac4j/.*"))
                .build()
                .apiInfo(aiOcsApiInfo());
    }

    @Bean
    UiConfiguration uiConfig() {
        String[] supportedSubmitMethod = UiConfiguration.Constants.NO_SUBMIT_METHODS;
        if (submitMethod.equals("true")) {
            supportedSubmitMethod = UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS;
        }
        return new UiConfiguration(
                null,// validator url
                "list",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                supportedSubmitMethod,
                false,        // enableJsonEditor      => true | false
                true         // showRequestHeaders    => true | false
        );
    }

    private ApiInfo aiOcsApiInfo(){
        return new ApiInfoBuilder()
                .title("Demo Of thulac4j 接口")
                .version("1.0")
                .build();
    }

    private List<ApiKey> apiKeys() {
        List<ApiKey> apiKeys = new ArrayList<ApiKey>();
        //api key的名称，key字段以及传输方式(header or query)
        ApiKey apiKey = new ApiKey("mykey", "api_key", "header");
        apiKeys.add(apiKey);
        return apiKeys;
    }
    private List<SecurityContext> securityContext() {
        List<SecurityContext> securityContexts = new ArrayList<SecurityContext>();
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/*"))
                .build();
        securityContexts.add(securityContext);
        return securityContexts;
    }
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "全局授权");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<SecurityReference>();
        securityReferences.add(new SecurityReference("mykey", authorizationScopes));
        return securityReferences;
    }
    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "global",
                "cuj",
                "Demo Of thulac4j 接口",
                "Demo Of thulac4j 接口",
                "apiKey",
                ApiKeyVehicle.HEADER,
                "api_key",
                "," /*scope separator*/);
    }
}
