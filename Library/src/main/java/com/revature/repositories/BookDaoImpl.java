package com.revature.repositories;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.models.Book;
import com.revature.models.Library;
import com.revature.util.LibraryUtil;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> findAll() {
		Session s = LibraryUtil.getSession();
		Query query = s.createQuery("from Library l", Library.class);
		return (List<Book>) query.getResultList();
	}

	@Override
	public Book findByTitle(String title) {
		Session s = LibraryUtil.getSession();
		Query query = s.createQuery("from Book B where b.title = :title", Library.class);
		query.setParameter("title", title);
		Book b = (Book) query.getSingleResult();
		return b;
	}

	@Override
	public List<Book> findByLibrary(Library library) {
		Session s = LibraryUtil.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		Join<Library, Book> join = root.join("libraries", JoinType.INNER);
		query.select(root).where(builder.equal(join.get("name"), library.getName()));
		return s.createQuery(query).getResultList();
	}
	
}
