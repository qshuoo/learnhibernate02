package com.qshuoo.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestInsert {
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	
	/**
	 * 执行了一次insert，一次update
	 */
	@Test
	public void testSave() {
		User user = new User();
		user.setName("lf");
		user.setId(1);
		session.save(user);
		user.setName("lgt");
	}
	
	@After
	public void afterTest() {
		transaction.commit();
	}

}
