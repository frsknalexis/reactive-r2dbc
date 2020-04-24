package com.dev.webapp.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev.webapp.api.entity.Documento;
import com.dev.webapp.api.repository.DocumentoRepository;
import com.dev.webapp.api.service.DocumentoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("documentoService")
public class DocumentoServiceImpl implements DocumentoService {

	@Autowired
	@Qualifier("documentoRepository")
	private DocumentoRepository documentoRepository;
	
	@Override
	public Flux<Documento> findAll() {	
		Flux<Documento> documentoFlux = documentoRepository.findAll();
		return documentoFlux;
	}

	@Override
	public Mono<Documento> findById(Integer documentoId) {
		Mono<Documento> documentoMono = Mono.empty();
		if (documentoId != null && documentoId.intValue() > 0) {
			documentoMono = documentoRepository.findById(documentoId);
		}
		return documentoMono;
	}

	@Override
	public Mono<Documento> saveOrUpdate(Documento documento) {
		if (documento.getDocumentoId().intValue() > 0) {
			Mono<Documento> documentoUpdate = documentoRepository.findById(documento.getDocumentoId());
			return documentoUpdate.flatMap((d) -> {
				d.setAbreviatura(documento.getAbreviatura());
				d.setNombreDocumento(documento.getNombreDocumento());
				
				d.setFechaModificacion(new Date());
				Mono<Documento> documentoSave = documentoRepository.save(d);
				return documentoSave;
			});
		} else {
			documento.setFechaRegistro(new Date());
			documento.setHabilitado(true);
			return documentoRepository.save(documento);
		}
	}

	@Override
	public Mono<Void> delete(Integer documentoId) {
		Mono<Documento> documentoMono = Mono.empty();
		if (documentoId != null && documentoId.intValue() > 0) {
			documentoMono = documentoRepository.findById(documentoId);
		}
		return documentoMono.flatMap((d) -> {
			return documentoRepository.delete(d);
		});
	}
}