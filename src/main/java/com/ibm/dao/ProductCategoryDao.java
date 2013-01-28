package com.ibm.dao;

import java.util.List;

import com.ibm.domain.ProductCategory;

public interface ProductCategoryDao extends IBaseDao<ProductCategory, Long> 
{
	//根据类别名称查询产品类别，再获取类别ID
	public List<ProductCategory> query(String categoryName);
	
	//查询所有产品类别
	public List<ProductCategory> query();
}
