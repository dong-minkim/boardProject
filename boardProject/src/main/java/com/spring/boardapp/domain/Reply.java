package com.spring.boardapp.domain;

public class Reply {
	private String reply_id;
    private String board_id;
    private String reply_content;
    private String reply_writer;
    private String reply_datetime;
    private String reply_updatetime;
    
    
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public String getReply_datetime() {
		return reply_datetime;
	}
	public void setReply_datetime(String reply_datetime) {
		this.reply_datetime = reply_datetime;
	}
	public String getReply_updatetime() {
		return reply_updatetime;
	}
	public void setReply_updatetime(String reply_updatetime) {
		this.reply_updatetime = reply_updatetime;
	}
}
