package com.ibm.model.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.ibm.model.IdEntity;

/**
 * 产品实体类
 * @author dgs
 *
 */
@Entity
@Table(name = "t_product")
public class Product extends IdEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3482929099342625429L;

	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 产品发布时间
	 */
	private String productPublishDate;

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
	private ProductCategory category;

	@Column(name = "productName")
	@NotBlank
	public String getProductName() 
	{
		return productName;
	}

	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	@Column(name = "productPublishDate")
	@NotBlank
	public String getProductPublishDate() 
	{
		return productPublishDate;
	}

	public void setProductPublishDate(String productPublishDate) 
	{
		this.productPublishDate = productPublishDate;
	}

	@Column(name = "productLog")
	@NotBlank
	public String getProductLog() 
	{
		return productLog;
	}

	public void setProductLog(String productLog) 
	{
		this.productLog = productLog;
	}

	public String getProductPrice() 
	{
		return productPrice;
	}

	public void setProductPrice(String productPrice) 
	{
		this.productPrice = productPrice;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="categoryId")
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

}
