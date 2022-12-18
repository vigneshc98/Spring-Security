package com.vtech.remembermejwt.config;

import com.vtech.remembermejwt.controller.DefaultController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@EnableWebMvc
@Configuration
public class RestConfiguration extends WebMvcConfigurationSupport {

    //for handling unmapped request
    protected Object createDefaultHandler() {
        return new DefaultController();
    }

    //for handling unmapped request
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = super.createRequestMappingHandlerMapping();
        Object defaultHandler = createDefaultHandler();
        handlerMapping.setDefaultHandler(defaultHandler);
        return handlerMapping;
    }

}
