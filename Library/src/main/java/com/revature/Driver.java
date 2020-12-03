package com.revature;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Book;
import com.revature.models.Library;
import com.revature.repositories.BookDaoImpl;
import com.revature.repositories.LibraryDaoImpl;
import com.revature.util.LibraryUtil;

public class Driver {

	public static void main(String[] args) {
		Library library = new Library("City Library", "Springfield", new ArrayList<>());
		Book book = new Book("A Tale of Two Cities", new ArrayList<>());
		library.getBooks().add(book);
		book.getLibraries().add(library);

		Session s = LibraryUtil.getSession();

		Transaction tx = s.beginTransaction();

		s.save(library);
		s.save(book);

		tx.commit();

		Library libFromDb = s.get(Library.class, "City Library");
		Book bookFromDb = s.get(Book.class, "A Tale of Two Cities");

		LibraryDaoImpl ldi = new LibraryDaoImpl();
		BookDaoImpl bdi = new BookDaoImpl();

		List<Library> libList = ldi.findAll();
		List<Book> bookList = bdi.findAll();

		System.out.println("Library = " + library + "\r\nlibFromDb = " + libFromDb + "\r\nlibList = " + libList);

		System.out.println("Book = " + book + "\r\nbookFromDb = " + bookFromDb + "\r\nbookList = " + bookList);
		
		TypedQuery<Book> query = s.createNamedQuery("getBookByLibraryLocation", Book.class);
		query.setParameter("location", "Springfield");
		List<Book> springfieldBooks = query.getResultList();
		System.out.println(springfieldBooks);
	}

}
