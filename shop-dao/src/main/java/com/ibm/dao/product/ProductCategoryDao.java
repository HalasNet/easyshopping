package com.ibm.dao.product;

import java.util.List;

import com.ibm.dao.IBaseDao;
import com.ibm.model.product.ProductCategory;

public interface ProductCategoryDao extends IBaseDao<ProductCategory, Long> 
{
	//根据类别名称模糊查询产品类别
	public List<ProductCategory> query(String categoryName);
	
	//根据类别名称查询产品类别
	public ProductCategory queryProductCategory(String categoryName);
	
	//查询所有产品类别
	public List<ProductCategory> query();
	
	//批量删除产品类别
	public void deleteBatchProductCategory(String categoryIds);
}
