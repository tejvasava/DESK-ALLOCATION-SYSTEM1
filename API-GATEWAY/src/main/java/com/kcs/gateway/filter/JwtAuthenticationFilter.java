package com.kcs.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.kcs.gateway.utils.JwtTokenUtil;
import com.kcs.gateway.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class JwtAuthenticationFilter implements GatewayFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		
		ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

		final List<String> apiEndpoints = List.of("/register","/login");

		Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
				.noneMatch(uri -> r.getURI().getPath().contains(uri));
		
		//for (String string : apiEndpoints) {
			//System.out.println("-->string"+string);
		//}

		if (isApiSecured.test(request)) {
			if (!request.getHeaders().containsKey("Authorization")) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.UNAUTHORIZED);

				return response.setComplete();
			}

			final String requestTokenHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

			String username = null;
			String jwtToken = null;

			// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
			// Token
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					log.info(" JWT Token: {}",jwtToken );
					
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
					
					//log.info("User name From Token: {}",username );
					
				} catch (IllegalArgumentException e) {
					log.info("Unable to get JWT Token: {}", e);
				} catch (ExpiredJwtException e) {
					log.info("JWT Token has expired", e); 
					
				}
			}
			
			// Once we get the token validate it.
			
				jwtTokenUtil.validateToken(jwtToken);

		}

		return chain.filter(exchange);
	}
}
