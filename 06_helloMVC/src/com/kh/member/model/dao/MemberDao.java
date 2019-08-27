package com.kh.member.model.dao;

import static common.template.JDBCtemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import common.template.JDBCtemplate;

public class MemberDao {

	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String sql = MemberDao.class.getResource("/sql/member/sql.properties").getPath();
		try {
			prop.load(new FileReader(sql));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member selectId(Connection conn, String id, String pw) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		String sql = "select * from member where userid=? and password=?";
		Member m = null;
		String sql = prop.getProperty("selectId");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			// 추가 쿼리문
			stmt.setString(1, id);
			stmt.setString(2, pw);
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				m = new Member();
				
//				USERID	VARCHAR2(30 BYTE)
//				PASSWORD	VARCHAR2(300 BYTE)
//				USERNAME	VARCHAR2(50 BYTE)
//				GENDER	CHAR(1 BYTE)
//				AGE	NUMBER
//				EMAIL	VARCHAR2(30 BYTE)
//				PHONE	CHAR(11 BYTE)
//				ADDRESS	VARCHAR2(100 BYTE)
//				HOBBY	VARCHAR2(80 BYTE)
//				ENROLLDATE	DATE

				m.setUserId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUsreName(rs.getString("username"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return m;
	}
	
	// 회원 가입
	public int memberEnroll(Connection conn, Member m) {
		
		PreparedStatement stmt = null;
//		insert into member values('a', '1234', 'aaaa', 'F', 11, '223@fdf.com', '01023234234', 'kaaa', 'ee,ee,rr', sysdate);
		String sql = prop.getProperty("insertUser");
		int ck = 0;
//		System.out.println("dfdfdfdf :: " + String.valueOf(m.getGender()));
//		System.out.println("dfdfdfdf :: " + String.valueOf(m.getPhone()));
//		System.out.println(m);
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getUserId());
			stmt.setString(2, m.getPassword());
			stmt.setString(3, m.getUsreName());
			stmt.setString(4, String.valueOf(m.getGender()));
			stmt.setInt(5, m.getAge());
			stmt.setString(6, m.getEmail());
			stmt.setString(7, m.getPhone());
			
			stmt.setString(8, m.getAddress());
			stmt.setString(9, m.getHobby());
			
			ck = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ck;
	}
	
	// userId ckeck
	public boolean selectCheckId(Connection conn, String id) {
		
		PreparedStatement stmt = null;
		String sql = prop.getProperty("selectIsId");
		ResultSet rs = null;
		boolean ck = false;
		
		try {
			stmt =  conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(!rs.next()) {
				ck = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return ck;
	}
	
	
	// mypage
	
	public Member selectInfo(Connection conn, String id) {
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectInfo");
		Member m = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();

			while(rs.next()) {
				m = new Member();

				m.setUserId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUsreName(rs.getString("username"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return m;
		
	}
	
	
	// 회원 정보 수정
	public int memberUpdate(Connection conn, Member m) {
		
		PreparedStatement stmt = null;
		String sql = prop.getProperty("memberUpdate");
		int ck = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, m.getUsreName());
			stmt.setString(2, String.valueOf(m.getGender()) );
			stmt.setInt(3, m.getAge());
			stmt.setString(4, m.getEmail());
			stmt.setString(5, m.getPhone());
			stmt.setString(6, m.getAddress());
			stmt.setString(7, m.getHobby());
			stmt.setString(8, m.getUserId());
			
			ck = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return ck;
	}
	
	// member delete
	public int memberDelete(Connection conn, String userId) {
		
		PreparedStatement stmt = null;
		String sql = prop.getProperty("memberDel");
		int ck = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, userId);
			ck = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return ck;
	}
	
	
	// password update 
	public int updatePassword(Connection conn, String userId, String pwNew) {
		
//		System.out.println("========");
//		System.out.println(userId);
//		System.out.println(pwNew);
//		System.out.println("========");
		
		PreparedStatement stmt = null;
		String sql = prop.getProperty("updatePassword");
		int ck = 0;
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pwNew);
			stmt.setString(2, userId);
			ck = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ck;
	}
	
	
	// 관리자 회원 리스트
	public List<Member> selectList(Connection conn){
		
		PreparedStatement stmt = null;
		String sql = prop.getProperty("selectList");
		List<Member> list = new ArrayList<Member>();
		Member m = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				m = new Member();
				m.setUserId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUsreName(rs.getString("username"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	
	//page
	public int selectCountMember(Connection conn) {
		
		PreparedStatement stmt = null;
		int ck = 0;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCountMember");
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				ck = rs.getInt("cnt");
//				ck = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return ck;
		
	}
	
	
	//page list
	public List<Member> selectListPage(Connection conn, int cPage, int numPerPage){
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectListPage");
		List<Member> list = new ArrayList<Member>();
		Member m = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, (cPage - 1)*numPerPage + 1); // 하단페이지
			stmt.setInt(2, cPage*numPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				m = new Member();
				m.setUserId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUsreName(rs.getString("username"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	
	// 회원 검색 처리하기
	public int selectCountFinder(Connection conn, String type, String key) {
		
		Statement stmt = null;
		ResultSet rs = null;
		int ck = 0;
		String sql="select count(*) as cnt from member where "+type+" like '%"+key+"%'";
		
//		String sql = "select count as cnt from member where "+ type + " like '%" + key + "%'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
	
	
	public List<Member> selectListFinder(Connection conn, String type, String key, int curPage, int numPerPage) {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();
		Member m = null;
		int start = (curPage - 1) * numPerPage + 1;
		int end = curPage * numPerPage;
		
//		String sql = "select * from ("
//				+ "select rownum as rnum, a.* from ("
//				+ "select * from member where "
//				+ type + " like '%" + key + "%')a) "
//				+ "where rnum between " + start + " and " + end;
		String sql="select * from ("
				+ "select rownum as rnum, a.* from("
				+ "select * from member where "
				+ type+" like '%"+key+"%' )a) "
				+ "where rnum between "+start+" and "+end;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				m = new Member();
				m.setUserId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUsreName(rs.getString("username"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
		
	}
	
}