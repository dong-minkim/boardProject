package com.spring.boardapp.domain;

public class BoardAttach {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;

	private String board_id;
	
	public BoardAttach(String uuid, String uploadPath, String fileName, boolean fileType, String board_id){
		this.uuid=uuid;
		this.uploadPath=uploadPath;
		this.fileName=fileName;
		this.fileType=fileType;
		this.board_id=board_id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getBoard_id() + " " + this.getUuid()+" "+this.getUploadPath() + " " + this.getFileName() + " " +this.isFileType();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isFileType() {
		return fileType;
	}

	public void setFileType(boolean fileType) {
		this.fileType = fileType;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

}
