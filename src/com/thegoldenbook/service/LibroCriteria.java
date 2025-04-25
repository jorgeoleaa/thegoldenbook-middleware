package com.thegoldenbook.service;

import java.util.Date;

import com.thegoldenbook.model.AbstractCriteria;

public class LibroCriteria extends AbstractCriteria{
	
	public static final String ORDER_BY_PRECIO = "PRECIO";
	public static final String ORDER_BY_FECHA = "FECHA_PUBLICACION";
	public static final String ORDER_BY_GENERO_LITERARIO_ID = "GENERO_LITERARIO_ID";
	public static final String ORDER_BY_CLASIFICACION_EDAD_ID = "CLASIFICACION_EDAD_ID";
	public static final String ORDER_BY_UNIDADES = "UNIDADES";
	
	private Long id;
	private String isbn;
	private String nombre;
	private Double desdePrecio;
	private Double hastaPrecio;
	private Integer unidadesDesde;
	private Integer unidadesHasta;
	private Date desdeFecha;
	private Date hastaFecha;
	private Integer generoLiterarioId;
	private Integer clasificacionEdadId;
	private Integer idiomaId;
	private Integer formatoId;
	
	private String locale;
	
	private String orderBy = ORDER_BY_PRECIO;
	private Boolean ascDesc = Boolean.FALSE;
	
	public LibroCriteria() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getDesdePrecio() {
		return desdePrecio;
	}


	public void setDesdePrecio(Double desdePrecio) {
		this.desdePrecio = desdePrecio;
	}


	public Double getHastaPrecio() {
		return hastaPrecio;
	}


	public void setHastaPrecio(Double hastaPrecio) {
		this.hastaPrecio = hastaPrecio;
	}


	public Date getDesdeFecha() {
		return desdeFecha;
	}


	public void setDesdeFecha(Date desdeFecha) {
		this.desdeFecha = desdeFecha;
	}


	public Date getHastaFecha() {
		return hastaFecha;
	}


	public void setHastaFecha(Date hastaFecha) {
		this.hastaFecha = hastaFecha;
	}


	public Integer getGeneroLiterarioId() {
		return generoLiterarioId;
	}


	public void setGeneroLiterarioId(Integer generoLiterarioId) {
		this.generoLiterarioId = generoLiterarioId;
	}


	public Integer getClasificacionEdadId() {
		return clasificacionEdadId;
	}


	public void setClasificacionEdadId(Integer clasificacionEdadId) {
		this.clasificacionEdadId = clasificacionEdadId;
	}


	public Integer getUnidadesDesde() {
		return unidadesDesde;
	}


	public void setUnidadesDesde(Integer unidadesDesde) {
		this.unidadesDesde = unidadesDesde;
	}


	public Integer getUnidadesHasta() {
		return unidadesHasta;
	}


	public void setUnidadesHasta(Integer unidadesHasta) {
		this.unidadesHasta = unidadesHasta;
	}


	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public Boolean getAscDesc() {
		return ascDesc;
	}


	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}


	public static String getOrderByPrecio() {
		return ORDER_BY_PRECIO;
	}


	public static String getOrderByFecha() {
		return ORDER_BY_FECHA;
	}


	public static String getOrderByGeneroLiterarioId() {
		return ORDER_BY_GENERO_LITERARIO_ID;
	}


	public static String getOrderByClasificacionEdadId() {
		return ORDER_BY_CLASIFICACION_EDAD_ID;
	}


	public static String getOrderByUnidades() {
		return ORDER_BY_UNIDADES;
	}


	public Integer getIdiomaId() {
		return idiomaId;
	}


	public void setIdiomaId(Integer idiomaId) {
		this.idiomaId = idiomaId;
	}


	public Integer getFormatoId() {
		return formatoId;
	}


	public void setFormatoId(Integer formatoId) {
		this.formatoId = formatoId;
	}
	
	

}
