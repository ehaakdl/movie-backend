package com.movie.backend.utils;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThymeleafUtils {
        public final SpringTemplateEngine templateEngine;

        public String generate(eThymeleafTemplateName templateName, Map<String, Object> model) {
                Context ctx = new Context();
                
                ctx.setVariables(model);

                return templateEngine.process(templateName.name(), ctx);
        }
}
