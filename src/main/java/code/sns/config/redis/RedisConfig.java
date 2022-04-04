package code.sns.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
//@EnableRedisRepositories
public class RedisConfig {

//    @Value("${spring.redis.port}")
//    public int port;
//
//    @Value("${spring.redis.host}")
//    public String host;
//
//    @Value("${spring.redis.username}")
//    public String username;
//
//    @Value("${spring.redis.password}")
//    public String password;


//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(host,port);
//    }
//
//
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//        RedisTemplate<byte[],byte[]> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());
//
//        return template;
//    }

}
