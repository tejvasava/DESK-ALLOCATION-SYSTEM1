package com.kcs.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kcs.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("ORGANIZATION-SERVICE", r -> r.path("/Orgnization/**").filters(f -> f.filter(filter)).uri("lb://ORGANIZATION-SERVICE"))
				.route("USER-SERVICE", r -> r.path("/user/**").filters(f -> f.filter(filter)).uri("lb://USER-SERVICE"))
				.route("FLOOR-SERVICE", r -> r.path("/floor/**").filters(f -> f.filter(filter)).uri("lb://FLOOR-SERVICE"))
				.route("DESK-SERVICE", r -> r.path("/desk/**").filters(f -> f.filter(filter)).uri("lb://DESK-SERVICE"))
				.route("OFFICE-SERVICE", r -> r.path("/office/**").filters(f -> f.filter(filter)).uri("lb://OFFICE-SERVICE"))
				.build();
	}
}
