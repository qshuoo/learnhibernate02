package com.qshuoo.test.query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;
import com.qshuoo.utils.HibernateUtils;

public class TestQBCQuery {
	
	private Session session;
	private Transaction transaction;
	@Before
	public void beforeTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void test01() {
		//构建CriteriaBuilder
		CriteriaBuilder cb = session.getCriteriaBuilder();
		//使用CriteriaBuilder 构建 CriteriaBuilder对象
		CriteriaQuery<User> cQuery = cb.createQuery(User.class);
		cQuery.from(User.class);
		Query<User> query = session.createQuery(cQuery);
		System.out.println(query.list());
		
	}
	
	@Test
	public void queryByConditionTest() {
		CriteriaBuilder cbBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> cQuery = cbBuilder.createQuery(User.class);
		Root root = cQuery.from(User.class);
		Path id = root.get("id");
		Path name = root.get("name");
		Predicate predicate = cbBuilder.like(name, "lgt");
		Predicate predicate2 = cbBuilder.gt(id, 2);
		cQuery.where(predicate, predicate2);
		cQuery.select(name);
		Query<User> query = session.createQuery(cQuery);
		
		System.out.println(query.list());
	}
	
	public void afterTest() {
		transaction.commit();
	}

}
