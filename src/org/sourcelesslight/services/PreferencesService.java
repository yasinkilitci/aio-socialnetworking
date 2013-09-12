package org.sourcelesslight.services;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sourcelesslight.model.Preferences;
import org.sourcelesslight.model.Theme;
import org.sourcelesslight.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PreferencesService {

	private SessionFactory sessionFactory;
	
	@Transactional(readOnly=false)
	public void savePreferencesForUser(User user,Preferences preferences)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.saveOrUpdate(preferences);
			user.setPreferences(preferences);
			session.save(user);
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			throw e;
		}
	}
	
	@Transactional(readOnly=true)
	public ArrayList<Theme> getAllThemes()
	{
		try
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Theme.class);
			@SuppressWarnings("unchecked")
			ArrayList<Theme> themes = (ArrayList<Theme>) criteria.list();
			session.close();
			return themes;
		}
		catch(HibernateException h)
		{
			throw h;
		}
	}
	
	@Transactional(readOnly=true)
	public Theme getThemeById(int themeId)
	{
		Session session = sessionFactory.openSession();
		try
		{
			Theme theme = (Theme)session.get(Theme.class, themeId);
			session.close();
			return theme;
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
