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
 * Servlet implementation class MemberEnrollEndServlet
 */
@WebServlet(name = "MemberEnroll", urlPatterns="/memberEnrollEnd")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member m = new Member();
		m.setUserId(request.getParameter("userId"));
		m.setPassword(request.getParameter("password"));
		m.setUsreName(request.getParameter("userName"));
		m.setAge( Integer.parseInt(request.getParameter("age") ));
		m.setEmail(request.getParameter("email"));
		m.setPhone(request.getParameter("phone"));
		m.setAddress(request.getParameter("address"));
		m.setGender(request.getParameter("gender").charAt(0));
		String[] hb = request.getParameterValues("hobby");
		m.setHobby( String.join(",", hb ));
//		String.join(",", hobby) 배열을 스트링으로
		
		int ck = new MemberService().memberEnroll(m);
//		System.out.println("insert : " + ck);
		
		String msg = "";
		String loc = "/";
		msg = ck>0?"성공적으로 회원등록완료" : "회원등록 실패";
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
