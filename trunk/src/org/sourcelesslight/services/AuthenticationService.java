package org.sourcelesslight.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sourcelesslight.hashing.SHA256Hasher;
import org.sourcelesslight.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class AuthenticationService {
	
	private SessionFactory sessionFactory;
	private SHA256Hasher hasher;
	
	@Transactional(readOnly=true)
	public User performLogin(String username, String password) throws HibernateException
	{
		try
		{
			Session session = sessionFactory.openSession();
			String hql = "From USERS Where USERNAME=:username and PASSWORD=:password";
			User user = (User)session.createQuery(hql)
					.setString("username", username)
					.setString("password",hasher.encrypt(password))
					.uniqueResult();
			session.close();
			return user;
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

	public SHA256Hasher getHasher() {
		return hasher;
	}

	public void setHasher(SHA256Hasher hasher) {
		this.hasher = hasher;
	}
	
	
}
