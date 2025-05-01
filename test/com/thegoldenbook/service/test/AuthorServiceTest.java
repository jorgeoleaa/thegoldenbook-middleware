package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.AuthorService;
import com.thegoldenbook.service.impl.AuthorServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class AuthorServiceTest {
	
	private static Logger logger = LogManager.getLogger(AuthorServiceTest.class);
	private AuthorService authorService = null;
	
	public AuthorServiceTest() {
		authorService = new AuthorServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		Results<Author> authors = authorService.findAll(1, 500);
		
		for(Author a : authors.getPage()) {
			logger.trace(a);
		}
	}
	
	public void testFindByAuthor() throws Exception{
		logger.traceEntry("Testing findByAuthor...");
		Author a = authorService.findByAuthor(2l);
		
		if(a.getId() == null) {
			logger.trace("No authors were found for the provided ID.");
		}else {
			logger.info(a);
		}
		
	}
	
	public void testFindByBook() throws Exception{
		logger.traceEntry("Testing findByBook...");
		List<Author> results = authorService.findByBook(3l);
		
		if(results.isEmpty()) {
			logger.trace("No authors were found for the provided ID.");
		}else {
			logger.info(results);
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Author a = new Author();
		a.setName("Haruki");
		a.setLastName("Murakami");
		a.setDateOfBirth(DateUtils.getDate(1949, 0, 12));
		a.setSecondLastName(null);
		authorService.create(a);
		
		logger.trace("The author with id "+a.getId()+" has been successfully created.");
		
		
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Author a = authorService.findByAuthor(29l);
		a.setLastName("aaaaaa");
		authorService.update(a);
		logger.trace("The author with id "+a.getId()+" has been seccessfully updated");
	}
	
	public static void main (String [] args) throws Exception{
		
		AuthorServiceTest authorTest = new AuthorServiceTest();
		//autorTest.testFindAll();
		authorTest.testFindByAuthor();
		//autorTest.testFindByBook();
		//autorTest.testCreate();
		//autorTest.testUpdate();
	}
}
