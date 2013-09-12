package org.sourcelesslight.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericService<T> {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	public void saveItem(T item)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.save(item);
			tx.commit();
			session.close();
		}
		catch(HibernateException h)
		{
			tx.rollback();
			session.close();
			throw h; 
		}
	}
	
	public void updateItem(T item)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.saveOrUpdate(item);
			tx.commit();
			session.close();
		}
		catch(HibernateException h)
		{
			tx.rollback();
			session.close();
			throw h; 
		}
	}
	
	public T getItem(int itemId) throws HibernateException
	{
		Session session = sessionFactory.openSession();
		try
		{
			@SuppressWarnings("unchecked")
			T item = (T)session.get(getClass(), itemId);
			session.close();
			return item;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
