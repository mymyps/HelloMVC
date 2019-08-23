package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.services.MemberService;

/**
 * Servlet implementation class MemberCheckIdServlet
 */
@WebServlet("/checkIdDuplicate")
public class MemberCheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 입력한 userid값이 db에 있는지 확인하고 있는지 없는지 view에 출력
		// 사용자가 보낸 데이터를 받아오기
		String userId = request.getParameter("userId");
		
		// 데이터확인 비지니스 로직구현
		MemberService service = new MemberService();
		boolean isEnableId = service.selectCheckId(userId);
		
		// view 결과 데이터 전송
		request.setAttribute("isUserId", isEnableId);
		request.setAttribute("userId", userId);
		request.getRequestDispatcher("views/member/checkIdDuplicate.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
