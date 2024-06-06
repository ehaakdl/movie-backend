package com.movie.backend.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThymeleafUtils {
        public final SpringTemplateEngine templateEngine;

        private String generateEmailNotice(Map<String, String> model) {
                Context ctx = new Context();

                for (Map.Entry<String, String> item : model.entrySet()) {
                        ctx.setVariable(item.getKey(), item.getValue());
                }

                return templateEngine.process(eThymeleafTemplateName.email_notice.name(), ctx);
        }

        public String generate(eThymeleafTemplateName templateName, Map<String, String> values) {
                switch (templateName) {
                        case email_notice:
                                return generateEmailNotice(values);

                        default:
                                return null;
                }

        }
}
