package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class RedisApplication
{
    public static void main( String[] args ) {

        Properties systemProps = System.getProperties();
        //systemProps.put("javax.net.debug", "ssl,handshake"); // this configuration will enable the debug output of the SSL handshake to stdout
        //systemProps.put("javax.net.ssl.keyStore","C:\\CERTS\\NEW-CERTS\\clientCredKeystore.jks");
        //systemProps.put("javax.net.ssl.keyStorePassword","123456789");
        systemProps.put("javax.net.ssl.trustStore", "/home/coder/project/code/spring-redis-tls/tests/tls/client-truststore.jks");
        systemProps.put("javax.net.ssl.trustStorePassword","whatever");
        System.setProperties(systemProps);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = applicationContext.getBean("redisTemplate",
                RedisTemplate.class);
        System.out.println(redisTemplate.getClientList().get(0));
        redisTemplate.opsForValue().set("key1", "hello world");
       System.out.println(redisTemplate.opsForValue().get("key1"));
    }
}
