package com.sim.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoshun.cxs
 * 12/28/2020
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    /**
     * serviceIp
     */
    private String host = "127.0.0.1";

    /**
     * remote server port
     */
    private Integer port = 8888;

}
