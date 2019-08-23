package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.services.MemberService;


/**
 * Servlet implementation class UpdatePasswordEndServleit
 */
@WebServlet(name="updatePassword", urlPatterns="/member/updatePasswordEnd")
public class UpdatePasswordEndServleit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServleit() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordNew = request.getParameter("passwordNew");
		
		int ck = new MemberService().updatePassword(userId, password, passwordNew);
		String msg = "";
		String loc = "/member/updatePassword";
		String msgClose = "self.close()";
		
		switch (ck) {
		case 0:
			msg = "패스워드 수정에 실패했습니다.";
			break;
		case -1:
			msg = "현재 패스워드가 일치하지 않습니다.";
			break;
		default:
			msg = "패스워드 수정완료";
			request.setAttribute("msgClose", msgClose);
			break;
		}//switch
		
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
