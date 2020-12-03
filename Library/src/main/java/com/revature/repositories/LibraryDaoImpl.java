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

public class LibraryDaoImpl implements LibraryDao {

	@Override
	public List<Library> findAll() {
		Session s = LibraryUtil.getSession();
		Query query = s.createQuery("from Library l", Library.class);
		return (List<Library>) query.getResultList();
	}

	@Override
	public Library findByName(String name) {
		Session s = LibraryUtil.getSession();
		Query query = s.createQuery("from Library l where l.name = :name", Library.class);
		query.setParameter("name", name);
		Library l = (Library) query.getSingleResult();
		return l;
	}

	@Override
	public Library findByLocation(String location) {
		Session s = LibraryUtil.getSession();
		Query query = s.createQuery("from Library l where l.name = :location", Library.class);
		query.setParameter("location", location);
		Library l = (Library) query.getSingleResult();
		return l;
	}

	@Override
	public List<Library> findByBook(Book book) {
		Session s = LibraryUtil.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Library> query = builder.createQuery(Library.class);
		Root<Library> root = query.from(Library.class);
		Join<Library, Book> join = root.join("books", JoinType.INNER);
		query.select(root).where(builder.equal(join.get("title"), book.getTitle()));
		return s.createQuery(query).getResultList();
	}
	
	

}
