package com.gateway.springcloudgateway.cloudconfig;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Configuration
public class RibbonConfig {
    @Bean
    public IRule ribbonRule() {
        return new CustomLoadBalancerConfiguration();
    }
}
