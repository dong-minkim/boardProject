package com.spring.boardapp.domain;

public class Board {
	private String id;
	private String title;
	private String content;
	private String writer;
	private String views;
	private String regist_datetime;
	private String modify_datetime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getRegist_datetime() {
		return regist_datetime;
	}
	public void setRegist_datetime(String regist_datetime) {
		this.regist_datetime = regist_datetime;
	}
	public String getModify_datetime() {
		return modify_datetime;
	}
	public void setModify_datetime(String modify_datetime) {
		this.modify_datetime = modify_datetime;
	}
}
