package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.services.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginMemberServlet
 */
@WebServlet(name="MemberLogin", urlPatterns = "/login.do")
public class LoginMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로긴 파라미터 값을 받아서 저장
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		
		//test print
//		System.out.println("id : " + id + " / pw : " + pw );
		
		// 로그인에대한 비지니스 로직 처리
		// DB에 접속해서 id,pw 를 확인해서 이후에 결과를 처리
		MemberService service = new MemberService();
		Member m = service.selectId(id, pw);
		
//		System.out.println(m);

		// view 화면을 선택
		String view = "";
		if (m != null) {
			// 로그인 처리
			

			/*
			request.getSession() 매개변수로 true || false 를 리턴함
			true(default) : 기존에 생성된 session 객체가 있으면 불러오고 없으면 생생해서 불러옴
			false : 기존에 생성된 session 긱체가 있으면 불러오고 없으면 null
			*/
			// session(로긴 유지)
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
			
			// session 객체는 getSession()객체를 생성했을때 생성됨
			// session.invalidate() 객체 소멸
			// session.setMaxInactiveInterval() -> 일정시간 후 자동 session.invalidate() 실행
//			session.setMaxInactiveInterval(5);
			
			//session listener 설정
			
//			쿠키에 아이디 저장하기
			String saveId = request.getParameter("saveId");
//			System.out.println(saveId);
			
			if(saveId != null) { //return : on / null
				/*
				1. cookie 저장
				첫번째 매개변수: 키, 두번재 매개변수 : 값
				*/
				Cookie c = new Cookie("saveId", id);
				c.setMaxAge(7*24*60*60);//7일간 유지해라~ 초단위로 계산
				//c.setPath("/");//경로를 설정할 수 있음.
				response.addCookie(c);
				
			}else {
				// checkbox에 체크가 안되어 있을경우 쿠키에서 해당 id 값을 제거
				Cookie c = new Cookie("saveId", id);
				c.setMaxAge(0);//기간이 존재하지않아 바로 생성과 동시에 삭제
				response.addCookie(c);
				
			}
			
			view = "/";  //index.jsp
			response.sendRedirect(request.getContextPath() + view);

		}else {
			// 로그인 실패 처리(로그인 거절에대한 에러화면 -> 메인화면으로 이동)

			String msg = "ID & Password가 일치하지 않습니다.";
			request.setAttribute("msg", msg);
			view = "views/common/msg.jsp";
			String local = "/";
			request.setAttribute("local", local);
//			response.sendRedirect(view);
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
//		request.setAttribute("loginMember", m);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
