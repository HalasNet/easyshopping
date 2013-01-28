package com.ibm.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.ProductDao;
import com.ibm.domain.Product;
import com.ibm.util.ConditionQuery;
import com.ibm.util.OrderBy;

@Repository("productDao")
public class ProductDaoImpl extends BaseHibernateDao<Product, Long> implements ProductDao 
{

	@Override
	public List<Product> queryProductList(Long categoryId, String productName,int pn,int pageSize) {
		ConditionQuery query=new ConditionQuery();
		if(StringUtils.isNotBlank(productName)){
			query.add(Restrictions.like("productName", productName));
		}
		if(categoryId!=null && categoryId.longValue()!=0){
			query.add(Restrictions.eq("category.id", categoryId));
		}
		OrderBy orderBy =new OrderBy();
		orderBy.add(Order.desc("id"));
		List<Product> products = this.list(query, orderBy, pn, pageSize);
		return products;

	}


}
