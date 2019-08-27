package com.kh.notice.model.dao;

import static common.template.JDBCtemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = new Properties();
	
	public NoticeDao() {
		String path = NoticeDao.class.getResource("/sql/notice/notice-sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// notice page
	public int selectNotice(Connection conn) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNotice");
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

	// notice list page
	public List<Notice> selectNoticeList(Connection conn, int cPage, int numPerPage){

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNoticeList");
		List<Notice> list = new ArrayList<Notice>();
		Notice n = null; 

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, (cPage-1) * numPerPage + 1);
			stmt.setInt(2, cPage * numPerPage);
			rs = stmt.executeQuery();

			while (rs.next()) {
				n = new Notice();
				n.setNoticeNo(rs.getInt("NOTICE_NO"));
				n.setNoticeTitile(rs.getString("NOTICE_TITLE"));
				n.setNoticeWriter(rs.getString("NOTICE_WRITER"));
				n.setNoticeWriter(rs.getString("NOTICE_CONTENT"));
				n.setNoticeDate(rs.getDate("NOTICE_DATE"));
				n.setFilePath(rs.getString("FILEPATH"));
				n.setStatus(rs.getString("STATUS").charAt(0));

				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}

		return list;
	}
	
	
	// notice click one
	public Notice selectNoticeOne(Connection conn, int no) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNoticOne");
		Notice n = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				n = new Notice();
				n.setNoticeNo(rs.getInt("NOTICE_NO"));
				n.setNoticeTitile(rs.getString("NOTICE_TITLE"));
				n.setNoticeWriter(rs.getString("NOTICE_WRITER"));
				n.setNoticeWriter(rs.getString("NOTICE_CONTENT"));
				n.setNoticeDate(rs.getDate("NOTICE_DATE"));
				n.setFilePath(rs.getString("FILEPATH"));
				n.setStatus(rs.getString("STATUS").charAt(0));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return n;
		
	}

	
	// notice mr
	public int insertNotice(Connection conn, Notice n) {
		
		PreparedStatement stmt = null;
		int ck = 0;
		String sql = prop.getProperty("insertNotice");
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, n.getNoticeTitile());
			stmt.setString(2, n.getNoticeWriter());
			stmt.setString(3, n.getNoticeContent());
			stmt.setString(4, n.getFilePath());
			
			ck = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ck;
	}

}
