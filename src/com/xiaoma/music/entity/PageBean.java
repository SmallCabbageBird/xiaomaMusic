package com.xiaoma.music.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 页面类
 * @author lenovo
 *
 */
public class PageBean<T> implements Serializable{
	private int currentPageNum;//当前页
	private int pageSize = 12;//页面大小
	private int totalRecords;//总记录数
	
	private int startIndex;//开始索引
	private int totalPageNum;//总页数
	private int prePageNum;//上一页
	private int nextPageNum;//下一页
	
	private List<T> records;//页面数据

	
	/**
	 * 要想使用这个对象，必须传入两个条件
	 * @param currentPageNum   ：当前页
	 * @param totalRecords	        ：  总记录数
	 */
	public PageBean(int currentPageNum, int totalRecords) {
		super();
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		//开始索引
		startIndex = (currentPageNum-1) * pageSize;
		//总页数
		totalPageNum = (int) Math.ceil( (double)totalRecords / pageSize );
	}


	//计算上一页
	public int getPrePageNum() {
		prePageNum = currentPageNum - 1;
		if(prePageNum < 1){
			prePageNum = 1;
		}	
		return prePageNum;
	}

	//计算下一页
	public int getNextPageNum() {
		nextPageNum = currentPageNum + 1;
		if(nextPageNum > totalPageNum){
			nextPageNum = totalPageNum;
		}
		
		return nextPageNum;
	
	}


	public int getCurrentPageNum() {
		return currentPageNum;
	}


	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public int getTotalPageNum() {
		return totalPageNum;
	}


	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}


	public List<T> getRecords() {
		return records;
	}


	public void setRecords(List<T> records) {
		this.records = records;
	}


	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}


	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}


	@Override
	public String toString() {
		return "PageBean [currentPageNum=" + currentPageNum + ", pageSize="
				+ pageSize + ", totalRecords=" + totalRecords + ", startIndex="
				+ startIndex + ", totalPageNum=" + totalPageNum
				+ ", prePageNum=" + prePageNum + ", nextPageNum=" + nextPageNum
				+ ", records=" + records + "]";
	}
	
	
	
	
	
	
}
