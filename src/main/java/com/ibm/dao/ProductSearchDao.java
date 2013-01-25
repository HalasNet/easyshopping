package com.ibm.dao;

import java.util.List;

import com.ibm.domain.Product;

public interface ProductSearchDao extends IBaseDao<Product, Long> 
{

	public List<Product> queryProductList(Long categoryId, String productName);
}
