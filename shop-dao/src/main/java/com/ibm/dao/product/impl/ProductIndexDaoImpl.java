package com.ibm.dao.product.impl;

import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ibm.common.dto.Pagination;
import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.product.ProductIndexDao;
import com.ibm.model.product.ProductIndex;

@Repository("productIndexDao")
public class ProductIndexDaoImpl extends BaseHibernateDao<ProductIndex, Long>
		implements ProductIndexDao {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductIndexDaoImpl.class);

	@Override
	public void createIndexByHibernateSearch() {

		long startTime = new Date().getTime();
		int BATCH_SIZE = 1000;
		FullTextSession s = Search.getFullTextSession(this.getSession());

		// Transaction tr = s.beginTransaction();
		s.setFlushMode(FlushMode.MANUAL);
		s.setCacheMode(CacheMode.IGNORE);
		ScrollableResults results = s.createQuery("from ProductIndex")
				.setFetchSize(BATCH_SIZE).scroll(ScrollMode.FORWARD_ONLY);
		int index = 0;
		while (results.next()) {
			index++;
			s.index(results.get(0)); // index each element
			if (index % BATCH_SIZE == 0) {
				// s.flushToIndexes(); //apply changes to indexes
				s.clear(); // clear since the queue is processed
			}
		}
		s.clear();

		long endTime = new Date().getTime();
		logger.debug("建立Product索引 ， 这花费了" + (endTime - startTime)
				+ " 毫秒来把文档增加到索引里面去!");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductIndex> QueryByIndex(String words, String startDate,
			String endDate) throws Exception {
		
		int page = 1;
		int pageSize = 2;
		int firstResult = (page -1) * pageSize;
		
		FullTextSession fullTextSession = Search.getFullTextSession(this
				.getSession());

		/*
		 * Query IKQuery = IKQueryParser.parseMultiField(new String[] {
		 * "proTitle", "proDescn" }, new String[] { words, words }, new
		 * BooleanClause.Occur[] { Occur.SHOULD, Occur.SHOULD });
		 * 
		 * Query luceneQuery = MultiFieldQueryParser.parse(new String[] { words,
		 * words }, new String[] { "pro_title", "pro_descn" }, new
		 * BooleanClause.Occur[] { Occur.SHOULD, Occur.SHOULD }, new
		 * StandardAnalyzer());
		 */
		BooleanQuery bQuery = new BooleanQuery();
		Analyzer analyzer = new IKAnalyzer();
		// 设置对域采用的某种分词器的QueryParser对象
		QueryParser qp;
		// 设置了关键字的查询您对象
		// Query q;

		qp = new QueryParser(Version.LUCENE_36, "productName", analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q1 = qp.parse(words);
		q1.setBoost(1.5f);
		bQuery.add(q1, Occur.SHOULD);

		qp = new QueryParser(Version.LUCENE_36, "categoryName", analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q2 = qp.parse(words);
		q2.setBoost(1.0f);
		bQuery.add(q2, Occur.SHOULD);


		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(
				bQuery, ProductIndex.class);
		// 添加是或者否的条件到query中
		boolean filterResult = false;
		BooleanQuery bQueryForFilter = new BooleanQuery();

		if (!startDate.equalsIgnoreCase("") && !endDate.equalsIgnoreCase("")) {
			// 时间过滤
			// RangeFilter rangefilter = new RangeFilter("pro_time",
			// "20090927","20090929", false, false);
			// 只能使用一个过滤器，所以只能用下面的RangeQuery，然后将所有query封装到一个过滤条件中
			TermRangeQuery rangeQuery = new TermRangeQuery(
					"productPublishDate", startDate, endDate, true, true);
			bQueryForFilter.add(rangeQuery, BooleanClause.Occur.MUST);
			filterResult = true;
		}
		if (filterResult) {
			// 将booleanQuery封装到Filter中
			Filter filter = new CachingWrapperFilter(new QueryWrapperFilter(
					bQueryForFilter));
			fullTextQuery.setFilter(filter);
		}
		
		fullTextQuery.setFirstResult(firstResult);
		fullTextQuery.setMaxResults(pageSize);
		
		int count = fullTextQuery.getResultSize();
		System.out.println("the result count is :" + count);

		List<ProductIndex> result = fullTextQuery.list();
		
		String findResult;

		// 根据上边已经写好的query封装出一个查询计分器
		QueryScorer qs1 = new QueryScorer(q1);

		// 设置高亮的模板，其实就是在关键字两边加一对html的格式标签，下面是最基本的加粗。
		Formatter formatter = new SimpleHTMLFormatter("<b>", "</b>");

		Highlighter highlighter1 = new Highlighter(formatter, qs1);
		String text;

		// 下面通过将上面根据关键字，过滤条件和权重排序等找出的结果集做一次循环，进行高亮，把高亮后得到的

		// 一个字符串，封装如每个实体类中的一个额外字段，方便在页面输出。
		for (ProductIndex product : result) {
			text = product.getProductName();
			findResult = highlighter1.getBestFragment(analyzer, "productName",
					text);
			product.setFindResult(findResult);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductIndex> search(String keyWord, Pagination pagination) throws Exception {

		int pageSize = pagination.getPageSize();
		int firstResult = pagination.getCurPage() * pageSize;
		
		FullTextSession fullTextSession = Search.getFullTextSession(this
				.getSession());

		/*
		 * Query IKQuery = IKQueryParser.parseMultiField(new String[] {
		 * "proTitle", "proDescn" }, new String[] { words, words }, new
		 * BooleanClause.Occur[] { Occur.SHOULD, Occur.SHOULD });
		 * 
		 * Query luceneQuery = MultiFieldQueryParser.parse(new String[] { words,
		 * words }, new String[] { "pro_title", "pro_descn" }, new
		 * BooleanClause.Occur[] { Occur.SHOULD, Occur.SHOULD }, new
		 * StandardAnalyzer());
		 */
		BooleanQuery bQuery = new BooleanQuery();
		Analyzer analyzer = new IKAnalyzer();
		// 设置对域采用的某种分词器的QueryParser对象
		QueryParser qp;
		// 设置了关键字的查询您对象
		// Query q;

		qp = new QueryParser(Version.LUCENE_36, "productName", analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q1 = qp.parse(keyWord);
		q1.setBoost(1.5f);
		bQuery.add(q1, Occur.SHOULD);

		qp = new QueryParser(Version.LUCENE_36, "categoryName", analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q2 = qp.parse(keyWord);
		q2.setBoost(1.0f);
		bQuery.add(q2, Occur.SHOULD);


		qp = new MultiFieldQueryParser(Version.LUCENE_36, new String[] { "productName", "categoryName" }, analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q3 = qp.parse(keyWord);
		q3.setBoost(1.0f);
		bQuery.add(q3, Occur.SHOULD);
		
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(
				bQuery, ProductIndex.class);




		
		fullTextQuery.setFirstResult(firstResult);
		fullTextQuery.setMaxResults(pageSize);
		
		int count = fullTextQuery.getResultSize();
		pagination.setTotal(count);
		System.out.println("the result count is :" + count);

		List<ProductIndex> result = fullTextQuery.list();
		
		String findResult;

		// 根据上边已经写好的query封装出一个查询计分器
		QueryScorer qs1 = new QueryScorer(q1);

		// 设置高亮的模板，其实就是在关键字两边加一对html的格式标签，下面是最基本的加粗。
		Formatter formatter = new SimpleHTMLFormatter("<b>", "</b>");

		Highlighter highlighter1 = new Highlighter(formatter, qs1);
		String text;

		// 下面通过将上面根据关键字，过滤条件和权重排序等找出的结果集做一次循环，进行高亮，把高亮后得到的

		// 一个字符串，封装如每个实体类中的一个额外字段，方便在页面输出。
		for (ProductIndex product : result) {
			text = product.getProductName();
			findResult = highlighter1.getBestFragment(analyzer, "productName",
					text);
			product.setFindResult(findResult);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductIndex> queryAutoComplete(String keyWord) throws Exception {

		
		FullTextSession fullTextSession = Search.getFullTextSession(this
				.getSession());
		
		 WildcardQuery wq = null;
		 wq = new WildcardQuery(new Term("productName",keyWord+"*"));
		
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(
				wq, ProductIndex.class);

		fullTextQuery.setFirstResult(0);
		fullTextQuery.setMaxResults(10);
		
		int count = fullTextQuery.getResultSize();
		System.out.println("the result count is :" + count);

		List<ProductIndex> result = fullTextQuery.list();


		return result;
	}

}
