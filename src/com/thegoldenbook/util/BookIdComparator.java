package com.thegoldenbook.util;

import java.util.Comparator;

import com.thegoldenbook.model.Book;

public class BookIdComparator implements Comparator<Book>{
	
	private boolean asc = true;
	
	public BookIdComparator() {
		
	}
	
	public BookIdComparator (boolean asc) {
		setAsc(asc);
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public int compare (Book one, Book other) {
		if(one.getId()>other.getId()) {
			return  asc?-1:1;
		}else if (one.getId()==other.getId()) {
			return 0;
		}else {
			return -1;
		}
	}	
}
