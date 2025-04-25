package com.thegoldenbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tipo parametrizado para resultados paginados.
 * @param <E> Entidad contenida en el listado.
 */

public class Results<E> {
	
	private int total = 0;
	private List<E> page = null;
	
	public Results() {
		page = new ArrayList<E>();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<E> getPage() {
		return page;
	}

	public void setPage(List<E> page) {
		this.page = page;
	}
	
	
}
