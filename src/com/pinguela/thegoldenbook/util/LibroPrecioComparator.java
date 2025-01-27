package com.pinguela.thegoldenbook.util;

import java.util.Comparator;

import com.pinguela.thegoldenbook.model.LibroDTO;

public class LibroPrecioComparator implements Comparator<LibroDTO>{
	
	public LibroPrecioComparator() {
		
	}
	
	public int compare (LibroDTO one, LibroDTO other) {
		if(one.getPrecio()>other.getPrecio()) {
			return 1;
		}else if (one.getPrecio()==other.getPrecio()) {
			return 0;
		}else {
			return -1;
		}
	}
}
