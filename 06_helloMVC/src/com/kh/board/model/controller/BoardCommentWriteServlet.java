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
 * Servlet implementation class BoardCommentWriteServlet
 */
@WebServlet("/boardComment/commentInsert")
public class BoardCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		String content = request.getParameter("boardCommentContent");
		String boardWriter = request.getParameter("boardCommentWriter");
		int boardLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment bc = new BoardComment(boardLevel, boardWriter, content, boardRef, commentRef);
		int ck = new BoardService().insertComment(bc);
		
		String msg = "";
		String local = "/board/boardView?boardNo=" + boardRef;
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
