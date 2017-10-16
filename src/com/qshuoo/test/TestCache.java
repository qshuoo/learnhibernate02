package com.qshuoo.test;

import org.hibernate.Session;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestCache {
	
	/**
	 * 只在开头查询一次user
	 * clear()方法执行后依然不会再次查询user
	 */
	@Test
	public void testCache() {
		Session session = HibernateUtils.getCurrentSession();
		session.beginTransaction();
		User user = session.get(User.class, 1);
		System.out.println(user);
		User user2 = session.get(User.class, 1);
		System.out.println(user2);
		session.clear();
		User user3 = session.get(User.class, 1);
		System.out.println(user3);
		User user4 = session.get(User.class, 1);
		System.out.println(user4);
		session.getTransaction().commit();
	}
	

}
