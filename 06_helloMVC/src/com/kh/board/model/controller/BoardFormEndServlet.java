package com.kh.board.model.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.services.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.notice.model.services.NoticeService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.fileRename.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/boardFormEnd")
public class BoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( !ServletFileUpload.isMultipartContent(request) ) {
			request.setAttribute("msg", "업로드 에러!");
			request.setAttribute("local", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		String root = getServletContext().getRealPath("/");
		String saveDir = root + "upload"+ File.separator +"board";
		int maxSize = 1024 * 1024 * 10;
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String content = mr.getParameter("content");
		
		String fileName = mr.getFilesystemName("up_file");
		String fileOriName = mr.getOriginalFileName("up_file");
		Board b = new Board(title, writer, content, fileOriName, fileName);
//		Board b1 = new Board(0, title, writer, content, fileOriName, fileName, null, 1);
		
		int ck = new BoardService().insertBoard(b);
		
		String msg = "";
		String local = "";
		
		if(ck > 0) {
			msg = "게시글 등록 완료";
			local = "/board/boardView?no=" + ck;
		}else {
			File remove = new File(saveDir + "/" + b.getBoardRenamedFileName());
			remove.delete();
			msg = "게시글 등록 실패";
			local = "/board/boardList";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("local", local);
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
