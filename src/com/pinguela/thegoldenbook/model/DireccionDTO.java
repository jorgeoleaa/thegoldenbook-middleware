package com.pinguela.thegoldenbook.model;

public class DireccionDTO extends AbstractValueObject{
	
	private Long id;
	private String nombreVia;
	private String dirVia;
	private Long clienteId;
	private Long empleadoId;
	private Integer localidadId;
	private String localidadNombre;
	private Integer provinciaId;
	private String provinciaNombre;
	private Integer paisId;
	private String paisNombre;
	
	
	public DireccionDTO (){
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public String getDirVia() {
		return dirVia;
	}

	public void setDirVia(String dirVia) {
		this.dirVia = dirVia;
	}
	
	public Integer getLocalidadId() {
		return localidadId;
	}

	public void setLocalidadId(Integer localidadId) {
		this.localidadId = localidadId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getLocalidadNombre() {
		return localidadNombre;
	}

	public void setLocalidadNombre(String localidadNombre) {
		this.localidadNombre = localidadNombre;
	}

	public Integer getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}

	public String getPaisNombre() {
		return paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	

	
	
}
