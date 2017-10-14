package com.qshuoo.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestDelete {
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	/**
	 * 将游离状态转换为瞬时状态
	 */
	@Test
	public void deleteTest1() {
		User user = new User();
		user.setId(1);
		session.delete(user);
	}
	
	/**
	 * 将持久状态转换为瞬时状态
	 */
	@Test
	public void deleteTest2() {
		User user = session.get(User.class, 2);
		session.delete(user);
	}
	
	@After
	public void afterTest() {
		transaction.commit();
	}

}
