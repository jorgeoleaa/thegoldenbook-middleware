package com.thegoldenbook.model;

public class Localidad extends AbstractValueObject{

	private Integer id;
	private String nombre;
	private Integer codigoPostal;
	private Integer provinciaId;

	public Localidad() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Localidad localidad = (Localidad) obj;
		return codigoPostal == localidad.codigoPostal &&
				(nombre != null ? nombre.equals(localidad.nombre) : localidad.nombre == null);
	}
	
    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + codigoPostal;
        return result;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Integer getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}
}
