package com.ibm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.ProductDao;
import com.ibm.domain.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseHibernateDao<Product, Long> implements ProductDao 
{


}
