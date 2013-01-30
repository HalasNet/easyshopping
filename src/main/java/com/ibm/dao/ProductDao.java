package com.ibm.dao;

import java.util.List;

import com.ibm.domain.Product;
import com.ibm.util.page.Pagination;

public interface ProductDao extends IBaseDao<Product, Long> {

	public List<Product> queryProductList(Long categoryId, String productName,Pagination pagination);
	
	public List<Product> queryProductList(String categoryIds);
}
