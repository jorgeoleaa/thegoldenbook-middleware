package com.thegoldenbook.util;

import java.util.Comparator;

import com.thegoldenbook.model.Book;

public class LibroPrecioComparator implements Comparator<Book>{
	
	public LibroPrecioComparator() {
		
	}
	
	public int compare (Book one, Book other) {
		if(one.getPrecio()>other.getPrecio()) {
			return 1;
		}else if (one.getPrecio()==other.getPrecio()) {
			return 0;
		}else {
			return -1;
		}
	}
}
