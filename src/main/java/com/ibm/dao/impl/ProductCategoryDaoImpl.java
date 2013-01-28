package com.ibm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.ProductCategoryDao;
import com.ibm.domain.ProductCategory;

@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends BaseHibernateDao<ProductCategory, Long> implements ProductCategoryDao 
{

	/**
	 * 根据类别名称获取产品类别
	 */
	public List<ProductCategory> query(String categoryName) 
	{
		Query query = this.getSession().createQuery(
				"from ProductCategory where categoryName like '%"+categoryName+"%'");

		List<ProductCategory> listProducts = query.list();
		return listProducts;
	}

	/**
	 * 根据类别名称查询产品类别
	 */
	public ProductCategory queryProductCategory(String categoryName)
	{
		Query query = this.getSession().createQuery(
				"from ProductCategory where categoryName=?");
		query.setString(0, categoryName);
		ProductCategory productCategory = (ProductCategory)query.uniqueResult();
		return productCategory;
	}
	/**
	 * 查询所有的产品类别
	 */
	public List<ProductCategory> query() 
	{
		return this.listAll();
	}
	
}
