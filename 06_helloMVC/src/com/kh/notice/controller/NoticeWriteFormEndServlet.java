package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.notice.model.services.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class NoticeWriteFormEndServlet
 */
@WebServlet("/notice/noticeFormEnd")
public class NoticeWriteFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// file up로드 구현
		// 1.enctype = "multipart/form-data" <--
		// client 가 데이터를 multipart/form-data로 보냈는지 확인
		if(!ServletFileUpload.isMultipartContent(request)){
			request.setAttribute("msg", "공지사항 에러!!");
			request.setAttribute("local", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//			return;
		}
		
		// 파일 업로드 처리 DB저장
		// 파일 업로드 경로를 가져옴
		// 서버의 디렉토리 절대경로를 가져와야함
		String root = getServletContext().getRealPath("/");
		System.out.println(root);
		
		// 파일 저장할 위치를 선정
		// 서버내부에 위치를 만들어 줘야함
		
		String saveDir = root+"upload/notice"; // 새로운 경로를 설정
		
		// 2. 업로드 파일의 최대용량을 정함
		int maxSize = 1024 * 1024 * 10;
		
		// 3. cos에서 지원하는 객체를 생성 (cos.jar 파일)
		/*
		multiPartRequest(파일처리 객체)
		매개변수가 있는 생성자를 이용해 생성할 수 있다.
		new MultipartRequest(HttpServletRequest, 저장경로, 최대파일크기, 문자열 인코딩, 파일 rename)
		*/

		// 생성후 바로 파일 업로드가 진행됨
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8",
							new DefaultFileRenamePolicy());
		
		// client 가 보낸 값을 받아오기
		// multiPartRequest 객체에서 가져옴
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String content = mr.getParameter("content");
		
		// file명 가져오기
		String fileName = mr.getFilesystemName("up_file");
		Notice n = new Notice(0, title, writer, content, null, fileName, 'Y');
		System.out.println("n 값 : " + n);
		
		int result = new NoticeService().insertNotice(n);
		
		String msg = "";
		String local = "";
		
		if(result > 0) {
			msg = "공지사항 등록 완료";
//			local = "/notice/noticeList";
			local = "/notice/noticeView?no=" + result;
		}else {
			msg = "공지사항 등록 실패";
			local = "/notice/noticeForm";
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
