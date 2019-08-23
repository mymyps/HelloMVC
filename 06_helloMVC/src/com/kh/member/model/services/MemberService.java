package com.kh.member.model.services;

import java.sql.Connection;
import java.util.List;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

import common.template.JDBCtemplate;

public class MemberService {

	private MemberDao mDao = new MemberDao();
	
	public Member selectId(String id, String pw) {
		
		Connection conn = JDBCtemplate.getConnection();
		Member m = mDao.selectId(conn, id, pw);
		JDBCtemplate.close(conn);
		
		return m;
	}
	
	public int memberEnroll(Member m) {
		
		Connection conn = JDBCtemplate.getConnection();
		int ck = mDao.memberEnroll(conn, m);
		
		if (ck == 0) {
			JDBCtemplate.rollback(conn);
		}else {
			JDBCtemplate.commit(conn);
		}
			
		JDBCtemplate.close(conn);
		
		return ck;
		
	}
	
	public boolean selectCheckId(String userId) {
		
		Connection conn = JDBCtemplate.getConnection();
		boolean ck = mDao.selectCheckId(conn, userId);
		
		JDBCtemplate.close(conn);
		
		return ck;
	}
	
	// mypage info
	public Member selectInfo(String userId) {
		
		Connection conn = JDBCtemplate.getConnection();
		Member m =  mDao.selectInfo(conn, userId);
		
		if(m != null) {
			JDBCtemplate.commit(conn);
		}else
			JDBCtemplate.rollback(conn);
		
		JDBCtemplate.close(conn);
		
		return m;
		
	}
	
	// member update
	public int memberUpdate(Member m) {
		
		Connection conn = JDBCtemplate.getConnection();
		int ck = mDao.memberUpdate(conn, m);
		
		if(ck != 0) {
			JDBCtemplate.commit(conn);
		}else {
			JDBCtemplate.rollback(conn);
		}
		JDBCtemplate.close(conn);
		
		return ck;
		
	}
	
	//member delete
	public int memberDelete(String userId) {
		
		Connection conn = JDBCtemplate.getConnection();
		int ck = mDao.memberDelete(conn, userId);
		
		if(ck != 0) {
			JDBCtemplate.commit(conn);
		}else {
			JDBCtemplate.rollback(conn);
		}
		JDBCtemplate.close(conn);
		
		return ck;
		
	}
	
	
	// password update 
	public int updatePassword(String userId, String pw, String pwNew) {
		
		Connection conn = JDBCtemplate.getConnection();
		Member m = mDao.selectId(conn, userId, pw);				//검색해서 유저를 찾음
		
		
		int ck = 0;
		
		if(m != null) {
//			System.out.println("패스워드 받음 : " + m.getPassword());
			// 패스워드 수정
			ck = mDao.updatePassword(conn, userId, pwNew);
		}else {
			// 현재 패스워드가 틀려서 입력이 안됐다는 구분
			ck = -1;
		}
		if(ck > 0) {
			JDBCtemplate.commit(conn);
			
		}else 
			JDBCtemplate.rollback(conn);
		
		JDBCtemplate.close(conn);
		
		return ck;
		
	}
	
	// 관리자 회원 리스트 관리
	public List<Member> selectList(){
		
		Connection conn = JDBCtemplate.getConnection();
		List<Member> list = mDao.selectList(conn);
		
//		if(list != null) {
//			JDBCtemplate.commit(conn);
//		}else
//			JDBCtemplate.rollback(conn);
		
		JDBCtemplate.close(conn);
		
		return list;
	}
}
