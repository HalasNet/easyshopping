package com.ibm.backend.ssecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.ibm.model.backend.security.Authority;
import com.ibm.service.backend.security.AuthorityService;

/**
 * 
 * 
 */
public class FilterInvocationSecurityMetadataSourceImpl implements
		FilterInvocationSecurityMetadataSource {
	
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	
	private AuthorityService authorityService;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private Collection<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
	
	public FilterInvocationSecurityMetadataSourceImpl( AuthorityService authorityService) {
		this.authorityService = authorityService;
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		  Collection<ConfigAttribute> configAttributes = null;
		  ConfigAttribute configAttribute = null;
		List<Authority> auths = getAuthorityService().queryAll();
	    for (Authority resource : auths) {  
	    		configAttributes = new ArrayList<ConfigAttribute>();  
                               //以权限名封装为Spring的security Object  
               configAttribute = new SecurityConfig(resource.getAuthcode());  
               configAttributes.add(configAttribute);
               allAttributes.add(configAttribute);
               resourceMap.put(resource.getRequestURI(), configAttributes);
           }  
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		if(resourceMap.isEmpty())
			loadResourceDefine();
		
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		for (String resourceUrl:resourceMap.keySet()) {
			
			if (resourceUrl!=null && !"/**".equals(resourceUrl) && urlMatcher.match(resourceUrl,requestUrl)) {
				return resourceMap.get(resourceUrl);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return allAttributes;
	}

	protected AuthorityService getAuthorityService() {
		return this.authorityService;
	}
	
	public static void main(String[] args)
	{
		//UrlMatcher urlMatcher = new RegexUrlPathMatcher();
		PathMatcher urlMatcher = new AntPathMatcher();
		if (urlMatcher.match("/view/**","/view/main")) {
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
}