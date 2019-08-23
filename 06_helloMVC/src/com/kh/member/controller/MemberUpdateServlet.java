package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.services.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member m = new Member();
		m.setUserId(request.getParameter("userId"));
		m.setUsreName(request.getParameter("userName"));
		m.setAge( Integer.parseInt(request.getParameter("age")));
		m.setEmail(request.getParameter("email"));
		m.setPhone(request.getParameter("phone"));
		m.setAddress(request.getParameter("address"));
		m.setGender(request.getParameter("gender").charAt(0));
		
		String[] hb = request.getParameterValues("hobby");
		m.setHobby( String.join(",", hb ));
		
//		System.out.println(m);
		
		int ck = new MemberService().memberUpdate(m);
//		System.out.println("update : " + ck);
		
		String msg = ck>0?"회원정보가 수정되었습니다":"회원정보가 수정되지 않았습니다.";
		String loc = "/mypage.do?userId=" + m.getUserId();
//		HttpSession hs = request.getSession();
//		hs.setAttribute("memberInfo", m);
		
		request.setAttribute("msg", msg);
		request.setAttribute("local", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
