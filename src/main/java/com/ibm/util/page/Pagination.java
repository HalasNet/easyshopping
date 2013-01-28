package com.ibm.util.page;

public class Pagination {

	private int start;// 起始的记录数。从0开始。
	private int step = 1;
	private int pageSize = 10;// 页面大小
	private int total;// 总数量
	private int curPage;// 当前页数
	private int prePage = curPage - step; // 上一页
	private int nextPage = curPage + step; // 下一页
	private int totalPage; // 总页数
	private int range = 10; // 分页标签上显示多少个页
	private int startNum; // 分页标签上的起始数字
	private int endNum; // 分页标签上的结束数字

	public Pagination() {
		this.step = 1;
		this.pageSize = 10;
	}

	public Pagination(int curPage, int pageSize) {
		this.step = 1;
		this.pageSize = pageSize;
		this.curPage = (curPage <= 0) ? 0 : curPage - 1;
		this.start = this.curPage * this.pageSize;
	}

	private int calculateTotalPage(int total, int pageSize) {
		int totalPage = 1;
		if (total > 0 && pageSize > 0) {
			totalPage = (int) Math.ceil(((double) total) / pageSize);
			totalPage = (totalPage == 0) ? 1 : totalPage;
		}
		return totalPage;
	}

	private Integer[] getNum(int curPage, int totalPage, int range) {
		int startNum = 1;
		int endNum = startNum + range - 1;
		if (endNum < totalPage && curPage > (startNum + endNum + 1) / 2) {
			startNum = curPage - (int) Math.ceil(((double) range) / 2);
			endNum = startNum + range - 1;

			if (endNum > totalPage) {
				endNum = totalPage;
				startNum = totalPage - range + 1;
			}
		}
		if (endNum > totalPage) {
			endNum = totalPage;
		}

		return new Integer[] { startNum, endNum };
	}

	public Pagination getPage() {
		this.totalPage = this.calculateTotalPage(this.total, this.pageSize);
		this.curPage = (this.curPage < this.totalPage) ? ++this.curPage
				: this.totalPage;
		this.prePage = this.curPage - this.step;
		this.prePage = this.prePage <= 0 ? 1 : this.prePage;
		this.nextPage = this.curPage + this.step;
		this.nextPage = this.nextPage > this.totalPage ? totalPage
				: this.nextPage;
		Integer[] num = this.getNum(this.curPage, this.totalPage, this.range);
		this.startNum = num[0];
		this.endNum = num[1];

		return this;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

}
