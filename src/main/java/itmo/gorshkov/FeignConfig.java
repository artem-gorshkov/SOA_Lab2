package itmo.gorshkov;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor contentTypeFilter() {
        return new ContentTypeInterceptor();
    }
}
