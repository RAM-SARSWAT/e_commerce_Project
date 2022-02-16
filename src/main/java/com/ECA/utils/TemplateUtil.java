package com.ECA.utils;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Map;

@Component
public class TemplateUtil {
    public String fillData(String templateName, Map<String, Object> map){
        try {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");
            templateResolver.setTemplateMode("HTML");
            templateResolver.setSuffix(".html");
            templateResolver.setCharacterEncoding("UTF-8");
            templateResolver.setOrder(1);
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
            Context context = new Context();
            context.setVariables(map);
            return templateEngine.process(templateName, context);
        }
        catch (Exception e){}
        return null;
    }
}
