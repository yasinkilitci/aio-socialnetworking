package org.sourcelesslight.services;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
			User user = (User) session.createQuery("from USERS where username=:username and password=:password")
					.setString("username", username)
					.setString("password", hasher.encrypt(password))
					.uniqueResult();
			
			if (user != null) {
				Hibernate.initialize(user.getPreferences());
				Hibernate.initialize(user.getPreferences().getTheme());
				Hibernate.initialize(user.getConfirmationCode());
			}
			
//			Criteria criteria = session.createCriteria(User.class);
//			User user = (User) criteria
//					.add(Restrictions.eq("username", username))
//					.add(Restrictions.eq("password", hasher.encrypt(password)))
//					.uniqueResult();
			
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
