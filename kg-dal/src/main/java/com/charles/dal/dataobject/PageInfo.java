package com.charles.dal.dataobject;

import java.io.Serializable;

public class PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8290529104741604585L;
	
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	//当前页
	private int currentPage = 1;
	
	//每页数量
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	//总记录数
	private int totalCount = 0;
	
	//总页数
	private int totalPage = 0;
	
	/**
	 * 从0开始，用户mysql分页
	 * @return	起始记录位置
	 */
	public int getStartIndex() {
		int index = getStartRow() - 1;
		if(index < 0) {
			index = 0;
		}
		return index;
	}
	
	/**
	 * 从1开始（currentPage - 1） * pageSize + 1
	 * @return 开始序号，从1开始
	 */
	public int getStartRow() {
		return (this.currentPage - 1) * this.pageSize + 1;
		
	}
	
	/**
	 * currentPage * pageSize
	 * @return	结束序号
	 */
	public int getEndRow() {
		int last = this.currentPage * this.pageSize;
		return this.totalCount > 0 && last > this.totalCount ? this.totalCount : last;
	}
	
	/**
	 * 最小为1
	 * @return this currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * 当前页只能大于等于1的值，默认为1
	 * 
	 * @param currentPage the currentPage to set
	 */
	public void setCurrenPage(int currentPage) {
		if(currentPage < 1) {
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	
	/**
	 * return the pageSize
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * pageSize 只能大于0的值，默认20
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if(pageSize < 0) {
			pageSize = 20;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 最小为0
	 * @return	总记录数
	 */
	public int getTotalCount() {
		return totalCount;
	}
	
	/**
	 * 设置总量，并计算总页数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if(totalCount < 0) {
			totalCount = 0;
		}
		this.totalCount = totalCount;
		if(totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}
		if(currentPage > totalPage) {
			currentPage = totalPage;
		}
		if(currentPage < 1) {
			currentPage = 1;
		}
	}
	
	/**
	 * 最小为1
	 * @return	总页数
	 */
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
