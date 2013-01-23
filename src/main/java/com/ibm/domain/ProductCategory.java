package com.ibm.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 产品类别实体类
 * @author dgs
 *
 */
@Entity
@Table(name = "t_productcategory")
public class ProductCategory  extends IdEntity implements Serializable 
{
	/**
	 * 产品类别名称
	 */
	private String categoryName;
	
	private Set<Product> products;

	@Column(name = "categoryName")
	@NotBlank
	public String getCategoryName() 
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName) 
	{
		this.categoryName = categoryName;
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
