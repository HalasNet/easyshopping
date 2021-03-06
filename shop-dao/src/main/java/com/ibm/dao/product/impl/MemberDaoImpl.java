package com.ibm.dao.product.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.product.MemberDao;
import com.ibm.model.product.Member;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-24 下午5:16:16
 */
@Repository
public class MemberDaoImpl extends BaseHibernateDao<Member, Long> implements MemberDao{

	@Override
	public Member getMemberByName(String username) {
		
		return unique("from Member where username = ?", username);
	}

	
}
