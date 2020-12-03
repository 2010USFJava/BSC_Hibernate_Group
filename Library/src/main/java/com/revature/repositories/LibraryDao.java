package com.revature.repositories;

import java.util.List;

import com.revature.models.Book;
import com.revature.models.Library;

public interface LibraryDao {
	
	public List<Library> findAll();
	
	public Library findByName(String name);
	
	public Library findByLocation(String location);
	
	public List<Library> findByBook(Book book);
	
}
