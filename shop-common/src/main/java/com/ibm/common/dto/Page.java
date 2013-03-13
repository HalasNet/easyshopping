package com.ibm.common.dto;

import java.util.Collections;
import java.util.List;

/**
 * 表示一次分页的详细信息
 * 
 * @author david
 * 
 * @param <E>
 */
public class Page<E> {

	private boolean isPre;// 是否首页
	private boolean hasNext;// 是否还有下一页，也表示是否为末页
	private List<E> items;// 当前页包含的记录列表，可以从list中获取具体的对象
	private int index;// 当前页页码(起始为1)
	private IPageContext<E> context;//当前分页的Context环境

	public IPageContext<E> getContext() {
		return this.context;
	}

	public void setContext(IPageContext<E> context) {
		this.context = context;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isPre() {
		return isPre;
	}

	public void setPre(boolean isPre) {
		this.isPre = isPre;
	}

	public boolean isHasNext() {
		return this.hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<E> getItems() {
		return this.items == null ? Collections.<E> emptyList() : this.items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

}
