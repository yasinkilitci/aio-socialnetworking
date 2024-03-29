package org.sourcelesslight.services;

import java.util.List;
import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.sourcelesslight.hashing.SHA256Hasher;
import org.sourcelesslight.model.User;
import org.sourcelesslight.model.enums.AuthType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserService {

	private SessionFactory sessionFactory;
	private SHA256Hasher hasher;
	
	@Transactional(readOnly=true)
	public User getUserById(int userId)
	{
		Session session = sessionFactory.openSession();
		try
		{
			User user = (User)session.get(User.class, userId);
			// Lazy initialization Exception happens if we don't initialize, preferences and theme for user
			Hibernate.initialize(user.getPreferences());
			Hibernate.initialize(user.getPreferences().getTheme());
			session.close();
			return user;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	@Transactional(readOnly=false)
	public void savePreferencesWithUser(User user)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.save(user.getPreferences());
			session.save(user.getConfirmationCode());
			user.setPassword(hasher.encrypt(user.getPassword()));
			session.save(user);
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			tx.rollback();
			session.close();
			throw e;
		}
	}
	
	@Transactional(readOnly=true)
	public User getUserByEmail(String email)
	{
		Session session = sessionFactory.openSession();
		try
		{
			User user = (User)session.createQuery("From USERS WHERE EMAIL=:email")
					.setString("email", email)
					.uniqueResult();
			
			session.close();
			return user;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	@Transactional(readOnly=true)
	public User getUserByUsername(String username)
	{
		Session session = sessionFactory.openSession();
		try
		{
			User user = (User)session.createQuery("From USERS WHERE USERNAME=:username")
					.setString("username", username)
					.uniqueResult();
			Hibernate.initialize(user.getConfirmationCode());
			session.close();
			return user;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> searchUser(String keyword)
	{
		Session session = sessionFactory.openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session); 
		
		// Now we have two options: 
				// 1. Hibernate DSL Query (Simpler)
				// 2. Native Lucene Query (Complex)
		
		SearchFactory sf = fullTextSession.getSearchFactory();
		final QueryBuilder qb = sf.buildQueryBuilder().forEntity(User.class).get();
		
		//Prevent getting all records from database
		// if we pass "*" only, all records will be queried
		if(keyword.length()>2)
			keyword += "*";
		
		org.apache.lucene.search.Query luceneQuery =
			    qb.keyword()
			    	.wildcard()
			        .onField("username").boostedTo(3)
			        .matching(keyword)
			        .createQuery();
		
		
		
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery( luceneQuery );
		
		return (List<User>)fullTextQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> getAllUsers()
	{
		Session session = sessionFactory.openSession();
		try
		{
			List<User> users = (List<User>)session.createQuery("From USERS where AUTHLEVEL=:authtype")
					.setInteger("authtype", AuthType.USER.toInt())
					.list();
			
			session.close();
			return users;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}

	@Transactional(readOnly=false)
	public void updateUser(User user)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			user.setPassword(hasher.encrypt(user.getPassword()));
			session.saveOrUpdate(user);
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			throw e;
		}
	}
	
	public void refreshIndexes()
	{
		Session session = sessionFactory.openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session); 
		try 
		{
			fullTextSession
			.createIndexer( User.class )
			.batchSizeToLoadObjects( 25 )
			.cacheMode( CacheMode.IGNORE )
			.threadsToLoadObjects( 5 )
			.idFetchSize( 150 )
			.threadsForSubsequentFetching( 20 )
			.startAndWait();
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SHA256Hasher getHasher() {
		return hasher;
	}

	public void setHasher(SHA256Hasher hasher) {
		this.hasher = hasher;
	}
	
	
}
