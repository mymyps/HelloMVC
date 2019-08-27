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
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("searchType");
		String keyword = request.getParameter("searchKeyword");
		int curPage;
		try {
			curPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			curPage = 1;
		}
		
		int numPerPage = 5;
		
		int totalFinder = new MemberService().selectCountFinder(type, keyword);
		List<Member> list = new MemberService().selectListFinder(type, keyword, curPage, numPerPage);
		
		// 전체 페이지 계산
		int totalPage = (int)Math.ceil( (double)totalFinder / numPerPage );
		
		// 페이지 바 구성
		int pageSizeBar = 5; // 페이지 바 크기(나오는 숫자 갯수)
		String pageBar = ""; // 페이지 태그
		int pageSt = ((curPage-1)/pageSizeBar) * pageSizeBar + 1;
		int pageEnd = pageSt + pageSizeBar - 1;

		
		// 화면에 보여줄 페이지 테그
		if(pageSt == 1) {
			pageBar += "<span>[이전]</span>";
		}else {
			pageBar += "<a href = '" + request.getContextPath() + "/admin/memberFinder?cPage=" + (pageSt-1)
					+ "&searchType=" + type + "&searchKeyword=" + keyword
					+ "'>[이전]</a>";
		}
		while( !((pageSt > pageEnd) || pageSt > totalPage )) {
			if(curPage == pageSt) {
				pageBar += "<span class='cPage'>" + pageSt +"</span>";
			}else {
				pageBar += "<a href = '" + request.getContextPath() + "/admin/memberFinder?cPage=" + (pageSt)
						+ "&searchType=" + type + "&searchKeyword=" + keyword
						+ "'>"+ pageSt +"</a>";
			}
			pageSt++;
		}
		
		if(pageSt>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
			+"/admin/memberFinder?cPage="+(pageSt)
			+"&searchType="+type+"&searchKeyword="+keyword
			+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("searchType", type);
		request.setAttribute("searchKeyword", keyword);
		request.setAttribute("cPage", curPage);
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
