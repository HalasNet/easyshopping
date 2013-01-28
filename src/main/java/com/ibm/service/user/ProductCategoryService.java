package com.ibm.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.ProductCategoryDao;
import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.ProductCategory;

@Service
@Transactional
public class ProductCategoryService{

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private ProductCategoryDao productCategoryDao;

	/**
	 * 查询所有类别
	 * 
	 * @return list 类别列表
	 */
	@Transactional(readOnly = true)
	public List<ProductCategory> queryCategorys() {
		return productCategoryDao.query();
	}

	/**
	 * 增加产品类别
	 * @param productCategory 类别实体
	 */
	@Transactional
	public void addProductCategory(ProductCategory productCategory) {
		productCategoryDao.save(productCategory);
	}
	
	/**
	 * 通过产品类别ID查询一条记录
	 * @param id 类别ID
	 * @return ProductCategory 产品类别实体
	 */
	@Transactional
	public ProductCategory queryProductCategoryById(Long id) {
		return productCategoryDao.get(id);

	}
	
	/**
	 * 修改产品类别
	 * @param productCategory 产品类别实体
	 * @void
	 */
	@Transactional
	public void modifyProductCategory(ProductCategory productCategory) {
		productCategoryDao.saveOrUpdate(productCategory);

	}
	
	/**
	 * 删除产品类别
	 * @param  categoryId Long类别Id
	 * @void
	 */
	@Transactional
	public void deleteProductCategory(Long categoryId) {
		productCategoryDao.delete(categoryId);

	}

}
