package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.security.NoSuchAlgorithmException;

@Configuration

public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() throws java.security.NoSuchAlgorithmException {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName("127.0.0.1");
        standaloneConfig.setPort(6379);
        SSLParameters sslParameters = new SSLParameters();
        sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
        sslParameters.setNeedClientAuth(false);
        sslParameters.setProtocols(new String[]{"TLSv1.2"});

        SSLContext context = SSLContext.getDefault();
        SSLSocketFactory socketFactory = context.getSocketFactory();
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder() //
                .useSsl()
                .sslParameters(sslParameters)
                .sslSocketFactory(socketFactory).and()
                .clientName("redis-client")
                .usePooling().poolConfig(poolConfig)
                .build();

        return new JedisConnectionFactory(standaloneConfig, jedisClientConfiguration);
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, String> redisTemplate() throws NoSuchAlgorithmException {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }


}