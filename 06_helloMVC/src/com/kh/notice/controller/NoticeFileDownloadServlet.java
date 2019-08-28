package com.kh.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownloadServlet
 */
@WebServlet("/notice/noticeFileDown")
public class NoticeFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		***** 파일 다운로드 서블릿 *****
		1. 실제 경로를 가져오기
		2. 인코딩처리(한글) 자동으로 인코딩 처리되는 브라우저도 있지만 없는 브라우저도 있기에 인코딩 처리해야함
			(client 가 보낼때도 인코딩 처리를 해줘야함)
		3. 저장파일과 스트림 연결(파일 입출력)
		4. response 해더 수정
		contentType:application/octet-stream,
		Content-Disposition:attachment;
		filename=파일명
		5. 파일을 respones stream 으로 전송하기
		*/
		
		// 1.
		String rootPath = getServletContext().getRealPath("/");
		String saveDir = rootPath + File.separator + "upload"+ File.separator +"notice";
		String fileName = request.getParameter("fileName");
		
		// 3. 대상파일을 하드->렘 으로 로드
		File downFile = new File(saveDir + "/" + fileName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		// 한글 파일명을 보낼때 깨지지 않게 인코딩 처리
		String resFilename = "";
		boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE") != -1
			|| request.getHeader("user-agent").indexOf("Trident") != -1;
		
		if(isMSIE) {
			resFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		}else {
			resFilename = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// 4.
		response.setContentType("application/octet-stream"); //이진 파일
		response.setHeader("Content-Disposition", "attachment;filename=" + resFilename);
		//attachment : 다운로드 창 활성화
		//inline : 익스플로러 이미지 로드 활성화
		
		// 5.
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
