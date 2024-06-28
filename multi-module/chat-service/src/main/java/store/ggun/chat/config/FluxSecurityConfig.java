package store.ggun.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class FluxSecurityConfig {
	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		return http
			.authorizeExchange((authorize) -> authorize
				.anyExchange().permitAll()
			)
			.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
			.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
			.csrf(ServerHttpSecurity.CsrfSpec::disable)
			.cors(ServerHttpSecurity.CorsSpec::disable)
			.build();
		// @formatter:on
	}
}
