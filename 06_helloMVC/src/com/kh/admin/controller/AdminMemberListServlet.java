package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.services.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		if(loginMember == null || !loginMember.getUserId().equals("admin")) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다");
			request.setAttribute("local", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		/* ************************************* */
		// 페이징 처리 
		int cPage; //현재 client가 보고있는 페이지
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1; // 0이나 숫자로 볼수 없는 값은 1로
		}
		
		int numPerPage = 5; // 페이지당 출력되는 페이지수
		// DB에서 데이터 현황, 필요한 데이터만큼만 조회해서 가져옴
		int totalMember = new MemberService().selectCountMember(); //데이터 총 현황을 가져옴
		List<Member> list = new MemberService().selectListPage(cPage, numPerPage); // 받을 데이터 예)5개씩
		
		
		//pageBar 구성! 구성하는 문자열 작성
		String pageBar = "";
		int totalPage = (int)Math.ceil((double)totalMember / numPerPage );
		int pageSizeBar = 5;
		int pageNo = ((cPage-1)/pageSizeBar) * pageSizeBar + 1;
		int pageEnd = pageNo + pageSizeBar - 1;
		
		if(pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		}else {
			pageBar += "<a href = " + request.getContextPath() + "/admin/memberList?cPage=" + (pageNo - 1)+">[이전]"+"</a>";
		}
		
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			if(pageNo == cPage) {
				pageBar += "<span class='cPage'>"+ pageNo + "</span>";
			}
			else {
				pageBar += "<a href = " + request.getContextPath() + "/admin/memberList?cPage=" + pageNo + ">" + pageNo + "</a>";
			}
			pageNo++;

		}
		
		if(pageNo>totalPage) {
			pageBar += "<span>[다음]</span>";
		}else {
			pageBar += "<a href = " + request.getContextPath() + "/admin/memberList?cPage=" + (pageNo)+">[다음]"+"</a>";
		}
		
		
		
		
		
		/* ************************************* */
//		List<Member> list = new MemberService().selectList();
		
		// view 페이지 데이터 전송
		request.setAttribute("pageBar", pageBar); //page
		request.setAttribute("cPage", cPage); //page

		request.setAttribute("members", list);
		request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
