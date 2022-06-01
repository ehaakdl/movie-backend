package com.mose.movie.config;


import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class JasyptConfig {
    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        final String JASYPT_PASSWD_ENV = "JASYPT_PASSWD";
        String key = System.getenv(JASYPT_PASSWD_ENV);
        String algorithm = "PBEWithMD5AndDES";

        if (key == null) {
            String errorMsg = JASYPT_PASSWD_ENV + " 환경변수 값이 NULL 입니다.";
            log.error(errorMsg, new NullPointerException());
            throw new NullPointerException(errorMsg);
        }

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key);
        config.setAlgorithm(algorithm);
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        return encryptor;
    }
}