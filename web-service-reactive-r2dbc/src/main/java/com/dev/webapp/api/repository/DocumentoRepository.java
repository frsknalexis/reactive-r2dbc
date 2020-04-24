package com.dev.webapp.api.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.webapp.api.entity.Documento;

@Repository("documentoRepository")
public interface DocumentoRepository extends ReactiveCrudRepository<Documento, Integer> {

}
