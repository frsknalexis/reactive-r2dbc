package com.dev.webapp.api.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "tbl_documentos")
public class Documento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2514007727837780085L;

	@Id
	@Column(value = "documento_id")
	private Integer documentoId;
	
	@Column(value = "abreviatura")
	private String abreviatura;
	
	@Column(value = "nombre_documento")
	private String nombreDocumento;
	
	@Column(value = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(value = "fecha_modificacion")
	private Date fechaModificacion;
	
	@Column(value = "habilitado")
	private Boolean habilitado;
}