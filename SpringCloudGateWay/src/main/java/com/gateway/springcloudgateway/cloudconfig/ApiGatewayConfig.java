package com.gateway.springcloudgateway.cloudconfig;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
@RibbonClients(defaultConfiguration = RibbonConfig.class)
public class ApiGatewayConfig  {



    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("MICROSERVICESBLOG-SERVICE", r -> r.path("/auth/**")
                        .uri("lb://MICROSERVICESBLOG-SERVICE"))
                .route("MICROSERVICESBLOG-SERVICE", r -> r.path("/blog/**")
                        .uri("lb://MICROSERVICESBLOG-SERVICE"))
                .build();
    }


//    @Bean
//    public GatewayProperties gatewayProperties() {
//        return new GatewayProperties();
//    }
}
