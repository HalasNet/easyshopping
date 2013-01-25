package com.ibm.service.user;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.MemberDao;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-24 下午5:17:31
 */
@Service
@Transactional
public class MemberService implements UserDetailsService{

	@Resource
	private MemberDao memberDao;
	
	@Override@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return memberDao.getMemberByName(username);
	}

	
}
