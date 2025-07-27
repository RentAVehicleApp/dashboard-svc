package rent.vehicle.dashboardserviceapi.common.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServiceConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // Разрешить ВСЕ домены
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean ("deviceWebClient")
    public WebClient deviceWebClient() {
        return WebClient.builder()
                .baseUrl("https://device-service.onrender.com")
//                .baseUrl("http://localhost:8080")
                .build();
    }

    @Bean ("customerServiceWebClient")
    public WebClient customerServiceWebClient() {
        return WebClient.builder()
                .baseUrl("https://user-service-xt20.onrender.com")
                .build();
    }
    @Bean ("workerServiceWebClient")
    public WebClient workerServiceWebClient() {
        return WebClient.builder()
                .baseUrl("https://worker-service-c4g6.onrender.com")
                .build();
    }


}

/* Вариант 2: Хранить origins в БД и проверять в CORS фильтре
Сделай собственный CorsFilter, который проверяет Origin в заголовке запроса.

Разрешённые адреса храни в БД или Redis — можно менять без перезапуска.
https://chatgpt.com/share/6852c4ce-e7a8-800b-bcb5-57e2259a11a1
*/