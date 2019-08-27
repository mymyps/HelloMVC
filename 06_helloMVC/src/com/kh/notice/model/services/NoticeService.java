package com.kh.notice.model.services;

import java.sql.Connection;
import java.util.List;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

import common.template.JDBCtemplate;

public class NoticeService {

	private NoticeDao nDao = new NoticeDao();

	// notice page
	public int selectNotice() {

		Connection conn = JDBCtemplate.getConnection();
		int ck = nDao.selectNotice(conn);

		JDBCtemplate.close(conn);
		return ck;

	}

	// notice list page
	public List<Notice> selectNoticeList(int cPage, int numPerPage){

		Connection conn = JDBCtemplate.getConnection();
		List<Notice> list = nDao.selectNoticeList(conn, cPage, numPerPage);

		JDBCtemplate.close(conn);
		return list;
	}
	
	// notice click one
	public Notice selectNoticeOne(int no) {
		
		Connection conn = JDBCtemplate.getConnection();
		Notice n = nDao.selectNoticeOne(conn, no);
		
		JDBCtemplate.close(conn);
		return n;
		
		
	}
	
	
	// notice insert /// mr
	public int insertNotice(Notice n) {
		Connection conn = JDBCtemplate.getConnection();
		int result = nDao.insertNotice(conn, n);
		
		if(result > 0)
			JDBCtemplate.commit(conn);
		else
			JDBCtemplate.rollback(conn);
		
		JDBCtemplate.close(conn);
		return result;
	}
	
	
	
}
