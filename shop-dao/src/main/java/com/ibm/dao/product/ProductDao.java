package com.ibm.dao.product;

import java.util.List;

import com.ibm.common.dto.Pagination;
import com.ibm.dao.IBaseDao;
import com.ibm.model.product.Product;

public interface ProductDao extends IBaseDao<Product, Long> {

	public List<Product> queryProductList(Long categoryId, String productName,Pagination pagination);
	
	public List<Product> queryProductList(String categoryIds);
}
