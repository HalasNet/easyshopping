package com.ibm.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.ProductCategoryDao;
import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.ProductCategory;

@Service
@Transactional
public class ProductCategoryService{

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Transactional(readOnly = true)
	public List<ProductCategory> queryCategorys() {
		return productCategoryDao.query();
	}



}
