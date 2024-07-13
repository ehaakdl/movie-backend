### cors 허용 설정해도 cors 에러 발생


아래와 같이 설정해야 에러 cors 설정이 옳바르게 설정된다. allowCredentials 기능 사용하면 cors 해제 설정이 안먹힌다.
```
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .maxAge(3600);

    }
}
```