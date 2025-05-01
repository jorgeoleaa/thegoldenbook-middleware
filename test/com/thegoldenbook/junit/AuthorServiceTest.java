package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.AuthorService;
import com.thegoldenbook.service.impl.AuthorServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class AuthorServiceTest {

	private AuthorService authorService = null;
	
	public AuthorServiceTest() {
		authorService = new AuthorServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		Results<Author> autores = authorService.findAll(1, Integer.MAX_VALUE);
		assertEquals(28, autores.getTotal());
	}
	
	@Test
	public void testFindByAutorId01() throws Exception{
		Author a = authorService.findByAuthor(2l);
		assertEquals(2, a.getId());
	}
	
	@Test
	public void testFindByAutorId02() throws Exception{
		Author a = authorService.findByAuthor(0l);
		assertEquals(null, a);
	}
	
	@Test
	public void testFindByLibro01() throws Exception{
		List<Author> autores = authorService.findByBook(3l);
		assertEquals(1, autores.size());
	}
	@Test
	public void testFindByLibro02() throws Exception{
		List<Author> autores = authorService.findByBook(0l);
		assertEquals(0, autores.size());
	}
	
	@Test
	public void testUpdate() throws Exception{
		Author a = authorService.findByAuthor(2l);
		a.setName("AAAAAAAAAAAAAAAAAA");
		authorService.update(a);
		assertEquals(a.getName(), authorService.findByAuthor(2l).getName());
	}
	
	@Test
	public void testCreate() throws Exception{
		Author a = new Author();
		a.setId(34l);
		a.setName("Michael");
		a.setLastName("Connelly");
		a.setDateOfBirth(DateUtils.getDate(1956, 06, 21));
		Long id = authorService.create(a);
		
		assertNotNull(id);
	}
}
