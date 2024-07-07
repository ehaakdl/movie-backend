package com.movie.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig {
        public static final String prefix = "templates/";
        public static final String suffix = ".html";
        public static final String templateMode = "HTML";

        @Bean
        public SpringTemplateEngine springTemplateEngine() {
                SpringTemplateEngine templateEngine = new SpringTemplateEngine();
                ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
                templateResolver.setPrefix(prefix);
                templateResolver.setSuffix(suffix);
                templateResolver.setTemplateMode(templateMode);
                templateResolver.setCacheable(false);

                // https://github.com/thymeleaf/thymeleaf/issues/606
                templateResolver.setForceTemplateMode(true);

                templateEngine.setTemplateResolver(templateResolver);

                return templateEngine;
        }
}
