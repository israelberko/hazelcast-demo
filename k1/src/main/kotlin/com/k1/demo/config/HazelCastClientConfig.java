package com.k1.demo.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.client.config.ClientUserCodeDeploymentConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.k1.demo.domain.Blog;
//import com.k1.demo.domain.Clog;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter;
import org.springframework.data.keyvalue.core.KeyValueTemplate;

@Configuration
public class HazelCastClientConfig {

//    @Bean
//    public ClientConfig clientConfig() {
//        ClientConfig clientConfig = new ClientConfig();
//        ClientUserCodeDeploymentConfig clientUserCodeDeploymentConfig = clientConfig.getUserCodeDeploymentConfig();
//        clientUserCodeDeploymentConfig.addClass("com.k1.demo.domain.Blog");
//        clientUserCodeDeploymentConfig.setEnabled(true);
//
//        clientConfig.setUserCodeDeploymentConfig(clientUserCodeDeploymentConfig);
//
//
//        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
//        networkConfig.addAddress("192.168.13.209:5701", "192.168.13.209:5701")
//                .setSmartRouting(true)
//                .addOutboundPortDefinition("34700-34710")
//                .setRedoOperation(true)
//                .setConnectionTimeout(5000);
//
//        return clientConfig;
//
//    }
//
//    @Bean
//    public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
//        return HazelcastClient.newHazelcastClient(clientConfig);
//    }

    @Bean
    public HazelcastInstance hazelcastInstance() {

        ClientConfig clientConfig = new ClientConfig();
        ClientUserCodeDeploymentConfig clientUserCodeDeploymentConfig = clientConfig.getUserCodeDeploymentConfig();
        clientUserCodeDeploymentConfig.addClass(Blog.class);

        clientUserCodeDeploymentConfig.addClass(Blog.Companion.class);
//        clientUserCodeDeploymentConfig.addClass("com.k1.demo.domain.Blog");
        clientUserCodeDeploymentConfig.setEnabled(true);

        clientConfig.setClassLoader(Blog.class.getClassLoader());

        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig.addAddress("192.168.13.209:5701", "192.168.13.209:5701")
                .setSmartRouting(true)
                .addOutboundPortDefinition("34700-34710")
                .setRedoOperation(true)
                .setConnectionTimeout(5000);

        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public KeyValueTemplate keyValueTemplate() {
        return new KeyValueTemplate(new HazelcastKeyValueAdapter(hazelcastInstance()));
    }



    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}
