package com.qshuoo.test.query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestSQLQuery {
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void test01() {
		String sql = "select * from user where id = 4";
		Query<User> query = session.createNativeQuery(sql, User.class);
		System.out.println(query.list());
	}
	
	@Test
	public void testByCondition() {
		String sql = "select * from user where id = ?";
		Query<User> query = session.createNativeQuery(sql, User.class);
		query.setParameter(1, 3);
		System.out.println(query.uniqueResult());
	}
	
	@Test
	public void testByCondition02() {
		String sql = "select name from user where id = ?";
//		Query<String> query = session.createNativeQuery(sql, String.class);
		NativeQuery<User> query2 = session.createNativeQuery(sql,User.class);
		query2.setParameter(1, 3);
		System.out.println(query2.uniqueResult());
//		System.out.println(query.getResultList());
//		System.out.println(query.uniqueResult());
	}
	
	@Test
	public void testLimitQuery() {
		String sql = "select * from user";
		Query<User> query = session.createNativeQuery(sql, User.class);
		query.setFirstResult(1);
		query.setMaxResults(2);
		System.out.println(query.list());
	}
	
	@After
	public void afterTest() {
		transaction.commit();
	}	

}
