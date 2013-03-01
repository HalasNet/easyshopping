package com.ibm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ibm.dao.AuthorityDao;
import com.ibm.dao.BaseHibernateDao;
import com.ibm.domain.Authority;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-23 下午4:50:14
 */
@Repository
public class AuthorityDaoImpl extends BaseHibernateDao<Authority, Long>
		implements AuthorityDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorityDaoImpl.class);

	@Override
	public void delete(Authority entity) {
		if(entity == null || entity.getId() == null)
			return;
		delete(entity.getId());
	}

	@Override
	public List<Authority> getAuthorityByName(String name) {
		logger.info("execute AuthorityDaoImpl method getAuthorityByName()");
		if (StringUtils.isNotBlank(name)) {
			String hql = "from Authority where name like ?";
			return list(hql, "%" + name + "%");
		} else {
			String hql = "from Authority ";
			return list(hql);
		}

	}

	@Override
	public void delete(Long id) {

		String sql = "delete from t_authority where id = ?";
		String sql2 = "delete from t_role_authority where authorityId = ?";
		getSession().createSQLQuery(sql2).setParameter(0, id).executeUpdate();
		getSession().createSQLQuery(sql).setParameter(0, id).executeUpdate();
	}
	@Override
	public List<Authority> listAll() {
		String hql = "from Authority";
		return list(hql, (Object[]) null);
	}
}
