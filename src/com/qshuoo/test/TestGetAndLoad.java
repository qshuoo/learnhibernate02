package com.qshuoo.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestGetAndLoad {
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	/**
	 * 执行了2次select
	 */
	@Test
	public void GetTest() {
		User user = session.get(User.class, 4);
		user = session.get(User.class, 3);
		user = session.get(User.class, 3);
		user = session.get(User.class, 3);
		transaction.commit();
	}
	/**
	 * 没有执行sql语句
	 */
	@Test
	public void LoadTest() {
		User user = session.load(User.class, 4);
		user = session.load(User.class, 3);
		user = session.load(User.class, 3);
		user = session.load(User.class, 3);
		transaction.commit();
	}
	
	/**
	 * 执行了1次select
	 */
	@Test
	public void LoadTest01() {
		User user = session.load(User.class, 4);
		user = session.load(User.class, 3);
		user = session.load(User.class, 3);
		user = session.load(User.class, 3);
		System.out.println(user);
		transaction.commit();
	}
	/**
	 * 执行了2次select
	 */
	@Test
	public void LoadTest02() {
		User user = session.load(User.class, 4);
		System.out.println("1++==="+user);
		user = session.load(User.class, 3);
		System.out.println("2++==="+user);
		user = session.load(User.class, 3);
		System.out.println("3++==="+user);
		user = session.load(User.class, 3);
		System.out.println("4++==="+user);
		transaction.commit();
	}

}
