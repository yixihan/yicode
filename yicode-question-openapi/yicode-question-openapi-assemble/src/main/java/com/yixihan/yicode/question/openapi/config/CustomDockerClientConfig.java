package com.yixihan.yicode.question.openapi.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * docker client 配置类
 *
 * @author yixihan
 * @date 2023/2/1 16:42
 */
@Configuration
public class CustomDockerClientConfig {
    
    @Value ("${docker.host}")
    private String host;
    
    @Value ("${docker.api-version}")
    private String apiVersion;
    
    @Value ("${docker.tls.verify}")
    private Boolean tlsVerify;
    
    @Value ("${docker.tls.path}")
    private String certPath;
    
    @Value ("${docker.register.enable}")
    private Boolean enableRegister;
    
    @Value ("${docker.register.url}")
    private String registryUrl;
    
    @Value ("${docker.register.username}")
    private String registryUsername;
    
    @Value ("${docker.register.password}")
    private String registryPassword;
    
    @Value ("${docker.max-connection}")
    private Integer maxConnection;
    
    @Value ("${docker.connection-timeout}")
    private Integer connectionTimeout;
    
    @Value ("${docker.response-timeout}")
    private Integer responseTimeout;
    
    /**
     * docker client config 配置
     */
    @Bean("dockerClientConfig")
    public DockerClientConfig dockerClientConfig () {
        DefaultDockerClientConfig.Builder builder = DefaultDockerClientConfig.createDefaultConfigBuilder ();
        
        builder
                // host
                .withDockerHost(host)
                // api version
                .withApiVersion (apiVersion)
                // tls
                .withDockerTlsVerify(tlsVerify);
        
        // certPath
        if (tlsVerify) {
            builder.withDockerCertPath (certPath);
        }
        
        // register
        if (enableRegister) {
            builder.withRegistryUrl (registryUrl)
                    .withRegistryUsername (registryUsername)
                    .withRegistryPassword (registryPassword);
        }
        
        return builder.build ();
    }
    
    /**
     * docker http client 配置
     */
    @Bean("dockerHttpClient")
    public DockerHttpClient dockerHttpClient(@Qualifier("dockerClientConfig")DockerClientConfig config) {
        return new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(maxConnection)
                .connectionTimeout(Duration.ofSeconds(connectionTimeout))
                .responseTimeout(Duration.ofSeconds(responseTimeout))
                .build();
    }
    
    /**
     * docker client 配置
     */
    @Bean("dockerClient")
    public DockerClient dockerClient (@Qualifier("dockerClientConfig")DockerClientConfig config,
                                      @Qualifier("dockerHttpClient")DockerHttpClient httpClient) {
        return DockerClientImpl.getInstance(config, httpClient);
    }
    
}
