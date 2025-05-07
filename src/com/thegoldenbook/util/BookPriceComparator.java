package com.thegoldenbook.util;

import java.util.Comparator;

import com.thegoldenbook.model.Book;

public class BookPriceComparator implements Comparator<Book>{
	
	public BookPriceComparator() {
		
	}
	
	public int compare (Book one, Book other) {
		if(one.getPrice()>other.getPrice()) {
			return 1;
		}else if (one.getPrice()==other.getPrice()) {
			return 0;
		}else {
			return -1;
		}
	}
}
