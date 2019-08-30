package com.kh.board.model.services;

import static common.template.JDBCtemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

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
	
	public Board selectBoardOne(int no, boolean hasRead) {
		
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoardOne(conn, no);
		
		//조회수
		if(!hasRead) {
			if(b != null) {
				int ck = new BoardDao().updateCount(conn, no);
				if(ck > 0) commit(conn);
				else rollback(conn);
			}
		}
		close(conn);
		return b;
	}
	
	public int insertBoard(Board b) {
		
		Connection conn = getConnection();
		int ck = new BoardDao().insertBoard(conn, b);
		
		if(ck > 0) {
			commit(conn);
			ck = new BoardDao().insertBoardSelectNum(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return ck;
	}
	
	
	public int insertComment(BoardComment bc) {
		
		Connection conn = getConnection();
		int ck = new BoardDao().insertComment(conn, bc);
		
		if(ck > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return ck;
	}
	
	public List<BoardComment> selectBoardComment(int no){
		
		Connection conn = getConnection();
		List<BoardComment> list = new BoardDao().selectBoardComment(conn, no);
		
		close(conn);
		return list;
		
	}

	
	public int deleteComment(int boardRef, int commentNo) {
		Connection conn=getConnection();
		int result= new BoardDao().deleteComment(conn,boardRef,commentNo);
		if(result>0) {
			commit(conn);
		}else {rollback(conn);}
		close(conn);
		return result;
	}
	
	
	
}
