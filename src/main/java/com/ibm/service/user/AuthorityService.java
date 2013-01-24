package com.ibm.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.AuthorityDao;
import com.ibm.domain.Authority;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-23 下午2:17:38
 */
@Service
@Transactional
public class AuthorityService {

	@Resource
	private AuthorityDao authorityDao;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Authority> queryAll(){
		return authorityDao.listAll();
	}
}
