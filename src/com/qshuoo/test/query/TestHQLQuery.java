package com.qshuoo.test.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestHQLQuery {
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void test01() {
		String hql = "from User";
		Query<User> query = session.createQuery(hql, User.class);
		System.out.println(query.list());
	}
	
	@Test
	public void test02() {
		String hql = "from User where id = 1";
		Query<User> query = session.createQuery(hql, User.class);
		System.out.println(query.uniqueResult());
	}
	
	@Test
	public void testByCondition() {
		String hql = "from User where id = :id";
		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("id", 1);
		System.out.println(query.uniqueResult());
	}
	
	@Test
	public void testColumnQuery() {
		String hql = "select name from User where id = 1";
		Query<String> query = session.createQuery(hql, String.class);
		System.out.println(query.uniqueResult());
	}
	
	@Test
	public void testManyColumnQuery() {
		String hql = "select id,name from User where id = 1";
		Query<Object[]> query = session.createQuery(hql, Object[].class);
		Object[] objects = query.uniqueResult();
		System.out.println(objects);
		for (Object object : objects) {
			System.out.println(object);
		}
	}
	
	@Test
	public void testLimitQuery() {
		String hql = "from User";
		Query<User> query = session.createQuery(hql, User.class);
		query.setFirstResult(2);
		query.setMaxResults(2);
		System.out.println(query.list());
	}
	
	@After
	public void afterTest() {
		transaction.commit();
	}

}
