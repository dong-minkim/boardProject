package com.spring.boardapp.domain.paging;

public class Paging {
	private int pageNum;    //현재 페이지
	private int pageAmount; //한 페이지당 출력되는 게시글 수
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int total;
	
	public Paging(int pageNum, int pageAmount, int total) {
		this.pageNum = pageNum;
		this.pageAmount = pageAmount;
		this.total = total;
		
		//끝 페이지
		this.endPage = (int) Math.ceil((this.pageNum)/5.0) * 5;
		
		//시작 페이지
		this.startPage = this.endPage - 4;
		
		//아예 페이지
		int tmpEndPage = (int)Math.ceil((total*1.0)/pageAmount);
		
		if(this.endPage > tmpEndPage) this.endPage = tmpEndPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < tmpEndPage;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageAmount() {
		return pageAmount;
	}
	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
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
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
