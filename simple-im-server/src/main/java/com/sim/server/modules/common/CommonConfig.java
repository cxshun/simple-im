package com.sim.server.modules.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoshun.cxs
 * 12/28/2020
 **/
@Configuration
@ConfigurationProperties(prefix = "server")
@Data
public class CommonConfig {

    /**
     * 端口
     */
    private Integer port = 8080;

}
