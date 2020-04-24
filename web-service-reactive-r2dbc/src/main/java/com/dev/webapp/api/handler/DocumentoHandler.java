package com.dev.webapp.api.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.dev.webapp.api.entity.Documento;
import com.dev.webapp.api.service.DocumentoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DocumentoHandler {

	@Autowired
	@Qualifier("documentoService")
	private DocumentoService documentoService;
	
	public Mono<ServerResponse> findAll(ServerRequest request) {
		Flux<Documento> documentoFlux = documentoService.findAll();
		return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(documentoFlux, Documento.class)
		.switchIfEmpty(ServerResponse.noContent().build());
	}
	
	public Mono<ServerResponse> getOneById(ServerRequest request) {
		String documentoId = request.pathVariable("documentoId");
		Mono<Documento> documentoMono = documentoService.findById(Integer.valueOf(documentoId));
		return documentoMono
				.flatMap((documento) -> {
					return ServerResponse.ok()
								.contentType(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromValue(documento));
				})
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> saveOrUpdate(ServerRequest request) {
		Mono<Documento> documentoMono = request.bodyToMono(Documento.class);
		return documentoMono
						.flatMap((d) -> {
							return documentoService.saveOrUpdate(d);
						})
						.flatMap((d) -> {
							return ServerResponse
									.created(URI.create("/api/v1/documento/".concat(d.getDocumentoId().toString())))
									.contentType(MediaType.APPLICATION_JSON)
									.body(BodyInserters.fromValue(d));
						});
	}
	
	public Mono<ServerResponse> delete(ServerRequest request) {
		String documentoId = request.pathVariable("documentoId");
		Mono<Documento> documentoMono = documentoService.findById(Integer.valueOf(documentoId));
		return documentoMono
					.flatMap((d) -> {
						return documentoService.delete(d.getDocumentoId())
								.then(ServerResponse.noContent().build());
					})
					.switchIfEmpty(ServerResponse.notFound().build());
	}
}