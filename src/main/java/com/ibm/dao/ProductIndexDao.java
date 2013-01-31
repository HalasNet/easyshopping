package com.ibm.dao;

import java.util.List;

import com.ibm.domain.ProductIndex;
import com.ibm.util.page.Pagination;


public interface ProductIndexDao extends IBaseDao<ProductIndex, Long>{

	public void createIndexByHibernateSearch();

	public List<ProductIndex> QueryByIndex(String words, String startDate,String endDate) throws Exception;
	
	public List<ProductIndex> search(String keyWord, Pagination pagination) throws Exception;
	
	public List<ProductIndex> queryAutoComplete(String keyWord) throws Exception;

}
