package com.ibm.dao;

import java.util.List;

import com.ibm.domain.ProductIndex;


public interface ProductIndexDao extends IBaseDao<ProductIndex, Long>{

	public void createIndexByHibernateSearch();

	public List<ProductIndex> QueryByIndex(String words, String startDate,String endDate) throws Exception;

}
