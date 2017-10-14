package com.qshuoo.test;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestUpdate {
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	/**
	 * 执行了一次update
	 */
	@Test
	public void updateTest1() {
		User user = new User();
		user.setId(1);
		user.setName("lllf");
		session.update(user);
		user.setName("lllf");
		transaction.commit();
	}
	
	/**
	 * 执行了一次select
	 * 当setName与数据库中不同时，执行了一次update
	 */
	@Test
	public void updateTest2() {
		User user = session.get(User.class, 1);
		user.setName("lgt");
		session.update(user);
		transaction.commit();
	}
	/**
	 * 执行了一次select
	 * 注释为1的setName与数据库中的不同时，执行了一次update
	 * 注释为2的setName与注释为1的setName不同时，执行了一次update
	 */
	
	@Test
	public void updateTest3() {
		User user = session.get(User.class, 1);
		transaction.commit();
		user.setName("lf");//1
		session=HibernateUtils.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		user.setName("qqq");
		user.setName("ggg");
		user.setName("lf");//2
		session.update(user);
		session.save(user);
		session.getTransaction().commit();
	}
	
/*	@After
	public void afterTest() {
		transaction.commit();
	}*/
}
