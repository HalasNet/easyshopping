package com.ibm.service.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.common.dto.Pagination;
import com.ibm.dao.backend.security.impl.UserDaoImpl;
import com.ibm.dao.product.ProductCategoryDao;
import com.ibm.dao.product.ProductDao;
import com.ibm.dao.product.ProductIndexDao;
import com.ibm.model.product.Product;
import com.ibm.model.product.ProductCategory;
import com.ibm.model.product.ProductIndex;

@Service
@Transactional
public class ProductService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductIndexDao productIndexDao;
	
	@Autowired
	private ProductCategoryDao productCategoryDao;

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
		ProductIndex pi = new ProductIndex();
		pi.setId(product.getId());
		pi.setProductName(product.getProductName());
		pi.setProductLog(product.getProductLog());
		pi.setProductPrice(product.getProductPrice());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(product.getProductPublishDate());
		} catch (ParseException e) {
			logger.error("string to date error");
			e.printStackTrace();
		}
		pi.setProductPublishDate(date);
		if (null == product.getCategory().getCategoryName() || "".equals(product.getCategory().getCategoryName())) {
			Long catId = product.getCategory().getId();
			ProductCategory cat = productCategoryDao.get(catId);
			product.setCategory(cat);
		}		
		pi.setCategoryName(product.getCategory().getCategoryName());
		productIndexDao.saveOrUpdate(pi);
		
	}

	public void remove(Long id) {
		productDao.delete(id);
		productIndexDao.delete(id);
	}

	public List<Product> search(Long categoryId, String productName,
			Pagination pagination) {
		return productDao.queryProductList(categoryId, productName, pagination);
	}
	
	public List<Product> queryProductsByCategoryIds(String categoryIds) {
		return productDao.queryProductList(categoryIds);
	}

	public ProductIndexDao getProductIndexDao() {
		return productIndexDao;
	}

	public void setProductIndexDao(ProductIndexDao productIndexDao) {
		this.productIndexDao = productIndexDao;
	}

	public ProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}

	public void setProductCategoryDao(ProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}

	
}
