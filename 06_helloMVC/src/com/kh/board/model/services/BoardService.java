package com.kh.board.model.services;

import static common.template.JDBCtemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;

public class BoardService {
	
	public int boardList() {
		
		Connection conn = getConnection();
		int ck = new BoardDao().boardList(conn);
		
		close(conn);
		return ck;
	}
	
	public List<Board> boardListView(int cPage, int numPerPage){
		
		Connection conn = getConnection();
		List<Board> list = new BoardDao().boardListView(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}
	
	public Board selectBoardOne(int no) {
		
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoardOne(conn, no);
		
		close(conn);
		return b;
	}
	
	public int insertBoard(Board b) {
		
		Connection conn = getConnection();
		int ck = new BoardDao().insertBoard(conn, b);
		
		close(conn);
		return ck;
	}
	

}
