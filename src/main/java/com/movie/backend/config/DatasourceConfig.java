package com.movie.backend.config;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatasourceConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource datasource() {
        Class<?> driverClass;
        try {
            driverClass = Class.forName(driverClassName);
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            dataSource.setDriverClass(driverClass.asSubclass(Driver.class));
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            log.info("jdbc 드라이버 클래스명을 확인해주세요 {}", driverClassName);
            throw new BeanCreationException("jdbc 드라이버 클래스명을 확인해주세요 " + driverClassName, e);
        }

    }
}