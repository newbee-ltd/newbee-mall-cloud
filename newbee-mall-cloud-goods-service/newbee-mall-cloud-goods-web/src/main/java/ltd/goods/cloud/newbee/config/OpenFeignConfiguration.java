package ltd.goods.cloud.newbee.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfiguration {
    @Bean
    public Logger.Level openFeignLogLevel() {
        // 设置OpenFeign日志级别
        return Logger.Level.FULL;
    }
}
