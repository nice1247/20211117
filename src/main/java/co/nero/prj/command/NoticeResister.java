package co.nero.prj.command;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.nero.prj.comm.Command;
import co.nero.prj.notice.service.NoticeService;
import co.nero.prj.notice.service.NoticeVO;
import co.nero.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeResister implements Command {
	private String filePath = "C:\\FileTest"; // 파일이 저장되는 절대경로
	private int fileSize = 1024*1024*100; // 파일 최대 사이즈
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 저장
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id")); // 세션에 저장된 id값
		vo.setName((String) session.getAttribute("name")); // 세션에 저장된 이름
		try {
			MultipartRequest multi = new MultipartRequest(request, filePath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			// 객체 생성시 파일이 전송됨 , multipartrequest는 cos.jar에서 제공, new~ 중복된 파일이 오면 자동으로 인덱스형성
			String fileName = multi.getFilesystemName("fileName"); // filename은 중복이름이 들어올 경우 자동으로 index가 있는 물리 파일명
			String original = multi.getOriginalFileName("fileName"); // index되기 전의 파일명
			fileName = filePath + File.separator + fileName; // 저장경로를 포함해서 만듦 (file.separator = /)
			
			vo.setFileName(original); // 원본파일 저장
			vo.setPfileName(fileName); // 물리파일 저장
			vo.setWdate(Date.valueOf(multi.getParameter("wdate"))); // form에서 넘어오는 wdate(String)을 date값으로 바꿔줌
			vo.setTitle(multi.getParameter("title"));
			vo.setSubject(multi.getParameter("subject"));
			noticeDao.noticeInsert(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "noticeList.do";
	}

}
