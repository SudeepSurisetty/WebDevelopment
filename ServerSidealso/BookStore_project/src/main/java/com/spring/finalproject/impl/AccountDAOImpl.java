package com.spring.finalproject.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.spring.finalproject.dao.AccountDAO;
import com.spring.finalproject.pojo.Account;
 
// Transactional for Hibernate
@Transactional
public class AccountDAOImpl implements AccountDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public Account findAccount(String userName ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("userName", userName));
        return (Account) crit.uniqueResult();
    }
    
    public Account register(Account a) throws Exception {
    	Session session = sessionFactory.getCurrentSession();
		try {
			
			//session.beginTransaction();
			System.out.println("inside DAO for register");
			session.save(a);
			if(!session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
			session.getTransaction().commit();
			return a;

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
    
    public boolean updateUser(String email) throws Exception {
    	Session session = sessionFactory.getCurrentSession();
		try {
			//session.beginTransaction();
			System.out.println("inside DAO for updating user");
			Query q = session.createQuery("from Account where userName = :useremail");
			q.setString("useremail", email);
			Account a = (Account) q.uniqueResult();
			if(a!=null){
				a.setActive(true);
				session.update(a);
				if(!session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
				session.getTransaction().commit();
				return true;
			}else{
				return false;
			}

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	
	}
 
}
