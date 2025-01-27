package com.pinguela.thegoldenbook.util;

import java.util.Comparator;

import com.pinguela.thegoldenbook.model.LibroDTO;

public class LibroIdComparator implements Comparator<LibroDTO>{
	
	private boolean asc = true;
	
	public LibroIdComparator() {
		
	}
	
	public LibroIdComparator (boolean asc) {
		setAsc(asc);
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public int compare (LibroDTO one, LibroDTO other) {
		if(one.getId()>other.getId()) {
			return  asc?-1:1;
		}else if (one.getId()==other.getId()) {
			return 0;
		}else {
			return -1;
		}
	}	
}
