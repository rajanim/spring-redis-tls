# Version compatibility
Jedis version is 3.3.0  
Spring Data Redis is 2.3.5.RELEASE
Jedis and Spring Data Redis version compatibility is necessary otherwise you will encounter exceptions.
checkout this blog for further details 
https://www.bswen.com/2021/03/springboot-how-to-solve-ClassNotFoundException-redis-clients-jedis-util-Pool.html

# Java client setup for one-way ssl
## Java Keystore

 On the client machine in the same directory where you downloaded the proxy cert. Proxy cert is typically present under  /etc/opt/redislabs/ on any RE node.
 Execute the following commands to configure the Java keystore (https://docs.oracle.com/cd/E19830-01/819-4712/ablqw/

## Create a truststore

keytool -genkey -dname "cn=CLIENT" -alias truststorekey -keyalg RSA -keystore ./client-truststore.jks -keypass
whatever -storepass whatever

### Add proxy cert to the truststore

keytool -import -keystore ./client-truststore.jks -file ./proxy_cert.pem -alias trustedproxy

### Java system properties for SSL
When running your java application, set the following JVM system properties.

Properties systemProps = System.getProperties();
//systemProps.put("javax.net.debug", "ssl,handshake"); // this configuration will enable the debug output of the SSL handshake to stdout
//systemProps.put("javax.net.ssl.keyStore","C:\\CERTS\\NEW-CERTS\\clientCredKeystore.jks");
//systemProps.put("javax.net.ssl.keyStorePassword","123456789");
systemProps.put("javax.net.ssl.trustStore", "/home/coder/project/code/spring-redis-tls/tests/tls/client-truststore.jks");
systemProps.put("javax.net.ssl.trustStorePassword","whatever");
System.setProperties(systemProps);