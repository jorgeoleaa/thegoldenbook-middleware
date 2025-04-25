package com.thegoldenbook.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibroDTO extends AbstractValueObject {
	
	private Long id;
	private String isbn;
	private String nombre;
	private Date fechaPublicacion;
	private String sinopsis;
	private Double valoracionMedia;
	private Integer unidades;
	private Double precio;
	private Integer generoLiterarioId;
	private String generoLiterarioNombre;
	private Integer clasificacionEdadId;
	private String clasificacionEdadNombre;
	private Integer idiomaId;
	private String idiomaNombre;
	private Integer formatoId;
	private String formatoNombre;
	private List<Author> autores;
	private List<Tematica> tematicas;

	
	public LibroDTO() {
		autores = new ArrayList<Author>();
		tematicas = new ArrayList<Tematica>();
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

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getGeneroLiterarioId() {
		return generoLiterarioId;
	}

	public void setGeneroLiterarioId(Integer generoLiterarioId) {
		this.generoLiterarioId = generoLiterarioId;
	}

	public String getGeneroLiterarioNombre() {
		return generoLiterarioNombre;
	}

	public void setGeneroLiterarioNombre(String generoLiterarioNombre) {
		this.generoLiterarioNombre = generoLiterarioNombre;
	}

	public Integer getClasificacionEdadId() {
		return clasificacionEdadId;
	}

	public void setClasificacionEdadId(Integer clasificacionEdadId) {
		this.clasificacionEdadId = clasificacionEdadId;
	}

	public String getClasificacionEdadNombre() {
		return clasificacionEdadNombre;
	}

	public void setClasificacionEdadNombre(String clasificacionEdadNombre) {
		this.clasificacionEdadNombre = clasificacionEdadNombre;
	}
	
	public Integer getIdiomaId() {
		return idiomaId;
	}

	public void setIdiomaId(Integer idiomaId) {
		this.idiomaId = idiomaId;
	}

	public String getIdiomaNombre() {
		return idiomaNombre;
	}

	public void setIdiomaNombre(String idiomaNombre) {
		this.idiomaNombre = idiomaNombre;
	}

	public Integer getFormatoId() {
		return formatoId;
	}

	public void setFormatoId(Integer formatoId) {
		this.formatoId = formatoId;
	}

	public String getFormatoNombre() {
		return formatoNombre;
	}

	public void setFormatoNombre(String formatoNombre) {
		this.formatoNombre = formatoNombre;
	}

	public List<Author> getAutores() {
		return autores;
	}

	public void setAutores(List<Author> autores) {
		this.autores = autores;
	}

	public List<Tematica> getTematicas() {
		return tematicas;
	}

	public void setTematicas(List<Tematica> tematicas) {
		this.tematicas = tematicas;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Double getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(Double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}
}