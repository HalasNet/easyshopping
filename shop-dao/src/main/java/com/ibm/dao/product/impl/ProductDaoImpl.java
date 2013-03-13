package com.ibm.dao.product.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ibm.common.dto.Pagination;
import com.ibm.common.util.ConditionQuery;
import com.ibm.common.util.OrderBy;
import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.product.ProductDao;
import com.ibm.model.product.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseHibernateDao<Product, Long> implements ProductDao 
{

	@Override
	public List<Product> queryProductList(Long categoryId, String productName,Pagination pagination) {
		ConditionQuery query=new ConditionQuery();
		if(StringUtils.isNotBlank(productName)){
			query.add(Restrictions.like("productName", "%"+productName+"%"));
		}
		if(categoryId!=null && categoryId.longValue()!=0){
			query.add(Restrictions.eq("category.id", categoryId));
		}
		OrderBy orderBy =new OrderBy();
		orderBy.add(Order.desc("id"));
		
		List<Product> products = this.list(query, orderBy, pagination.getCurPage(), pagination.getPageSize());
		pagination.setTotal(this.countList(query));
		return products;

	}

	@Override
	public List<Product> queryProductList(String categoryIds) {
		Query query = this.getSession().createQuery(
				"from Product where id in ("+categoryIds+")");
		List<Product> listProducts = query.list();
		return listProducts;
	}


}
