package com.ibm.service.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.ProductIndexDao;
import com.ibm.domain.ProductIndex;

@Transactional
@Service
public class ProductSearchService {
	private static final Logger logger = LoggerFactory
			.getLogger(ProductSearchService.class);

	@Autowired
	private ProductIndexDao productIndexDao;
	
	

	// 下面的方法是用hibernate search的方法来创建索引

	public void createIndexByHibernateSearch() {

		productIndexDao.createIndexByHibernateSearch();

	}

	public List<ProductIndex> QueryByIndex(String words, String startDate,
			String endDate) throws Exception {
		return productIndexDao.QueryByIndex(words, startDate, endDate);
	}


}
