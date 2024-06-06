package com.movie.backend.utils;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThymeleafUtils {
        public final SpringTemplateEngine templateEngine;

        private String generateEmailNotice(Map<String, Object> model) {
                Context ctx = new Context();
                List<String> movieNames = (List<String>) model.get("movieNames");
                ctx.setVariable("movieNames", movieNames);

                return templateEngine.process(eThymeleafTemplateName.email_notice.name(), ctx);
        }

        public String generate(eThymeleafTemplateName templateName, Map<String, Object> model) {
                switch (templateName) {
                        case email_notice:
                                return generateEmailNotice(model);

                        default:
                                return null;
                }

        }
}
