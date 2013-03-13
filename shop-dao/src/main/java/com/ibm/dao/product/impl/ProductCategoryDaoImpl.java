package com.ibm.dao.product.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.product.ProductCategoryDao;
import com.ibm.model.product.ProductCategory;

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

	/**
	 * 批量删除产品类别
	 */
	@Override
	public void deleteBatchProductCategory(String categoryIds) {
		Query query = this.getSession().createQuery(
				"delete from ProductCategory where id in ("+categoryIds+")");
		query.executeUpdate();
	}
	
}
