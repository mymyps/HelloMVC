package com.kh.board.model.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownloadServlet
 */
@WebServlet("/board/boardFileDown")
public class BoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일 다운로드
//		orifileName="+file+"&refileName=
		String ori = request.getParameter("orifileName");
		String re = request.getParameter("refileName");
		
		// 1.경로
		String saveDir = getServletContext().getRealPath("/upload/board");
		
		// file open
		File f = new File(saveDir + "/" + re);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f)); // 스트림에 파일을 연결
		
		// 보낼 스트림 생성
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		// 브라우저 인코딩
		String filename = "";
		boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE") != -1 
				|| request.getHeader("user-agent").indexOf("trident") != -1;
		
		if(isMSIE) {
			filename = URLEncoder.encode(ori, "UTF-8").replaceAll("\\+", "%20");
		}else {
			filename = new String(ori.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// 4.
		response.setContentType("application/octet-stream"); //이진 파일
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		
		int read = -1;
		while ( ( read = bis.read() ) != -1 ) {
			bos.write(read);
		}
		bos.close();
		bis.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
