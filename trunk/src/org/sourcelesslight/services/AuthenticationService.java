package org.sourcelesslight.services;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.sourcelesslight.hashing.SHA256Hasher;
import org.sourcelesslight.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class AuthenticationService {
	
	private SessionFactory sessionFactory;
	private SHA256Hasher hasher;
	
	@Transactional(readOnly=true)
	public User performLogin(String username, String password, String ip) throws HibernateException
	{
		try
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			User user = (User) criteria
					.add(Restrictions.eq("username", username))
					.add(Restrictions.eq("password", hasher.encrypt(password)))
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
