package com.ibm.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author qzq
 * 
 * @time 2013-3-13 下午3:55:59
 */
@Controller
public class IndexController {

	private static final String INDEX_PAGE = "index";
	
	@RequestMapping("/")
	public String index(){
		return INDEX_PAGE;
	}
}
