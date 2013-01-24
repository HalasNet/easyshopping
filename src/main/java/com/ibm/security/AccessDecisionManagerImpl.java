package com.ibm.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
/**
 * 
 * NOTE
 *
 * @author qzq
 * @since 2012-10-20
 */
public final class AccessDecisionManagerImpl implements AccessDecisionManager {
	protected Logger logger = Logger.getLogger(getClass());
	public AccessDecisionManagerImpl(){
	}
	// In this method, need to compare authentication with configAttributes.
	// 1, A object is a URL, a filter was find permission configuration by this
	// URL, and pass to here.
	// 2, Check authentication has attribute in permission configuration
	// (configAttributes)
	// 3, If not match corresponding authentication, throw a
	// AccessDeniedException.
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		logger.info(" *** decide authentication has configAttributes *** ");
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		ConfigAttribute configAttribute = null;
		String needPermission = null;
		while (ite.hasNext()) {
			configAttribute = ite.next();
			needPermission = configAttribute.getAttribute();
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				if (needPermission.equals(authority.getAuthority())) {
					return;
				}
			}
		}
		
		logger.info("authentication's authorities : "+authentication.getAuthorities());
		logger.info("to access required authorities : "+configAttributes);
		throw new AccessDeniedException(" 没有权限访问 : current user has no right to access "+object);
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	
}