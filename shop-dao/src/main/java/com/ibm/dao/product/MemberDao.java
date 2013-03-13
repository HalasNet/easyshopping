package com.ibm.dao.product;

import com.ibm.dao.IBaseDao;
import com.ibm.model.product.Member;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-24 下午5:15:42
 */
public interface MemberDao extends IBaseDao<Member, Long> {

	public Member getMemberByName(String username);
}
