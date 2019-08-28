package com.kh.board.model.vo;

import java.util.Date;

public class Board {
	
	/*
	BOARD_NO	NUMBER
	BOARD_TITLE	VARCHAR2(50 BYTE)
	BOARD_WRITER	VARCHAR2(15 BYTE)
	BOARD_CONTENT	VARCHAR2(2000 BYTE)
	BOARD_ORIGINAL_FILENAME	VARCHAR2(100 BYTE)
	BOARD_RENAMED_FILENAME	VARCHAR2(100 BYTE)
	BOARD_DATE	DATE
	BOARD_READCOUNT	NUMBER
	*/
	
	private int boardNo;
	private String boardTitile;
	private String boardWriter;
	private String boardContent;
	private String boardOriginalFileName;
	private String boardRenamedFileName;
	private Date boardDate;
	private int boardReadCount;
	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}


	public Board(int boardNo, String boardTitile, String boardWriter, String boardContent, String boardOriginalFileName,
			String boardRenamedFileName, Date boardDate, int boardReadCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitile = boardTitile;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardOriginalFileName = boardOriginalFileName;
		this.boardRenamedFileName = boardRenamedFileName;
		this.boardDate = boardDate;
		this.boardReadCount = boardReadCount;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardTitile() {
		return boardTitile;
	}


	public void setBoardTitile(String boardTitile) {
		this.boardTitile = boardTitile;
	}


	public String getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getBoardOriginalFileName() {
		return boardOriginalFileName;
	}


	public void setBoardOriginalFileName(String boardOriginalFileName) {
		this.boardOriginalFileName = boardOriginalFileName;
	}


	public String getBoardRenamedFileName() {
		return boardRenamedFileName;
	}


	public void setBoardRenamedFileName(String boardRenamedFileName) {
		this.boardRenamedFileName = boardRenamedFileName;
	}


	public Date getBoardDate() {
		return boardDate;
	}


	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}


	public int getBoardReadCount() {
		return boardReadCount;
	}


	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitile=" + boardTitile + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardOriginalFileName=" + boardOriginalFileName
				+ ", boardRenamedFileName=" + boardRenamedFileName + ", boardDate=" + boardDate + ", boardReadCount="
				+ boardReadCount + "]";
	}
	
	
	

}
