package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Library;
import com.revature.repositories.LibraryDaoImpl;
import com.revature.util.LibraryUtil;

public class Driver {

	public static void main(String[] args) {
		Library library = new Library("City Library", "Springfield", new ArrayList<>());
		Session s = LibraryUtil.getSession();

		Transaction tx = s.beginTransaction();

		s.save(library);
		tx.commit();

		Library libFromDb = s.get(Library.class, "City Library");

		LibraryDaoImpl ldi = new LibraryDaoImpl();

		List<Library> libList = ldi.findAll();

		System.out.println("Library = " + library + "\r\nlibFromDb = " + libFromDb + "\r\nlibList = " + libList);
	}

}
