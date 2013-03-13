package com.ibm.model.product;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotBlank;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 产品索引类 非具体表
 * @author lbz
 *
 */
@Entity
@Table(name = "t_product_index")
@Indexed(index = "product_index")
@Analyzer (impl = IKAnalyzer.class )
public class ProductIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 691364218057020244L;
	
	
	private Long id;

	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 产品发布时间
	 */
	private Date productPublishDate;

	/**
	 * 产品logo
	 */
	private String productLog;

	/**
	 * 产品价格
	 */
	private String productPrice;
	
	/**
	 * 产品类别
	 */
	private String categoryName;
	
	
	private String findResult;

	@Id
	@Column(name = "id")
	@DocumentId
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "productName")
	@NotBlank
	@Field(name = "productName", index = Index.TOKENIZED, store = Store.YES)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	@Column(name = "productPublishDate")	
	@Field(name = "productPublishDate", index = Index.UN_TOKENIZED, store = Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	public Date getProductPublishDate() {
		return productPublishDate;
	}

	public void setProductPublishDate(Date productPublishDate) {
		this.productPublishDate = productPublishDate;
	}


	@Column(name = "productLog")
	@NotBlank
	@Field(name = "productLog", index = Index.UN_TOKENIZED, store = Store.YES)
	public String getProductLog() {
		return productLog;
	}

	public void setProductLog(String productLog) {
		this.productLog = productLog;
	}


	@Column(name = "productPrice")
	@NotBlank
	@Field(name = "productPrice", index = Index.UN_TOKENIZED, store = Store.YES)
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	@Column(name = "categoryName")
	@NotBlank
	@Field(name = "categoryName", index = Index.TOKENIZED, store = Store.YES)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	//封装搜索出的高亮内容
	@Transient
	public String getFindResult() {
		return findResult;
	}

	public void setFindResult(String findResult) {
		this.findResult = findResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result
				+ ((findResult == null) ? 0 : findResult.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((productLog == null) ? 0 : productLog.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result
				+ ((productPrice == null) ? 0 : productPrice.hashCode());
		result = prime
				* result
				+ ((productPublishDate == null) ? 0 : productPublishDate
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductIndex other = (ProductIndex) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (findResult == null) {
			if (other.findResult != null)
				return false;
		} else if (!findResult.equals(other.findResult))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productLog == null) {
			if (other.productLog != null)
				return false;
		} else if (!productLog.equals(other.productLog))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productPublishDate == null) {
			if (other.productPublishDate != null)
				return false;
		} else if (!productPublishDate.equals(other.productPublishDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductIndex [id=" + id + ", productName=" + productName
				+ ", productPublishDate=" + productPublishDate
				+ ", productLog=" + productLog + ", productPrice="
				+ productPrice + ", categoryName=" + categoryName
				+ ", findResult=" + findResult + "]";
	}

	

}
