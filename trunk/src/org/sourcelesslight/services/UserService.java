package org.sourcelesslight.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sourcelesslight.hashing.SHA256Hasher;
import org.sourcelesslight.model.Preferences;
import org.sourcelesslight.model.User;
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
			session.close();
			return user;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	@Transactional(readOnly=false)
	public void savePreferencesWithUser(User user,Preferences preferences)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.saveOrUpdate(preferences);
			user.setPreferences(preferences);
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
			
			session.close();
			return user;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	

	@Transactional(readOnly=false)
	public void saveUser(User user)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			user.setPassword(hasher.encrypt(user.getPassword()));
			session.save(user);
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			throw e;
		}
	}
	
	@Transactional(readOnly=false)
	public void deleteUser(User user)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.delete(user);
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			throw e;
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
