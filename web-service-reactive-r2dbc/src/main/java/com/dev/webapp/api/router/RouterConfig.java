package com.dev.webapp.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.dev.webapp.api.handler.DocumentoHandler;

@Configuration
public class RouterConfig {

	@Bean
	public RouterFunction<ServerResponse> routesDocumento(DocumentoHandler handler) {
		return RouterFunctions.route(RequestPredicates.GET("/api/v1/documento/documentos"), handler::findAll)
				.andRoute(RequestPredicates.GET("/api/v1/documento/{documentoId}"), handler::getOneById)
				.andRoute(RequestPredicates.POST("/api/v1/documento"), handler::saveOrUpdate)
				.andRoute(RequestPredicates.DELETE("/api/v1/documento/{documentoId}"), handler::delete);
	}
}
