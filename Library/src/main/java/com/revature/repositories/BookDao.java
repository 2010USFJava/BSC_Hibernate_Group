package com.revature.repositories;

import java.util.List;

import com.revature.models.Book;
import com.revature.models.Library;

public interface BookDao {
	
	public List<Book> findAll();

	public Book findByTitle(String title);

	public List<Book> findByLibrary(Library library);
	
}
