package com.ibm.service.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.ProductDao;
import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.Product;
import com.ibm.util.page.Pagination;

@Service
@Transactional
public class ProductService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private ProductDao productDao;

	@Transactional(readOnly = true)
	public Product get(Long id) {
		return productDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Product> getAll() {
		return productDao.listAll();
	}

	public void saveOrUpdate(Product product) {
		productDao.saveOrUpdate(product);
	}

	public void remove(Long id) {
		productDao.delete(id);
	}

	public List<Product> search(Long categoryId, String productName,
			Pagination pagination) {
		return productDao.queryProductList(categoryId, productName, pagination);
	}
}
