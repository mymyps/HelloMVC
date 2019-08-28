package com.kh.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;

import static common.template.JDBCtemplate.close;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		
		String path = BoardDao.class.getResource("/sql/board/board-sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// board
	public int boardList(Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCount");
//		String sql = "select count(*) as cnt from board where status = 'Y'";
		int ck = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				ck = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return ck;
	}
	
	
	
	public List<Board> boardListView(Connection conn, int cPage, int numPerPage){
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectList");
//		String sql = "select * from (select rownum as rnum, a.* from (select * from board where status = 'Y' order by board_no) a) where rnum between ? and ?";
		List<Board> list = new ArrayList<Board>();
		Board b = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  (cPage-1) * numPerPage + 1 );
			stmt.setInt(2, cPage * numPerPage);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				b = new Board();
				b.setBoardNo( rs.getInt("board_no") );
				b.setBoardTitile(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardOriginalFileName(rs.getString("board_original_filename"));
				b.setBoardRenamedFileName(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
		
	}
	
	public Board selectBoardOne(Connection conn, int no) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		Board b = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				b = new Board();
				b.setBoardNo( rs.getInt("board_no") );
				b.setBoardTitile(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardOriginalFileName(rs.getString("board_original_filename"));
				b.setBoardRenamedFileName(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return b;
	}
	
	
	public int insertBoard(Connection conn, Board b) {
		
		PreparedStatement stmt = null;
		int ck = 0;
		String sql = prop.getProperty("insertBoard");
//		(0, title, writer, content, null, fileName, null, 1);
//		seq_board_no.nextval, ?, ?, ?, default, ?, ?, default, ?
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, b.getBoardTitile());
			stmt.setString(2, b.getBoardWriter());
			stmt.setString(3, b.getBoardContent());
			stmt.setString(4, b.getBoardOriginalFileName());
			stmt.setString(5, b.getBoardRenamedFileName());
			
			ck = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ck;
	}

}
