package com.kh.board.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.services.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewListServlet
 */
@WebServlet("/board/boardView")
public class BoardViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		게시판 글 선택 뷰
//		뷰 전환용도
		
		// 조회수 쿠키를 사용하여 체크
		int no = Integer.parseInt(request.getParameter("no"));
		
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = ""; // 쿠기의 보더를 저장
		boolean hasRead = false; // 게시글을 읽었는지 확인
		
		if(cookies != null) { // 쿠기가 있는지 확인
			out:
				for(Cookie c: cookies) {
					String name = c.getName(); // 키
					String value = c.getValue(); // 값
					
					if("boardCookie".equals(name)){ // boardcookie 아래 특정 구분자를 확인함(키)
						boardCookieVal = value; // Val 에 값을 넣음
						if(value.contains("|"+ no + "|")) { // 컨테인으로 해당 no 값을 찾음
							hasRead = true; // 읽은 적이 있는지 체크
							break out;
						}
					}
				}
			//out:
		}
		
		//http://localhost:9090/06_helloMVC/board/boardView?boardNo=36
		//http://localhost:9090/06_helloMVC/board/boardView?no=36
		
		if(!hasRead) { // false 이면->
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal + "|" + no + "|"); // boardcookie의 키 값으로 해당 no 값을 저장
			boardCookie.setMaxAge(-1); // 브라우저 닫기, 로그아웃했을때
			response.addCookie(boardCookie);
		}
		
		Board b = new BoardService().selectBoardOne(no, hasRead);
		List<BoardComment> list = new BoardService().selectBoardComment(no);
		
		request.setAttribute("list", list);
		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
