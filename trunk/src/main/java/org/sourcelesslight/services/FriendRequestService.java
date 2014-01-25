package org.sourcelesslight.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sourcelesslight.model.FriendRequest;
import org.sourcelesslight.model.User;
import org.sourcelesslight.model.enums.RequestType;
import org.sourcelesslight.model.enums.RespondType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Repository
public class FriendRequestService {

	private SessionFactory sessionFactory;
	
	@Transactional(readOnly=false)
	public void sendRequest(User sender, User receiver)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			FriendRequest fr = new FriendRequest();
			fr.setUserFrom(sender);
			fr.setUserTo(receiver);
			fr.setRespond(RespondType.PENDING);
			fr.setRequestType(RequestType.OUTGOING);
			fr.setDateSent(new Date());
			session.save(fr);
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			tx.rollback();
			session.close();
		}
		
	}
	
	@Transactional(readOnly=false)
	public void respondRequest(int requestId, RespondType respond)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			FriendRequest fr = (FriendRequest)session.get(FriendRequest.class, requestId);
			fr.setRespond(respond);
			fr.setDateRespond(new Date());
			tx.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			tx.rollback();
			session.close();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
