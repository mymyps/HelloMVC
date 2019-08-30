package com.kh.board.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.services.BoardService;
import com.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("tttttttttttttttttttttttttttttttt");
		
		int boardRef = Integer.parseInt(request.getParameter("board_ref"));
		String writer = request.getParameter("writer");
		int level = Integer.parseInt(request.getParameter("level"));
		String content = request.getParameter("content");
		int commetRef = Integer.parseInt(request.getParameter("commentRef"));
		
		BoardComment bc = new BoardComment(level, writer, content, boardRef, commetRef);
//		System.out.println("bc   : " + bc);
		int ck = new BoardService().insertComment(bc);
		
		String msg = "";
		String local = "/board/boardView?no=" + boardRef;
		String view = "/views/common/msg.jsp";
		msg = ck>0?"댓글등록 성공":"댓글등록 실패";
		
		request.setAttribute("msg", msg);
		request.setAttribute("local", local);
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
