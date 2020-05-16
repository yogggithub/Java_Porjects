package com.stumgnt.domain;

import java.util.List;

public class PageStudent<T> {
	private int currentPage, totalPages, totalSize, pageSize;
	private List<T> currentList;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPage) {
		this.totalPages = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getCurrentList() {
		return currentList;
	}
	public void setCurrentList(List<T> list) {
		this.currentList = list;
	}
}
