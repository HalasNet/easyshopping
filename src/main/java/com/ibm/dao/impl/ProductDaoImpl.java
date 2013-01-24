package com.ibm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.ProductDao;
import com.ibm.domain.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseHibernateDao<Product, Long> implements ProductDao 
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> queryProductList(Long categoryId, String productName) {
		Query query = this.getSession().createQuery(
				"from Product where categoryId=? or productname=?");
		query.setLong(0, categoryId);
		query.setString(1, productName);
		List<Product> products = query.list();
		return products;

	}


}
