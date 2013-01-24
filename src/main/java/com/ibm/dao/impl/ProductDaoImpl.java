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
				"from Product where categoryId=? or productname like '%"
						+ productName + "%'");

		// List
		// result=this.getSession().createQuery("from Product where categoryId=? or productname like '%"+productName+"%'").list();
		// Query query1 =
		// this.getSession().createQuery("from Product where categoryId=? or productname like :name");
		// query1.setString("name", "%"+productName+"%");

		query.setLong(0, categoryId);
		List<Product> products = query.list();
		return products;

	}


}
