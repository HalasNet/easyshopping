package com.ibm.dao.product;

import java.util.List;

import com.ibm.common.dto.Pagination;
import com.ibm.dao.IBaseDao;
import com.ibm.model.product.ProductIndex;


public interface ProductIndexDao extends IBaseDao<ProductIndex, Long>{

	public void createIndexByHibernateSearch();

	public List<ProductIndex> QueryByIndex(String words, String startDate,String endDate) throws Exception;
	
	public List<ProductIndex> search(String keyWord, Pagination pagination) throws Exception;
	
	public List<ProductIndex> queryAutoComplete(String keyWord) throws Exception;

}
