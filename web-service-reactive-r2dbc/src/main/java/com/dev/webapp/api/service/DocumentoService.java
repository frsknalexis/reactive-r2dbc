package com.dev.webapp.api.service;

import com.dev.webapp.api.entity.Documento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentoService {

	Flux<Documento> findAll();
	
	Mono<Documento> findById(Integer documentoId);
	
	Mono<Documento> saveOrUpdate(Documento documento);
	
	Mono<Void> delete(Integer documentoId);
}
