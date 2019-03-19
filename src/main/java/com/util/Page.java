package com.util;

public class Page {
	private int pageIndex = 1;// 当前页
	private int pageCount = 15;// 每页条数
	private int totalCount = 0; // 总记录数
	private int totalPage = 1; // 总页数
	// 显示的页码
	private int startPage;
	private int endPage;

	/**
	 * 开始记录数
	 * 
	 * @return
	 */
	public int getStart() {
		return (pageIndex - 1) * pageCount;
	}

	/**
	 * 结束记录数
	 * 
	 * @return
	 */
	public int getEnd() {
		return pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (pageIndex > 0)
			this.pageIndex = pageIndex;
		/*
		 * if(pageIndex>totalPage){ pageIndex=totalPage; }
		 */
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			setTotalPage();
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalPage() {
		if (totalCount % pageCount == 0)
			this.totalPage = totalCount / pageCount;
		else
			this.totalPage = totalCount / pageCount + 1;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void setShowPage(int pageIndex, int totalCount) {
		setTotalCount(totalCount);
		setTotalPage();
		// 显示的页码
		if (totalPage <= 5) {
			startPage = 1;
			endPage = totalPage;
		} else {
			startPage = pageIndex - 2;
			endPage = pageIndex + 2;
			if (startPage < 1) {
				startPage = 1;
				endPage = 5;
			}
			if (endPage > totalPage) {
				endPage = totalPage;
				startPage = totalPage - 4;
			}
		}
	}
}
