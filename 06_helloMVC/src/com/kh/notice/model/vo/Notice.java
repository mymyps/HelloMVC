package com.kh.notice.model.vo;

import java.util.Date;

public class Notice {
	
//	NOTICE_NO NUMBER PRIMARY KEY,
//    NOTICE_TITLE VARCHAR2(100) NOT NULL,
//    NOTICE_WRITER VARCHAR2(15) NOT NULL,
//    NOTICE_CONTENT VARCHAR2(4000) NOT NULL,
//    NOTICE_DATE DATE DEFAULT SYSDATE,
//    FILEPATH VARCHAR2(300),
//    STATUS VARCHAR2(1) DEFAULT 'Y',
//    constraint fk_notice_writer FOREIGN KEY (NOTICE_WRITER) REFERENCES MEMBER (USERID)
	
	private int noticeNo;
	private String noticeTitile;
	private String noticeWriter;
	private String noticeContent;
	private Date noticeDate;
	private String filePath;
	private char status;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitile() {
		return noticeTitile;
	}

	public void setNoticeTitile(String noticeTitile) {
		this.noticeTitile = noticeTitile;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Notice(int noticeNo, String noticeTitile, String noticeWriter, String noticeContent, Date noticeDate,
			String filePath, char status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitile = noticeTitile;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.filePath = filePath;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitile=" + noticeTitile + ", noticeWriter=" + noticeWriter
				+ ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate + ", filePath=" + filePath
				+ ", status=" + status + "]";
	}
	
	
}
